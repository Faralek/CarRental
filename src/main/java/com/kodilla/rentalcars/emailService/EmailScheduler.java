package com.kodilla.rentalcars.emailService;

import com.kodilla.rentalcars.configuration.AdminConfig;
import com.kodilla.rentalcars.repository.UserRepository;
import com.kodilla.rentalcars.webServices.GasPrice;
import com.kodilla.rentalcars.webServices.WeatherAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherAlert weatherAlert;
    @Autowired
    private GasPrice gasPrice;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() throws IOException {

        int usersNumber = userRepository.findAll().size();

        String SUBJECT = "Tasks: once a day report";
        emailService.sendDailyMessage(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT,
                usersNumber + " Active users" + " Current gas prices: " + gasPrice.getGasPrice() + "Weather alerts: " + weatherAlert.getWeatherAlert()));
    }

}
