public class MyThirdProgram {


  public static void main(String[] args) {
    Point p1 = new Point(-5, 10.5);
    Point p2 = new Point(16, - 3.8);

    System.out.println("Расстояние между двумя точками" + " = " + p1.distance(p2.x,p2.y));
  }
}