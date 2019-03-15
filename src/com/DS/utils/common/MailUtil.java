package com.DS.utils.common;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.DS.bean.MailBean;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import java.util.Date;
import java.util.Properties;
/***
 * 
 * @author jeff
 * 发送邮件的工具类
 *
 */
public class MailUtil {
    public static String myEmailAccount = "*************";//发送方邮件地址
    public static String myEmailPassword = "*************";//发送方密码
    public static String myEmailSMTPHost = "*************";
    public static String receiveMailAccount ="*************";
    
    
    public static void initConfig(MailBean mail){
    	Prop p =PropKit.use("config.properties");
    	myEmailAccount=p.get("myEmailAccount");
    	myEmailPassword=p.get("myEmailPassword");
    	myEmailSMTPHost=p.get("myEmailSMTPHost");
    	receiveMailAccount=mail.getReceiveMailAccount();
    }
    
    
    public static void sendMail(MailBean mail) throws Exception {   
    	initConfig(mail);
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        MimeMessage message = createMimeMessage(session,mail);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    /****
     * 封装邮件信息
     * @param session
     * @param sendMail
     * @param receiveMail
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session,MailBean mail) throws Exception {       
        MimeMessage message = new MimeMessage(session);     
        message.setFrom(new InternetAddress(myEmailAccount, mail.getSenderName(), "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, mail.getReceiveName(), "UTF-8"));
        message.setSubject(mail.getSubject(), "UTF-8");
        message.setContent(mail.getContent(), "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    
    public  static void main(String[] args){
    	MailBean mail=new MailBean("2498082473@qq.com","系统提示");
    	mail.setReceiveName("用户");
    	mail.setSenderName("系统");   	
    	mail.setContent("信息内容");    
    	try {
			sendMail(mail);
		} catch (Exception e) {		
			e.printStackTrace();
		}
    }
 

}