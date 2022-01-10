package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().all().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov")
                    .withAddress("Test city, Test street, 1"));
        }
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAddress(),equalTo(cleaned(contactInfoFormEditForm.getAddress())));
    }


    public static String cleaned(String address) {
        String[] adr = address.trim().split("\n");
        List<String> newAdr = new ArrayList<>();
            for (String a:adr){
                String b = a.trim();
                newAdr.add(b);
            }
        return String.join("\n", newAdr).replaceAll("\\s{2,}"," ");
    }

}
