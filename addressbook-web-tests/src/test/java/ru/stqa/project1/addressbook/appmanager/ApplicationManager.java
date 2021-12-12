package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    private ContactHelper contactHelper;
    private  SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser=browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())){
            wd = new FirefoxDriver();
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {wd.quit();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
