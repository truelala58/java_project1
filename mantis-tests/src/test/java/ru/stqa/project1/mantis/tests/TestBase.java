package ru.stqa.project1.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.stqa.project1.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config.inc.php"),"config.inc.php","config_bak.inc.php");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config.inc_bak.php","config.inc.php");
        app.stop();
    }
}
