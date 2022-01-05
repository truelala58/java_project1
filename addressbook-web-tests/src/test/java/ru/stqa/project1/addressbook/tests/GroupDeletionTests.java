package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.GroupData;


public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before-1);
    app.getSessionHelper().logout();
  }

}
