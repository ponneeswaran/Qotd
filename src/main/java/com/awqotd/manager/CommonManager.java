package com.awqotd.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awqotd.dao.DAG;
import com.awqotd.dao.DataAccessor;
import com.awqotd.dao.RecommenderDetailsVO;
import com.awqotd.lucene.Oracle_Searcher;
import com.awqotd.mail.MailClass;
import com.awqotd.vo.Node;
import com.awqotd.vo.QuizDetailVO;
import com.awqotd.vo.SubAnsResVO;
import com.awqotd.vo.UserKnowledgeVO;

@Service
public class CommonManager 
{
	@Autowired
	DataAccessor dataAccessor;
	@Autowired
	MailClass mailClass;
	@Autowired
	Oracle_Searcher oracleSearcher;
	@Autowired
	DAG dag;

	public void sendQuiz()
	{
		List<QuizDetailVO> quizes = dataAccessor.getQuizContent();
		for(QuizDetailVO temp: quizes)
		{
			temp.setMailingList(dataAccessor.getQuizUsers(temp.getQuiz_id()));
			mailClass.processQuiz(temp);
		}
	}
	public int isNumeric(String input)
	{
		int result = -1;
		try
		{
			result = Integer.parseInt(input);
		}
		catch(Exception e){}
		return result;
	}
	public String submitResponse(String u_id, String o_id)
	{
		int unique_id = isNumeric(u_id);
		int option_id = isNumeric(o_id);
		String result = null;
		SubAnsResVO resp_obj = null;
		boolean give_recommendation = false;
		Map<String, UserKnowledgeVO> user_knowledge_details = null;
		Map<String, Double> question_TW_dict = null;
		int status = 0;
		if(unique_id == -1 || option_id == -1)
			status = 0;
		else
		{
			resp_obj = dataAccessor.updateStudentResponse(unique_id, option_id);
			status = resp_obj.getStatus();
			System.out.println(resp_obj.getQuestion_id()+" "+resp_obj.getQuiz_id()+" "+resp_obj.getUser_id());
		}
		switch(status)
		{
		case 0:
			result = "<h2>Invalid option! Wrong URL given</h2>";
			break;
		case 1:
			result = "<h2>Quiz question already answered!</h2>";
			break;
		case 2:
			result = "<h2>Quiz duration has expired! You cannot submit your answers!</h2>";
			break;
		case 3:
			result = "<h2>Your response has been recorded. Please wait for a feedback mail</h2>";
			give_recommendation = true;
			break;
		default:
			result = "<h2>Invalid option! Wrong URL given</h2>";
		}
		if(give_recommendation)
		{
			user_knowledge_details= dataAccessor.getUserKnowledgeData(resp_obj.getUser_id());
			question_TW_dict = dataAccessor.getQTagData(resp_obj.getQuestion_id(), option_id);
			user_knowledge_details = createUserKnowledgeQueries(user_knowledge_details, question_TW_dict, resp_obj.getUser_id());
			double correctness = dataAccessor.getCorrectness(option_id);
			String tags = getTagsForRecommendation(user_knowledge_details, question_TW_dict, correctness);
			mailClass.sendRecommendation(dataAccessor.getUserEmail(resp_obj.getUser_id()), getRecommendation(tags), correctness);
		}
		return result;
	}
	public Map<String, UserKnowledgeVO> createUserKnowledgeQueries(Map<String, UserKnowledgeVO> user_knowledge_details, Map<String, Double> question_TW_dict, int user_id)
	{
		StringBuilder queries = new StringBuilder();
		for (Map.Entry<String, Double> entry : question_TW_dict.entrySet()) 
		{
		    String tag = entry.getKey();
		    Double weight = entry.getValue();
		    UserKnowledgeVO temp = user_knowledge_details.get(tag);
		    if(temp!=null)
		    {
		    	double adj = (1.0-(temp.getWeight()/100.0)) * weight;
		    	double res = adj+temp.getWeight();
		    	res = Math.max(Math.min(res, 100.0), 0);
		    	adj = res-temp.getWeight();
		    	temp.setWeight(res);
		    	String to_print = "update user_knowledge set weight = "+res+", last_adj_wgt = "+adj+" where tag_name='"+tag+"' and user_id="+user_id+";"; 
		    	System.out.println(to_print);
		    	queries.append(to_print);
		    }
		    else
		    {
		    	double res = Math.max(Math.min(weight, 100), 0);
		    	StringBuilder b = new StringBuilder();
		    	b.append("insert into user_knowledge (user_id, tag_name, weight, last_adj_wgt) values (");
		    	b.append(user_id).append(",'");
		    	b.append(tag).append("',");
		    	b.append(res).append(",").append(res).append(");");
		    	System.out.println(b.toString());
		    	queries.append(b.toString());
		    	temp = new UserKnowledgeVO(tag, res, res);
		    }
		    user_knowledge_details.put(tag, temp);
		}
		dataAccessor.executeBatch(queries.toString());
		return user_knowledge_details;
	}
	public String getTagsForRecommendation(Map<String, UserKnowledgeVO> input,Map<String, Double> question_TW_dict, double correctness)
	{
		StringBuilder result = new StringBuilder();
		for(Map.Entry<String, UserKnowledgeVO> input1: input.entrySet())
		{
			
			List<Node> parent_list = null;
			List<Node> child_list = null;
			String tag_name = input1.getKey();
			if(question_TW_dict.get(tag_name)==null)
				continue;
			Node current_node = dag.getNodeFromTagName(tag_name);
			parent_list = dag.getParentNodes(current_node);
			child_list = dag.getChildNodes(current_node);
			if(current_node == null)
				continue;
			if(correctness<80)
			{
				StringBuilder high_value = new StringBuilder();
				boolean low_value = false;
				for(Node p_node: parent_list)
				{
					UserKnowledgeVO detail = input.get(p_node.getTagName());
					double wt = detail==null?0:detail.getWeight();
					if(wt<65)
					{
						result.append(p_node.getTagName()).append(",");
						low_value = true;
					}
					else
					{
						high_value.append(p_node.getTagName()).append(" ");
					}
				}
				if(parent_list.size()>0 && !low_value)
					result.append(high_value.toString()).append(",");
				result.append(current_node.getTagName()).append(",");
			}
			else
			{
				StringBuilder high_value = new StringBuilder();
				boolean low_value = false;
				for(Node c_node: child_list)
				{
					UserKnowledgeVO detail = input.get(c_node.getTagName());
					double wt = detail==null?0:detail.getWeight();
					if(wt<65)
					{
						result.append(c_node.getTagName()).append(",");
						low_value = true;
					}
					else
					{
						high_value.append(c_node.getTagName()).append(" ");
					}
				}
				if(child_list.size()>0 && !low_value)
					result.append(high_value.toString()).append(",");
				result.append(current_node.getTagName()).append(",");
			}
		}
		System.out.println(result.toString());
		return result.toString();
	}
	public List<RecommenderDetailsVO> getRecommendation(String query)
	{
		List<RecommenderDetailsVO> result = new ArrayList<RecommenderDetailsVO>();
		String [] tokens = query.split(",");
		Map<String, Integer> is_already_present = new HashMap<String, Integer>();
		int no_of_recommendation = (int) Math.ceil(10.0/tokens.length);
		for (String q_string : tokens)
		{
			try 
			{
				List<RecommenderDetailsVO> temp = oracleSearcher.search(q_string, no_of_recommendation,is_already_present);
				for (RecommenderDetailsVO rdVO: temp)
				{
					result.add(rdVO);
					is_already_present.put(rdVO.getUrl(), 1);
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		}
		Collections.sort(result,new Comparator<RecommenderDetailsVO>() 
		{
			@Override
			public int compare(RecommenderDetailsVO o1, RecommenderDetailsVO o2) 
			{
				return (int)Math.ceil(o1.getScore()-o2.getScore());
			}
		});
		return result;
	}
}
