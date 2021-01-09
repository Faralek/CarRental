package com.kodilla.rentalcars.emailService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTestSuite {
    @InjectMocks
    EmailService emailService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //GIVEN
        Mail mail = new Mail("test@mail.com","", "test", "Example text");

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(mail.getMailTo());
        mailMessage.setCc(mail.getToCc());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //WHEN
        emailService.send(mail);

        //THEN
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
