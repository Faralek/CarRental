package com.kodilla.rentalcars.emailService;

import com.kodilla.rentalcars.configuration.AdminConfig;
import com.kodilla.rentalcars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {

        int usersNumber = userRepository.findAll().size();

        String SUBJECT = "Tasks: once a day report";
        emailService.sendDailyMessage(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT,
                "Currently in your application u got " + usersNumber + " registered users"));
    }

}
