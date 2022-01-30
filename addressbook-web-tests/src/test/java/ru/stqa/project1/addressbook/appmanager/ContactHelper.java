package ru.stqa.project1.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
    public void returnToGroupPage(int id) {
        wd.findElement(By.cssSelector("a[href='./?group=" + id + "'")).click();
    }
    public void goToHomePage() {
        wd.findElement(By.cssSelector("a[href='./']")).click();
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("phone2"),contactData.getPhone2());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
 //       attach(By.name("photo"),contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size()>0){
                Assert.assertTrue(contactData.getGroups().size()==1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
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

    public void create(ContactData contact,boolean creation) {
        fillContactForm(contact, creation);
        submitContactForm();
        returnToHomePage();
    }

    public void modifyHomeDown(ContactData contact,boolean creation) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact, creation);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyHomeUp(ContactData contact, boolean creation) {
        initContactModificationHomePageById(contact.getId());
        fillContactForm(contact, creation);
        submitContactModificationUp();
        returnToHomePage();
    }
    public void addGroupHome(ContactData contact,int id){
        goToHomePage();
        selectContactById(contact.getId());
        addContactInGroup(id);
    }

    public void addContactInGroup(int id) {
        Assert.assertTrue(isElementPresent(By.xpath("//select[@name='to_group']")));
        wd.findElement(By.xpath("//select[@name='to_group']//option[@value='" + id + "']")).click();
                    wd.findElement(By.name("add")).click();
                    goToHomePage();
        }
    public void removeGroupHome (ContactData contact, int id) {
        selectGroup(id);
        selectContactById(contact.getId());
        removeFromGroup(id);
    }

    public void removeFromGroup(int id) {
        click(By.name("remove"));
        returnToGroupPage(id);
    }

    public void selectGroup(int id) {
        goToHomePage();
        Assert.assertTrue(isElementPresent(By.xpath("//select[@name='group']")));
        wd.findElement(By.xpath("//select[@name='group']//option[@value='" + id + "']")).click();
            /*new Select(wd.findElement(By.xpath("//select[@name='group']")))
                    .selectByVisibleText(group.getName());*/
    }

    public void modifyInsideDown(ContactData contact, boolean creation) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact, creation);
        submitContactModificationDown();
        returnToHomePage();
    }

    public void modifyInsideUp(ContactData contact, boolean creation) {
        initContactDetailsById(contact.getId());
        initContactModificationInside();
        fillContactForm(contact,creation);
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
        deleteContactModify();
    }

    public void deleteModeHome(ContactData contact) {
        initContactModificationHomePageById(contact.getId());
        deleteContactModify();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return contacts;
    }

    public ContactData infoFormEditForm(ContactData contact){
        initContactModificationHomePageById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

}
