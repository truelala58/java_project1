package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;

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
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
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

    public void deleteContactModify() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int count() {
        return  wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactForm();
        contactCache = null;
        returnToHomePage();
    }

    public void modifyHomeDown(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact);
        submitContactModificationDown();
        contactCache = null;
        returnToHomePage();
    }

    public void modifyHomeUp(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact);
        submitContactModificationUp();
        contactCache = null;
        returnToHomePage();
    }

    public void modifyInsideDown(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationDown();
        contactCache = null;
        returnToHomePage();
    }

    public void modifyInsideUp(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact);
        submitContactModificationUp();
        contactCache = null;
        returnToHomePage();
    }

    public void deleteHome(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactHomePage();
        contactCache = null;
    }

    public void deleteModInside(ContactData contact) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        deleteContactModify();
        contactCache = null;
    }

    public void deleteModeHome(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        deleteContactModify();
        contactCache = null;
    }
    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            String firstname = wd.findElement(By.xpath("//td[3]")).getText();
            String lastname = wd.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return new Contacts(contactCache);
    }
    public Contacts allPhones() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            String firstname = wd.findElement(By.xpath("//td[3]")).getText();
            String lastname = wd.findElement(By.xpath("//td[2]")).getText();
            String allPhones = wd.findElement(By.xpath("//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFormEditForm(ContactData contact){
        initContactModificationHomePageById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

}
