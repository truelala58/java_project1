package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

  @Test
  public void testCreationContact() throws Exception {
    app.goToContactPage();
    app.fillContactForm(new ContactData("Test", "Testov", "Test city, Test street, 1", "+79211234567", "test@mail.ru"));
    app.submitContactForm();
    app.returnToContactPage();
    app.logout();
  }

}