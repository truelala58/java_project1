package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.project1.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModificationHomePageById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }
    public void initContactDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
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

    public void modifyHomeDown(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyHomeUp(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact);
        submitContactModificationUp();
        returnToHomePage();
    }

    public void modifyInsideDown(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyInsideUp(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationUp();
        returnToHomePage();
    }

    public void deleteHome(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactHomePage();
    }

    public void deleteModInside(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        deleteContactModifiy();
    }

    public void deleteModeHome(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        deleteContactModifiy();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
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
