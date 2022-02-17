//package server.admin.utils;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//@Component
//public class EmailSender {
//    private final String SENDER = "lily9903@naver.com";
//    private String PASSWORD = "pa1ep!nk9812^^";
//    private final String SUBJECT = "PLAV - 관리자가입 인증번호 발급";
//    private final String HOST = "smtp.naver.com";
//
//    private Properties setSMTPProperties() {
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.port", 465); //ssl=465 tsl=587
//        props.put("mail.smtp.host", HOST);
//        props.put("mail.smtp.ssl.trust", HOST);
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        return props;
//    }
//
//    private Session getInstance(){
//        Session session = Session.getInstance(setSMTPProperties(), new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(SENDER, PASSWORD);
//            }
//        });
//        return session;
//    }
//
//    private String setText(String verficationCode){
//        final String logoImageUri = "https://cdn.plav.io/origin/64bc8b00-f566-4e28-ab93-bbbeb8133000.png";
//        final String imageHtml = String.format("<img src=\"%s\"> <h1>관리자가입 인증번호</h1>", logoImageUri);
//        return String.join(
//                System.getProperty("line.separator"),
//                imageHtml,
//                "<h3>인증번호는 ",
//                verficationCode,
//                " 입니다.</h3>"
//        );
//    }
//
//    public void sendEmail(String verificationCode, String email) throws MessagingException {
//        Session session = getInstance();
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(SENDER));
//        message.setRecipients(
//                Message.RecipientType.TO, InternetAddress.parse(email));
//        message.setSubject(SUBJECT);
//
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//        mimeBodyPart.setContent(setText(verificationCode), "text/html; charset=utf-8");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(mimeBodyPart);
//
//        message.setContent(multipart);
//
//        try {
//
//            System.out.println("Sending...");
//            Transport.send(message);
//            System.out.println("Email sent!");
//        } catch (AddressException ex) {
//            System.out.println(ex.getMessage());
//            throw new MessagingException("Invalid Address!");
//        } catch (MessagingException ex){
//            System.out.println(ex.getMessage());
//            throw new MessagingException("Email sent Fail");
//        }
//    }
//
//
//
//}
