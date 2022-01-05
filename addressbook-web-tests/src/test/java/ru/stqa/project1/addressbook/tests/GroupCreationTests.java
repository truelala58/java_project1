package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getCountList();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    List<GroupData> after = app.getGroupHelper().getCountList();
    Assert.assertEquals(after.size(),before.size() + 1);
    app.getSessionHelper().logout();
  }

}
