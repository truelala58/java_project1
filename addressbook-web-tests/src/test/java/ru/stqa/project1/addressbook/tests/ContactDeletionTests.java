package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletionHomePage(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactHomePage();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionDetails(){
        app.getContactHelper().initContactDetails();
        app.getContactHelper().initContactModificationInside();
        app.getContactHelper().deleteContactModifiy();
        app.getSessionHelper().logout();
    }
    @Test
    public void testContactDeletionEdition(){
        app.getContactHelper().initContactModificationHomePage();
        app.getContactHelper().deleteContactModifiy();
        app.getSessionHelper().logout();
    }

}
