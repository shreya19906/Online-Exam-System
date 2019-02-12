package com.Question;

public class Question {

	int id;
	 private String ques;
	 private String a;
	 private String b;
	 private String c;
	 private String d;
	 private String ans;
	 


	
	public int getId() {
		return id;
	}




	public String getQues() {
		return ques;
	}




	public String getA() {
		return a;
	}




	public String getB() {
		return b;
	}




	public String getC() {
		return c;
	}




	public String getD() {
		return d;
	}




	public String getAns() {
		return ans;
	}




	public Question(int id, String ques, String a, String b, String c, String d, String ans) {
		
		super();
		this.id=id;
		this.ques=ques;
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
		this.ans=ans;
	}

}
