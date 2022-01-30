package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.project1.addressbook.model.ContactData;
import ru.stqa.project1.addressbook.model.Contacts;
import ru.stqa.project1.addressbook.model.GroupData;
import ru.stqa.project1.addressbook.model.Groups;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInOutGroups extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            String name = UUID.randomUUID().toString();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(name).withHeader("test2").withFooter("test3"));

        }
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Testov")
                    .withAddress("Test city, Test street, 1").withMobilePhone("+79211234567").withEmail("test@mail.ru")
                    .withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhone2("+79211234567")
                    .withWorkPhone("8(812)98737373").withHomePhone("435-8377"), false);
        }
    }

    @Test
    public void testModifyContactAddGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        Groups groupsForAdd = getGroupForAdd(modifiedContact, groups);;
        app.contact().addGroupHome(modifiedContact, groupsForAdd.iterator().next());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(modifiedContact.inGroup(groupsForAdd.iterator().next()))));
    }

    @Test
    public void testModifyContactRemoveGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        Groups groupsForRemove = getGroupForRemove(contacts,groups);
        GroupData groupForRemove = groupsForRemove.iterator().next();
        app.contact().selectGroup(groupForRemove);
        Contacts before = app.contact().all();
        ContactData modifiedContactWithGroups = before.iterator().next();
        app.contact().removeGroupHome(modifiedContactWithGroups,groupForRemove.getId());
        Groups afterModifiedGroups = modifiedContactWithGroups.getGroups();
        assertThat(afterModifiedGroups, equalTo(groupsForRemove.without(groupsForRemove.iterator().next())));
    }

    public Groups getGroupForAdd(ContactData contact, Groups groups) {
        Groups groupsForAdd = new Groups();
        for (GroupData group : groups) {
            if (!contact.getGroups().contains(group)) {
                groupsForAdd.add(group);
            }
        }
        if (groupsForAdd.size() == 0) {
            String name = UUID.randomUUID().toString();
            app.goTo().groupPage();
            GroupData group = new GroupData().withName(name).withHeader("test2").withFooter("test3");
            app.group().create(group);
            Groups groupsDbNew = app.db().groups();
            groupsForAdd.add(group.withId(groupsDbNew.stream().mapToInt(GroupData::getId).max().getAsInt()));
        }
        return groupsForAdd;
    }

    public Groups getGroupForRemove(Contacts contacts, Groups groups) {
        Groups groupsForRemove = new Groups();
        for (ContactData contact: contacts){
            if (contact.getGroups().size()>0){
                groupsForRemove.add(contact.getGroups().iterator().next());
            }
        }
        if (groupsForRemove.size() == 0 & groups.size()>0) {
                ContactData modifiedContact = contacts.iterator().next();
                app.contact().addGroupHome(modifiedContact, groups.iterator().next());
                groupsForRemove.add(groups.iterator().next());

        } 
        return groupsForRemove;
    }

}


