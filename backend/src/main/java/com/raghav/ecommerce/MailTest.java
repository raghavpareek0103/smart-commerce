package com.raghav.ecommerce;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

public class MailTest {
    public static void main(String[] args) throws Exception {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("raghav2003pareek@gmail.com");
        sender.setPassword("djkxgsxbmqvfbbew");

        Properties p = sender.getJavaMailProperties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");

        sender.testConnection();
        System.out.println("LOGIN OK");
    }
}