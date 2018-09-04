package com.DS.utils.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.DS.utils.MailUtil;

public class TestMain_mail {

	public static void main(String[] args) throws ParseException {
		  Date d = new Date();
		  String test="2018-9-1";
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  long test1 = sdf.parse(test).getTime();
		  long test2=d.getTime();
		  long temp=test2-test1;
		  temp=temp/(1000 * 60 * 60 * 24);
		  System.out.println(temp);
		 
	}

}
