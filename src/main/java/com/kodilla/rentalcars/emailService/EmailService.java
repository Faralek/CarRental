package com.kodilla.rentalcars.emailService;

import com.kodilla.rentalcars.configuration.AdminConfig;
import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private AdminConfig adminConfig;

    public void send(final Mail mail) {
        LOGGER.info("Start email preparation");
        try {
            javaMailSender.send(createSimpleMessage(mail));
            LOGGER.info("Email has been send");
        } catch (MailException e) {
            LOGGER.error("Fail to process email sending: ", e.getMessage(), e);
        }
    }

    public void sendDailyMessage(final Mail mail) {
        LOGGER.info("Start email preparation");
        try {
            javaMailSender.send(createDailyMessage(mail));
            LOGGER.info("Email has been send");
        } catch (MailException e) {
            LOGGER.error("Fail to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createDailyMessage(final Mail mail) {

        return dailyMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(dailyMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setCc(mail.getToCc());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public SimpleMailMessage createSimpleMessage(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getMailTo());
        message.setCc(mail.getToCc());
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        return message;
    }

    public void sendNewOrderInformationMail(List<Car> cars, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminConfig.getAdminMail());
        message.setCc("");
        message.setSubject("New order in your Application");
        message.setText("User " + user.getUsername() + " created new order for " + cars.stream().toString());
        javaMailSender.send(message);
    }

    public void sendDeleteOrderInformation(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminConfig.getAdminMail());
        message.setCc("");
        message.setSubject("Deleted Order");
        message.setText("User " + user.getUsername() + " deleted his order");
        javaMailSender.send(message);
    }
}
