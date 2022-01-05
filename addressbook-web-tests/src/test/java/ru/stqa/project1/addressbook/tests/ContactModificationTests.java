package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModificationEditionDown(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactModificationHomePage(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationEditionUp(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactModificationHomePage(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsDown(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactDetails(before.size() - 1);
        app.getContactHelper().initContactModificationInside();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModificationDown();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactModificationDetailsUp(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().contactCreation(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getCountList();
        app.getContactHelper().initContactDetails(before.size() - 1);
        app.getContactHelper().initContactModificationInside();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModificationUp();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getCountList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        app.getSessionHelper().logout();
    }
}
