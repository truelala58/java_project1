package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().all().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactDeletionHomePage(){
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteHome(deletedContact);
        app.goTo().goToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before,after);
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionDetails(){
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteModInside(deletedContact);
        app.goTo().goToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before,after);
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionEdition(){
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteModeHome(deletedContact);
        app.goTo().goToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before,after);
        //    app.getSessionHelper().logout();

    }

}
