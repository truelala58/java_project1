package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().list().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactModificationEditionDown(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeDown(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
       // app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationEditionUp(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeUp(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
   //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsDown(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideDown(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
   //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsUp(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideUp(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
     //   app.getSessionHelper().logout();
    }

}
