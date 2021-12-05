package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.*;


public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectedGroup();
    app.deletedGroup();
    app.returnToGroupPage();
  }

}
