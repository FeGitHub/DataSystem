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
import java.util.Random;
/***
 * 
 * @author jeff
 * 发送邮件的工具类
 * 发送失败的原因很常是因为被判定成垃圾邮件(最好接收方和发送方都是同种类型的邮箱)
 * 本人用某易邮箱发某讯邮箱时常被某易拦截，发给同类型邮箱就不会 ╮(╯ ▽ ╰)╭
 * *********************************此邮件类暂时弃用*************用NewMailUtil*************
 */
public class MailUtil {
    public static String myEmailAccount = "*************";//发送方邮件地址
    public static String myEmailPassword = "*************";//客户端授权码
    public static String myEmailSMTPHost = "*************";
    public static String receiveMailAccount ="************";
    
    
    /****
     * 初始化基本配置信息
     * @param mailAccount
     */
    public static void initConfig(String mailAccount){
    	Prop p =PropKit.use("config.properties");
    	myEmailAccount=p.get("myEmailAccount");
    	myEmailPassword=p.get("myEmailPassword");
    	myEmailPassword=SecretUtil.decrypt(myEmailPassword);
    	myEmailSMTPHost=p.get("myEmailSMTPHost");
    	receiveMailAccount=mailAccount;
    }
    
    
    public static void sendMail(MailBean mail) throws Exception {   
    	initConfig(mail.getReceiveMailAccount());
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
    
        message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
        message.addRecipients(MimeMessage.RecipientType.CC,  InternetAddress.parse(myEmailAccount));
        message.addRecipients(MimeMessage.RecipientType.TO,  InternetAddress.parse(receiveMailAccount));
        message.setFrom(new InternetAddress(myEmailAccount, mail.getSenderName(), "UTF-8"));
       // message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, mail.getReceiveName(), "UTF-8"));
        message.setSubject(mail.getSubject(), "UTF-8");   
        message.setContent(mail.getContent(), "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    
    public  static void main(String[] args){
   	 Random random = new Random();
	 int randomNum = random.nextInt(1000000);
     String randomCode = String.format("%06d", randomNum);
     MailBean mail=new MailBean("XXXXXXXXX@qq.com","系统验证码");
     mail.setReceiveName("用户");
     mail.setSenderName("PAMS");
     mail.setContent("<h2>"+randomCode+"</h2>");  
     mail.setSubject("邮件主题");
    	try {
			sendMail(mail);
		} catch (Exception e) {		
			e.printStackTrace();
		}
    }
 

}