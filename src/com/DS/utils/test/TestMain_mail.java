package com.DS.utils.test;

import com.DS.utils.MailUtil;

public class TestMain_mail {

	public static void main(String[] args) {
		try {
			MailUtil.sendMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
