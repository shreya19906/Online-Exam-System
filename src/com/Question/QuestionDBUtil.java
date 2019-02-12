package com.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class QuestionDBUtil {
	private DataSource dataSource;

	public QuestionDBUtil(DataSource theDataSource) {
		dataSource=theDataSource;
		
	}
	
public List<Question> getQuestions() throws Exception {
		
		List<Question> questions = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		
			myConn = dataSource.getConnection();
			String sql = "select * from questions order by rand() limit 10";
			
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			
			while (myRs.next()) {
				
				
				int id = myRs.getInt("qid");
				String ques = myRs.getString("ques");
				String a = myRs.getString("a");
				String b = myRs.getString("b");
				String c = myRs.getString("c");
				String d= myRs.getString("d");
				String ans= myRs.getString("ans");
				
				Question tempQuestion = new Question(id,ques,a,b,c,d,ans);
				questions.add(tempQuestion);
			}
			
			return questions;		
		}
		finally {
			
			close(myConn, myStmt, myRs);
			}		
}

public int calculate_marks(List<Question>qList , List<String>ans)
{
	int marks=0;
	for(int i=0;i<10;i++)
	{
		Question q=qList.get(i);
		String s1=ans.get(i);
		String s2=q.getAns();
		if(s1==null)
			continue;
		if(s1.equals(s2))
			marks=marks+4;
		else
			marks=marks-1;
			
	}
	int percentage = (marks*100)/40;
	return percentage;
}
private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();   
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
	
 }

}
