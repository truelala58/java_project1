package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInternalsTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().all().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov")
                    .withHomePhone("+7(912)1234567").withMobilePhone("8(921)765-4321").withWorkPhone("5435627")
                    .withPhone2("8-812-123455").withEmail("test@mail.ru").withEmail2("test2@mail.ru")
                    .withEmail3("test3@mail.ru").withAddress("Test city, Test street, 1"));
        }
    }

    @Test
    public void testContactInternals() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFormEditForm)));
        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFormEditForm)));
        assertThat(contact.getAddress(),equalTo(cleanedAddresses(contactInfoFormEditForm.getAddress())));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone(),contact.getPhone2())
               .stream().filter((s)-> ! s.equals(""))
               .map(ContactInternalsTests::cleanedPhones)
               .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone){
        return phone.replaceAll("\\s","")
                .replaceAll("[-()]","");
    }
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail().trim(),contact.getEmail2().trim(),contact.getEmail3().trim())
                .stream().filter((s)-> ! s.equals(""))
                .map(ContactInternalsTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedEmails(String email){
        return email.replaceAll("\\s{2,}"," ");
    }
    public static String cleanedAddresses(String address) {
        String[] adr = address.trim().split("\n");
        List<String> newAdr = new ArrayList<>();
        for (String a:adr){
            String b = a.trim();
            newAdr.add(b);
        }
        return String.join("\n", newAdr).replaceAll("\\s{2,}"," ");
    }
}
