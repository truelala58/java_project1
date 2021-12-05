package ru.stqa.project1.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupForm();
    returnToGroupPage();
    logout();
  }

}
