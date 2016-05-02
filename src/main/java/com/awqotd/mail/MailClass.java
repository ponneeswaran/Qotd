package com.awqotd.mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awqotd.dao.DataAccessor;
import com.awqotd.dao.RecommenderDetailsVO;
import com.awqotd.vo.OptionDetailVO;
import com.awqotd.vo.QuizDetailVO;
import com.awqotd.vo.UserDetailsVO;

@Service
public class MailClass 
{
	@Autowired
	private DataAccessor dataAccessor;
	private Properties properties;
	private Session session;
	private static final String FROM_ADDRESS = "aqotdmailer@gmail.com";
	private static final String URL_PREFIX = "http://localhost:8080/QOTD-0.0.1-SNAPSHOT/submitAnswer/";
	Authenticator auth;
	public MailClass()
	{
		init();		
	}
	public void init()
	{
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.user", FROM_ADDRESS);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");
		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_ADDRESS, "Adaptive1234");
            }
		};
		session = Session.getDefaultInstance(properties,auth);
	}
	public void createAndSendMsg(String to, String subject, String msg_content)
	{
		MimeMessage message = new MimeMessage(session);
		try 
		{
			message.setFrom(new InternetAddress(FROM_ADDRESS));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(msg_content, "text/html");
			Transport.send(message);
		}
		catch (AddressException e) 
		{
			e.printStackTrace();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}	
	}
	public String getDate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	public String getUrlTemplate(String label, String url)
	{
		String result = "<a href=\""+url+"\">"+label+"</a>";
		System.out.println(result);
		return result;
	}
	public String generateURL(int user_id, int question_id, int quiz_id)
	{
		StringBuilder result = new StringBuilder();
		Integer unique_id = dataAccessor.getUniqueId(user_id, quiz_id, question_id);
		result.append(URL_PREFIX).append(unique_id).append("/");
		return result.toString();
	}
	public void processQuiz(QuizDetailVO quizDetailVO)
	{
		String subject = "Quiz question for "+quizDetailVO.getQuiz_name();
		String new_line = "<br>";
		StringBuilder b = new StringBuilder();
		b.append(new_line);
		b.append("Please Find Below the quiz question for ").append(getDate()).append(new_line);
		b.append(new_line).append("<b> Question: ");
		b.append(quizDetailVO.getQuestion().getQuestionText()).append("</b>").append(new_line).append(new_line);
		for(UserDetailsVO user_detail: quizDetailVO.getMailingList())
		{
			StringBuilder b1 = new StringBuilder();
			b1.append(b);
			String url = generateURL(user_detail.getUserId(), quizDetailVO.getQuestion().getQuestion_id(), quizDetailVO.getQuiz_id());
			int count = 1;
			for (OptionDetailVO temp: quizDetailVO.getQuestion().getOptions())
			{
				b1.append(getUrlTemplate(Integer.toString(count)+")", url+temp.getOption_id()));
				b1.append(" <b>");
				b1.append(temp.getOption_text()).append("</b>");
				b1.append(new_line).append(new_line);
				count++;
			}
			createAndSendMsg(user_detail.getEmailId(), subject, b1.toString());
		}
	}
	public void sendRecommendation(String user_email, List<RecommenderDetailsVO> recommenderDetailsVOs, double correctness)
	{
		String subject = "Recommendations for Quiz on "+getDate();
		String new_line = "<br>";
		StringBuilder b = new StringBuilder();
		b.append(new_line);
		if(correctness == 0.0)
			b.append("<b>Your response to the question was incorrect. Please Find Below few recommended links that might help</b>");
		else if (correctness < 90)
			b.append("<b>Your response to the question was partially correct. Please Find Below some recommended links for the question</b>");
		else
			b.append("<b>Your response to the question was correct. Please Find Below some recommended links for related topics</b>");
		b.append(new_line).append(new_line);
		int count = 1;
		for(RecommenderDetailsVO temp: recommenderDetailsVOs)
		{
			b.append(count++).append(") ").append(getUrlTemplate(temp.getUrl(), temp.getUrl())).append(new_line);
			System.out.println(temp.getUrl());
		}
		b.append(new_line);
		
		createAndSendMsg(user_email, subject, b.toString());
	}
	public static void main(String[] args) 
	{
		/*String from = "aqotdmailer@gmail.com";
		String subject = "Test Mail Adaptive Web";
		String msg_content = "<html><body><h3>If you can see this then AJITHS quiz of the day works!!</h3>"
				+ "<form>First name:<br><input type=\"text\" name=\"firstname\"><br>Last name:"
				+ "<br><input type=\"text\" name=\"lastname\"></form></body></html>";
		String msg_cont_1 = "<!DOCTYPE html><html><body><button onclick=\"myFunction()\">Try it</button><script>function myFunction() {alert(\"I am an alert box!\");}</script></body></html>";
		List<String> list = new ArrayList<String>();
		list.add("Anandh.Ravikumar@asu.edu");
		list.add("mmalaiar@asu.edu");
		list.add("pnatara8@asu.edu");
		list.add("avimalch@asu.edu");
		new MailClass().createAndSendMsg(list, subject, msg_cont_1);*/
		System.out.println(Calendar.getInstance().getTime());
	}
}