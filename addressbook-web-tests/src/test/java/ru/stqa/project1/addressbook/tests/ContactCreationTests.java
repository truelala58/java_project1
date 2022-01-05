package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    List<ContactData> before = app.getContactHelper().getCountList();
    app.goTo().goToContactPage();
    ContactData contact = new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactForm();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getCountList();
    Assert.assertEquals(after.size(),before.size()+1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  //  app.getSessionHelper().logout();
  }

}
