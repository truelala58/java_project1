public class MyThirdProgram {


  public static void main(String[] args) {
    Point p1 = new Point(-5, 10.5);
    Point p2 = new Point(16, - 3.8);

    System.out.println("Расстояние между двумя точками" + " = " + distance(p1,p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.x-p1.x)+(p2.y-p1.y));
  }
}