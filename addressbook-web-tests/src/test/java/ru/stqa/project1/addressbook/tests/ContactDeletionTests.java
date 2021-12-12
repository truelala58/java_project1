package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletionHomePage(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactHomePage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionDetails(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().deleteContactModifiy();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionEdition(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().deleteContactModifiy();
        app.getSessionHelper().logout();
    }

}
