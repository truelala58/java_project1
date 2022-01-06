package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;
import java.util.Set;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Test").withLastname("Testov");
    app.goTo().contactPage();
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size()+1);
    contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
    //  app.getSessionHelper().logout();
  }

}
