
package com.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {

	private DataSource dataSource;

	public StudentDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "insert into student "
					   + "(firstname, lastname, email,pwd) "
					   + "values (?, ?, ?,?)";
			
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getFirstname());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4, theStudent.getPwd());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	public boolean  login_authentication(Student theStudent) throws SQLException {
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
		myConn = dataSource.getConnection();
		
		String emailid=theStudent.getEmail();
	
		String sql = "select * from student where email='"+emailid+"'";
		
		
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);
		//int i=0;
		//while(myRs.next()) {
			//System.out.println(i);
		//}
		
				
		if(!myRs.next())
			return false;
		else {
			if(theStudent.getPwd().equals(myRs.getString("pwd")))
			{
				theStudent.setFirstname(myRs.getString("firstname"));
				theStudent.setLastname(myRs.getString("lastname"));
				
				//System.out.println(theStudent.getFirstname()+"  "+ theStudent.getLastname());

				
				return true;
			}
			else
				return false;
			
		}
			
		}
		finally {
			close(myConn, myStmt, myRs);
			
		}
		
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







