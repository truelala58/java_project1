package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletionHomePage(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContactHomePage();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionDetails(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactDetails(before.size() - 1);
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().deleteContactModifiy();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionEdition(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactModificationHomePage(before.size() - 1);
        app.getContactHelper().deleteContactModifiy();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }

}
