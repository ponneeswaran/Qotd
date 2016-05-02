package com.awqotd.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import com.awqotd.dao.RecommenderDetailsVO;

@Service
public class Oracle_Searcher 
{
	private IndexSearcher indexSearcher;
	private QueryParser queryParser;
	private Query query;
	private static final String STOP_WORDS = "a,about,above,across,after,afterwards,again,against,all,almost,alone,along,already,also,although,always,am,among,amongst,amoungst,amount,an,and,another,any,anyhow,anyone,anything,anyway,anywhere,are,around,as,at,back,be,became,because,become,becomes,becoming,been,before,beforehand,behind,being,below,beside,besides,between,beyond,bill,both,bottom,but,by,call,can,cannot,cant,co,computer,con,could,couldnt,cry,de,describe,detail,do,done,down,due,during,each,eg,eight,either,eleven,else,elsewhere,empty,enough,etc,even,ever,every,everyone,everything,everywhere,except,few,fifteen,fify,fill,find,fire,first,five,for,former,formerly,forty,found,four,from,front,full,further,get,give,go,had,has,hasnt,have,he,hence,her,here,hereafter,hereby,herein,hereupon,hers,herse,him,himse,his,how,however,hundred,i,ie,if,in,inc,indeed,interest,into,is,it,its,itse,keep,last,latter,latterly,least,less,ltd,made,many,may,me,meanwhile,might,mill,mine,more,moreover,most,mostly,move,much,must,my,myse,name,namely,neither,never,nevertheless,next,nine,no,nobody,none,noone,nor,not,nothing,now,nowhere,of,off,often,on,once,one,only,onto,or,other,others,otherwise,our,ours,ourselves,out,over,own,part,per,perhaps,please,put,rather,re,same,see,seem,seemed,seeming,seems,serious,several,she,should,show,side,since,sincere,six,sixty,so,some,somehow,someone,something,sometime,sometimes,somewhere,still,such,system,take,ten,than,that,the,their,them,themselves,then,thence,there,thereafter,thereby,therefore,therein,thereupon,these,they,thick,thin,third,this,those,though,three,through,throughout,thru,thus,to,together,too,top,toward,towards,twelve,twenty,two,un,under,until,up,upon,us,very,via,was,we,well,were,what,whatever,when,whence,whenever,where,whereafter,whereas,whereby,wherein,whereupon,wherever,whether,which,while,whither,who,whoever,whole,whom,whose,why,will,with,within,without,would,yet,you,your,yours,yourself,yourselves,Trail,Lesson";
	private CharArraySet obj;
	public Oracle_Searcher()
	{
		init();
	}
	public CharArraySet getStopWords()
	{
		if(obj == null)
			obj =new CharArraySet(Arrays.asList(STOP_WORDS.split(",")), true);
		return obj;
	}
	public void init()
	{
		try 
		{
			Directory indexDir = FSDirectory.open(Paths.get("C:\\Users\\Ponneeswaran\\Downloads\\LIndex\\LIndex"));
			indexSearcher = new IndexSearcher(DirectoryReader.open(indexDir));
			queryParser = new QueryParser("keywords", new StandardAnalyzer(getStopWords()));
		}
		catch (IOException e) 
		{
			System.out.println("Error Opening Directory Index!");
			e.printStackTrace();
		}
	}
	public List<RecommenderDetailsVO> search(String searchQuery, int count, Map<String, Integer> is_already_present) throws IOException, ParseException 
	{
		List<RecommenderDetailsVO> result = new ArrayList<RecommenderDetailsVO>();
		query = queryParser.parse(searchQuery);
		int list_count = 0;
		for (ScoreDoc value: indexSearcher.search(query, count+10).scoreDocs)
		{
			Document doc = indexSearcher.doc(value.doc);
			String url = doc.get("url");
			if(is_already_present.containsKey(url))
				continue;
			RecommenderDetailsVO temp = new RecommenderDetailsVO();
			temp.setUrl(url);
			temp.setScore(value.doc);
			result.add(temp);
			list_count++;
			if(list_count==count)
				break;
		}
		return result;
	}
}
