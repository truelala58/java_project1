package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.project1.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
    public ContactHelper (WebDriver wd){
        super(wd);
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("email"),contactData.getEmail());
    }
}
