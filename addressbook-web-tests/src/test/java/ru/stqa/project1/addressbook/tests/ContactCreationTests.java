package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Test").withLastname("Testov");
    app.goTo().contactPage();
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    //  app.getSessionHelper().logout();
  }

}
