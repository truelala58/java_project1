package ru.stqa.project1.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.project1.addressbook.model.GroupData;
import ru.stqa.project1.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(),equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    //  app.getSessionHelper().logout();
  }

}
