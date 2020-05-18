package com.example.demo.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@AllArgsConstructor
public class SendGridMailer {

    private SendGridAPI sendGridAPI;

    public void sendMail(String emailcontent) {
        Email from = new Email("opasieczynska@gmail.com");
        String subject = "FILE UPDATE";
        Email to = new Email("opasieczynska@gmail.com");
        Content content = new Content("text/plain", emailcontent);
        Mail mail = new Mail(from, subject, to, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridAPI.api(request);
            System.out.println(response.getBody());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
