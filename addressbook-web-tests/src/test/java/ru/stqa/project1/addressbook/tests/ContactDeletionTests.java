package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactDeletionHomePage(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteHome(deletedContact);
        app.goTo().goToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionDetails(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteModInside(deletedContact);
        app.goTo().goToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionEdition(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteModeHome(deletedContact);
        app.goTo().goToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        //    app.getSessionHelper().logout();

    }

}
