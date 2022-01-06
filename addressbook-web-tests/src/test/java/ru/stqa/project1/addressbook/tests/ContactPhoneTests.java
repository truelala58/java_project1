package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().allPhones().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov")
                    .withHomePhone("+7(912)1234567").withMobilePhone("8(921)765-4321").withWorkPhone("5435627"));
        }
    }

    @Test
    public void testContactPhone() {
        ContactData contact = app.contact().allPhones().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFormEditForm)));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
               .stream().filter((s)-> ! s.equals(""))
               .map(ContactPhoneTests::cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
