package hello.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PropertySource("classpath:spring.mail.properties")
public class JavaMailSenderComponent {
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.transport.protocol}")
    private String mailTransportProtocol;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;
    @Value("${spring.mail.debug}")
    private String mailDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        /*Properties props = mailSender.getJavaMailProperties();
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.port", 587);
        props.put("mail.email", "my@gmail.com");
        props.put("mail.password", "mypass");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");*/

        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        return mailSender;
    }
}
