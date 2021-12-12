package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModificationEditionDown(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationEditionUp(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsDown(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsUp(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToContactPage();
        app.getSessionHelper().logout();
    }
}
