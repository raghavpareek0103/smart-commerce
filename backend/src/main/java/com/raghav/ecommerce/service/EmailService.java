package com.raghav.ecommerce.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationOtpEmail(
            String userEmail,
            String otp,
            String subject,
            String text) throws MessagingException {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Sender
            helper.setFrom("aaravsharam@gmail.com");

            // Receiver
            helper.setTo(userEmail);

            // Subject
            helper.setSubject(subject);

            // Email body
            helper.setText(
                    "<h2>Shopzy Verification</h2>" +
                            "<p>" + text + "</p>" +
                            "<h1>" + otp + "</h1>" +
                            "<p>This OTP is valid for verification.</p>",
                    true
            );

            // Send email
            javaMailSender.send(mimeMessage);

            System.out.println("=================================");
            System.out.println("OTP EMAIL SENT SUCCESSFULLY");
            System.out.println("TO: " + userEmail);
            System.out.println("OTP: " + otp);
            System.out.println("=================================");

        } catch (MailException e) {

            System.out.println("=================================");
            System.out.println("OTP EMAIL FAILED");
            System.out.println("TO: " + userEmail);
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("=================================");

            e.printStackTrace();

            throw new MailSendException("Failed to send OTP email", e);
        }

    }
}