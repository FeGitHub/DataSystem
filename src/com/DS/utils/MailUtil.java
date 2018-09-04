package com.DS.utils;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
/***
 * 
 * @author jeff
 * 发送邮件的工具类
 *
 */
public class MailUtil {
    public static String myEmailAccount = "*******";
    public static String myEmailPassword = "*******";
    public static String myEmailSMTPHost = "*******";
    public static String receiveMailAccount = "*******";
    public static void sendMail() throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {       
        MimeMessage message = new MimeMessage(session);     
        message.setFrom(new InternetAddress(sendMail, "15软工测试", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "用户", "UTF-8"));
        message.setSubject("测试主题", "UTF-8");
        message.setContent("测试成功", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
 

}