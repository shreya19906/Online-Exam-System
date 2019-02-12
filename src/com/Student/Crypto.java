package com.Student;

public class Crypto {
	
	Crypto()
	{
		
	}
	
	public static String encrypt(String password)
	{
		char indx[]="shreya".toCharArray();
		char arr[]=password.toCharArray();
		StringBuffer Sbuff=new StringBuffer();
		int i=0;
		for(char a:arr)
		{
			if(i==indx.length)
				i=0;
			Sbuff.append(indx[i] ^ a);
		}
		password=Sbuff.toString();
		//System.out.println(password);
		return password;
	}
	
	public static String decrypt(String password)
	{
		char indx[]="shreya".toCharArray();
		char arr[]=password.toCharArray();
		StringBuffer Sbuff=new StringBuffer();
		int i=0;
		for(char a:arr)
		{
			if(i==indx.length)
				i=0;
			Sbuff.append(indx[i] ^ a);
		}
		password=Sbuff.toString();
		//System.out.println(password);
		return password;
	}

}
