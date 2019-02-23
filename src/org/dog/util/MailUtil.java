package org.dog.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {

    public static String sendMail(String mailAdress,String mailMsg) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true
        //props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1559407260@qq.com", "qcqtsljyldgdijif");//授权码
            }
        };

        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);


        try {
            message.setFrom(new InternetAddress("1559407260@qq.com")); // 设置发送者
        } catch (MessagingException e) {
            return "邮箱错误";
        }

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailAdress)); // 设置发送方式与接收者

        message.setSubject("测试信息");
        // message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

        message.setContent(mailMsg, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送

        Transport transport=session.getTransport();

        transport.connect();

        transport.sendMessage(message,message.getAllRecipients());

        transport.close();

        return "success";
    }

    public static void sendMailBySpring(String emailadress,String emailMsg){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true
        //props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        JavaMailSenderImpl sender=new JavaMailSenderImpl();
        sender.setDefaultEncoding("UTF-8");
        sender.setJavaMailProperties(props);
        sender.setUsername("1559407260@qq.com");
        sender.setPassword("qcqtsljyldgdijif");

        SimpleMailMessage m=new SimpleMailMessage();
        m.setFrom("1559407260@qq.com");
        m.setTo(emailadress);
        m.setSubject("测试信息");
        m.setText("动态码："+emailMsg);
        sender.send(m);
    }

}
