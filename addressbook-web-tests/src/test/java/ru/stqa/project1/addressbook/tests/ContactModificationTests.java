package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModificationEditionDown(){
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationEditionUp(){
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsDown(){
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsUp(){
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
}
