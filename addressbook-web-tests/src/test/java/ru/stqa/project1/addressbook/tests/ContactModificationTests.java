package ru.stqa.project1.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeDown(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        // app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationEditionUp(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyHomeUp(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsDown(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideDown(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsUp(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov").withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru");
        app.contact().modifyInsideUp(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //   app.getSessionHelper().logout();
    }

}
