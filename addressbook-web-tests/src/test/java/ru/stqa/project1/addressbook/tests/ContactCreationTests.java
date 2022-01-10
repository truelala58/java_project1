package ru.stqa.project1.addressbook.tests;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo.png");
    ContactData contact = new ContactData().withFirstname("Test").withLastname("Testov").withPhoto(photo);
    app.goTo().contactPage();
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    //  app.getSessionHelper().logout();
  }

}
