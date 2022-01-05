package ru.stqa.project1.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getCountList();
    GroupData group = new GroupData("test2", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getCountList();
    Assert.assertEquals(after.size(),before.size() + 1);
    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    app.getSessionHelper().logout();
  }

}
