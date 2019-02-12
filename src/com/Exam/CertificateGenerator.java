package com.Exam;

import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.awt.*;


public class CertificateGenerator {

	private String name;
	private int marks;
	CertificateGenerator(String name,int marks) throws Exception
	{
		
		this.name=name;
		this.marks=marks;
		
	}
	public BufferedImage read() throws IOException
	{
	File fle=new File("C:\\Users\\SHREYA\\Desktop\\certificate.png");
	
	
	BufferedImage bimg=ImageIO.read(fle);
	
	Graphics pen=bimg.getGraphics();
	
	pen.setColor(Color.getHSBColor(0, 240, 60));
	
	pen.setFont(new Font("Segoe Script",Font.BOLD,50));
	
	pen.drawString(name,600,485);
	
	pen.drawString(Integer.toString(marks),750,821 );
	
	File tf=new File("C:\\Users\\SHREYA\\Desktop\\certificate1.png");
	
	
	
	
	ImageIO.write(bimg,"PNG",tf);
	
	return bimg;
	
	}
	
	
	
	
	
	
}
