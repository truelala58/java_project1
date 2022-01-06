package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().all().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactModificationEditionDown(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeDown(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
        // app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationEditionUp(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeUp(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsDown(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideDown(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsUp(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideUp(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
        //   app.getSessionHelper().logout();
    }

}
