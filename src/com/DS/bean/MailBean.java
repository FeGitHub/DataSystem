package com.DS.bean;

public class MailBean {
	 private  String receiveMailAccount="";//接收者邮箱
	 private  String senderName="";//发送者名称
	 private  String receiveName="";//接收者姓名
	 private  String subject="";//邮件主题
	 private  String content="";//邮件内容
	 
	public MailBean(String receiveMailAccount,String content){
		 this.receiveMailAccount=receiveMailAccount;
		 this.content=content;
	 }
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveMailAccount() {
		return receiveMailAccount;
	}
	public void setReceiveMailAccount(String receiveMailAccount) {
		this.receiveMailAccount = receiveMailAccount;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
