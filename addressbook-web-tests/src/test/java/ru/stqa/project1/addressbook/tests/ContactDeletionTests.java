package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().list().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov"));
        }
    }

    @Test
    public void testContactDeletionHomePage(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().deleteHome(index);
        app.goTo().goToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before,after);
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionDetails(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().deleteModInside(index);
        app.goTo().goToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before,after);
        //   app.getSessionHelper().logout();
    }

    @Test
    public void testContactDeletionEdition(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().deleteModeHome(index);
        app.goTo().goToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before,after);
        //    app.getSessionHelper().logout();

    }

}
