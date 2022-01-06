package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Test").withLastname("Testov");
    app.goTo().contactPage();
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size()+1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  //  app.getSessionHelper().logout();
  }

}
