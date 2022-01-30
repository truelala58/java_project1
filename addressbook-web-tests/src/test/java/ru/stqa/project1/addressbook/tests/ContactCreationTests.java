package ru.stqa.project1.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;
import ru.stqa.project1.addressbook.model.GroupData;
import ru.stqa.project1.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
        String json = "";
        String line = reader.readLine();
        while (line != null) {
          json += line;
          line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
  }

    @Test(dataProvider = "validContactsFromJson", enabled = true)
    public void testCreationContactFromFile(ContactData contact) throws Exception {
     //   Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        app.contact().create(contact, false);
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
        //  app.getSessionHelper().logout();
    }

    @BeforeTest
    public void ensurePreconditions(){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test(enabled = false)
    public void testCreationContactAddGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData newContact = new ContactData().withFirstname("Test").withLastname("Testov")
                .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                .withWorkPhone("8(812)98737373").withHomePhone("435-8377").inGroup(groups.iterator().next());
        //   File photo = new File("src/test/resources/photo.png");
        app.goTo().contactPage();
        app.contact().create(newContact,true);
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
        //  app.getSessionHelper().logout();
    }

}
