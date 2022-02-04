package ru.stqa.project1.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.project1.mantis.model.UserData;
import ru.stqa.project1.mantis.model.Users;

public class UsersHelper extends HelperBase{

        public UsersHelper(ApplicationManager app) {
            super(app);
        }

        public void login (String username, String password){
            wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
            type(By.name("username"),username);
            click(By.cssSelector("input[value='Вход']"));
            type(By.name("password"),password);
            click(By.cssSelector("input[value='Вход']"));

        }

        public void userForChangePassword(String username) {

            Users users = app.db().users();
            Integer id = null;
            for (UserData user : users) {
                String userName = user.getUsername();
                if (userName.equals(username)) {
                    id = user.getId();
                }
            }
                click(By.cssSelector("a[href='/" + app.getProperty("web.domen") + "/manage_overview_page.php']"));
                click(By.cssSelector("a[href='/" + app.getProperty("web.domen") + "/manage_user_page.php']"));
                click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "'"));
                click(By.cssSelector("input[value='Сбросить пароль']"));

        }

        public void passwordChange(String confirmationLink, String password) {
            wd.get(confirmationLink);
            type(By.name("password"),password);
            type(By.name("password_confirm"),password);
            click(By.xpath("//button[@type='submit']"));
        }
    }

