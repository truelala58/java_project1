package ru.stqa.project1.sandbox;

public class MyThirdProgram {


  public static void main(String[] args) {
    Point p1 = new Point(-5, 10.5);
    Point p2 = new Point(16, - 3.8);
    Point p3 = new Point(1, 0);
    Point p4 = new Point(-3.33,  -115);

    System.out.println("Расстояние между двумя точками" + " = " + p1.distance(p2));
    System.out.println("Расстояние между двумя точками" + " = " + p3.distance(p4));
  }
  public static double distance(Point p1, Point p2){
    double a = (p2.x-p1.x);
    double b = (p2.y-p1.y);
    return Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
  }
}