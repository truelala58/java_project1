package ru.stqa.project1.addressbook;

import org.testng.annotations.*;


public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectgroup();
    selectedGroups();
    returnToGroupPage();
  }

}
