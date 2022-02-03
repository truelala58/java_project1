package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private final Properties properties;

    private ContactHelper contactHelper;
    private  SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser)  {
        this.browser=browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));
        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
    }

    public void stop() {wd.quit();
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
    public DbHelper db(){
        return dbHelper;
    }
}
