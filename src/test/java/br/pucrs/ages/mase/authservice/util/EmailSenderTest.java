package br.pucrs.ages.mase.authservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.mail.host=127.0.0.1", "spring.mail.port=25" })
public class EmailSenderTest {

    @Autowired(required = true)
    private EmailSender emailSender;

    @Test
    public void shouldSendEmail() {
        System.out.println("HERE1");
        emailSender.sendSimpleMessage("guedes4g@hotmail.com", "Teste", "Funfou");
        System.out.println("HERE2");
    }
}
