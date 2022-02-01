package ru.stqa.project1.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/mantisbt-2.25.2/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.xpath("//button[@type='submit']"));
       // click(By.cssSelector("input[value='Изменить пользователя']"));
    }
}
