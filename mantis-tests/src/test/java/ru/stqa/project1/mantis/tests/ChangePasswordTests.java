package ru.stqa.project1.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.project1.mantis.model.MailMessage;
import ru.stqa.project1.mantis.model.UserData;
import ru.stqa.project1.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @BeforeTest
    public void ensurePreconditions () throws MessagingException, IOException {
        if (app.db().users().size()==0){
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String password = "password";
            String email = String.format("user%s@localhost.localdomain",now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages =  app.mail().waitForMail(2,10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(user,password));
        }
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String passwordNew = String.format("password%s", now);
        app.user().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Users users = app.db().users();
        UserData user = users.iterator().next();
        String email = user.getEmail();
        String userName = user.getUsername();
        app.user().userForChangePassword(userName);
        List<MailMessage> mailMessagesNew =  app.mail().waitForMail(1,10000);
        String confirmationLinkNew = findConfirmationLink(mailMessagesNew, email);
        app.user().passwordChange(confirmationLinkNew, passwordNew);
        assertTrue(app.newSession().login(userName,passwordNew));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)-> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
