package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase{
    public ContactHelper (WebDriver wd){
        super(wd);
    }

    public void returnToHomePage() {
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

    public void initContactModificationHomePage(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }
    public void initContactDetails(int index) {
        wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
    }
    public void initContactModificationInside() {
        click(By.name("modifiy"));
    }

    public void submitContactModificationDown() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }
    public void submitContactModificationUp() {
        click(By.xpath("//div[@id='content']/form[1]/input[1]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContactHomePage() {
        String mainWindow = wd.getWindowHandle();
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.switchTo().window(mainWindow);
    }

    public void deleteContactModifiy() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactForm();
        returnToHomePage();
    }

    public void modifyHomeDown(int index, ContactData contact) {
        initContactModificationHomePage(index);
        fillContactForm(contact);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyHomeUp(int index, ContactData contact) {
        initContactModificationHomePage(index);
        fillContactForm(contact);
        submitContactModificationUp();
        returnToHomePage();
    }

    public void modifyInsideDown(int index, ContactData contact) {
        initContactDetails(index);
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyInsideUp(int index, ContactData contact) {
        initContactDetails(index);
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationUp();
        returnToHomePage();
    }

    public void deleteHome(int index) {
        selectContact(index);
        deleteContactHomePage();
    }

    public void deleteModInside(int index) {
        initContactDetails(index);
        initContactModificationInside();
        deleteContactModifiy();
    }

    public void deleteModeHome(int index) {
        initContactModificationHomePage(index);
        deleteContactModifiy();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            String firstname = wd.findElement(By.xpath("//td[3]")).getText();
            String lastname = wd.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
