package hello.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class JavaMailSenderController {
    private final JavaMailSenderComponent emailSender;

    @Autowired
    public JavaMailSenderController(JavaMailSenderComponent emailSender) {
        this.emailSender = emailSender;
    }

    @GetMapping("/test")
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1arxemond1@gmail.com");
        message.setTo("1arxemond1@gmail.com");
        message.setSubject("test");
        message.setText("hi");
        emailSender.getJavaMailSender().send(message);
    }
}
