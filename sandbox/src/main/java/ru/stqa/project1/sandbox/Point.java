package ru.stqa.project1.sandbox;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Point {
    public double x;
    public double y;
    public Point (double x, double y) {
        this.x=x;
        this.y=y;
    }
    public double distance(double x, double y){
        double a = (x - this.x);
        double b = (y - this.y);
        BigDecimal s = BigDecimal.valueOf(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
        s = s.setScale(3,RoundingMode.HALF_UP);
        return s.doubleValue();
    }
}
