package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactModificationEditionDown(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov")
                .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                .withWorkPhone("8(812)98737373").withHomePhone("435-8377");
        app.contact().modifyHomeDown(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        // app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationEditionUp(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov")
                .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                .withWorkPhone("8(812)98737373").withHomePhone("435-8377");
        app.contact().modifyHomeUp(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsDown(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov")
                .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                .withWorkPhone("8(812)98737373").withHomePhone("435-8377");
        app.contact().modifyInsideDown(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //     app.getSessionHelper().logout();
    }

    @Test
    public void testContactModificationDetailsUp(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Test").withLastname("Testov")
                .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                .withWorkPhone("8(812)98737373").withHomePhone("435-8377");
        app.contact().modifyInsideUp(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        //   app.getSessionHelper().logout();
    }

}
