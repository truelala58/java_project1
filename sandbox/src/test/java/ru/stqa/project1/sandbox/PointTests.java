package ru.stqa.project1.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance(){
        Point p1 = new Point(-5, 10.5);
        Point p2 = new Point(16, - 3.8);
        Assert.assertEquals(p1.distance(p2.x,p2.y),2.588,"Расстояние рассчитано неверно");
        Assert.assertTrue(p1.distance(p2.x,p2.y)>=0,"Расстояние не может быть отрицательным");
    }
}
