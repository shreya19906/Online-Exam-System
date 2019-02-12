package com.Exam;
import com.Question.*;
import com.SendEmail.*;
import com.Student.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
//import java.util.*;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/ExamSystemServlet")

public class ExamSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private StudentDBUtil studentDBUtil;
    private QuestionDBUtil questionDBUtil;
    BufferedImage img;
   
    @Resource(name="jdbc/sys")
	private DataSource dataSource;
	
	@Override  
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDBUtil = new StudentDBUtil(dataSource);
			questionDBUtil=new QuestionDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");
			
			switch(theCommand) {
			
			case "signup": {
				signup(request,response);
				break;
			}
			
			case "login": {
				login(request,response);
				break;
			}
			
			case "exam": {
				exam(request,response);
			}
		 }
			
        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exam(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		calculate(request,response);
		
		sendEmail(request,response);
}

	private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Student s=(Student)session.getAttribute("StudentInfo");
		String receiverEmail=s.getEmail();
		int marks=s.getMarks();
		String subject="DIG.COM";
		String text="Thank you for taking the test.";
		
		SendEmail.send(receiverEmail,subject,text);
		response.setContentType("text/plain");
		
		response.sendRedirect("ThankYou.jsp");
		
		//PrintWriter out=response.getWriter();
		//out.println("Thankyou for taking the test.Marks has been emailed to you");
		
	}

	private void calculate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession(false);
		List<Question> qList = (List<Question>)session.getAttribute("QUESTION_LIST");
		
		List<String> ans=new ArrayList<>();
		for(int i=1;i<11;i++)
		{
			String param="ans"+i;
			ans.add(request.getParameter(param));
		}
	
		int marks=questionDBUtil.calculate_marks(qList,ans);
		Student s=(Student)session.getAttribute("StudentInfo");
		
		s.setMarks(marks);
		
		session.setAttribute("StudentInfo", s);
		try 
		{
		img=new CertificateGenerator(s.getFirstname() +"  "+s.getLastname(),s.getMarks()).read();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void setQuestions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Question> ques=null;
		try {
			ques = questionDBUtil.getQuestions();
			
			HttpSession session=request.getSession(false);
			//session.setAttribute("QuestionList",ques);
			
			request.setAttribute("QUESTION_LIST", ques);
			
			RequestDispatcher dispatcher =request.getRequestDispatcher("/Instruction.jsp");
			dispatcher.forward(request,response);
			
		  } catch (Exception e) {
			   e.printStackTrace();
		}
	}
		
	    private void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");	
		String password = request.getParameter("pwd");
		password=Crypto.encrypt(password);
		//System.out.println(password);
		
		Student s=new Student(firstName,lastName,email,password);
		//HttpSession session=request.getSession(true);
		//session.setAttribute("StudentInfo", s);
		studentDBUtil.addStudent(s);
	    //setQuestions(request,response);
		response.sendRedirect("login.html");
	    
	}
	
private void login (HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		String email = request.getParameter("email");	
		String password = request.getParameter("pwd");
		
		Student s=new Student(email,password);
		s.setPwd(Crypto.decrypt(s.getPwd()));
		
		PrintWriter out=response.getWriter();
		
		response.setContentType("text/html");	
		boolean check= studentDBUtil.login_authentication(s);
	    
		if(!check)
		{
			response.sendRedirect("loginFailed.html");
			
			/*
	    out.println("<html><body>");
        out.println("<p>The email or the password is incorrect</p><br><br>");
		out.println("<a href=\"login.html\">try again</a>");
		out.println("</body></html>");
		*/
		}
		else
		{
			
			HttpSession session=request.getSession(true);
			session.setAttribute("StudentInfo", s);
			
			setQuestions(request,response);
			
			//setQuestions(request,response);
			
			//response.sendRedirect("Instruction.jsp");
			
			
			
			

		}
	}
}
