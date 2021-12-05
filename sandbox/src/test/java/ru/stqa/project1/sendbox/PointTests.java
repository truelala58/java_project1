package ru.stqa.project1.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.project1.sandbox.Point;

public class PointTests {

    @Test
    public void testAres(){
        Point p1 = new Point(-5, 10.5);
        Point p2 = new Point(16, - 3.8);
        Point p3 = new Point(1, 0);
        Point p4 = new Point(-3.33,  -115);
        Assert.assertEquals(p1.distance(p2),25.406);
        Assert.assertEquals(p3.distance(p4),115.081);
    }
}
