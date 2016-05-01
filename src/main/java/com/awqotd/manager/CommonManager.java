package com.awqotd.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awqotd.dao.DataAccessor;
import com.awqotd.mail.MailClass;
import com.awqotd.vo.QuizDetailVO;
import com.awqotd.vo.SubAnsResVO;

@Service
public class CommonManager 
{
	@Autowired
	DataAccessor dataAccessor;
	@Autowired
	MailClass mailClass;

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
			break;
		default:
			result = "<h2>Invalid option! Wrong URL given</h2>";
		}
		return result;
	}
}
