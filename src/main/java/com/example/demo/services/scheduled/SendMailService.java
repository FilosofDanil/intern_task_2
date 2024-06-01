package com.example.demo.services.scheduled;


import com.example.demo.component.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
    private MailSender mailSender;
    @Scheduled(fixedDelay = 10000)
    public void send(){
        mailSender.send("serhio3347@gmail.com", "Notification!", "Notification"
        );
    }
}
