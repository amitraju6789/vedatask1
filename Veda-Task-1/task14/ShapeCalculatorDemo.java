package task14;

// Abstract Parent Class
abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    // Abstract methods to be implemented by child classes
    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public void displayInfo() {
        System.out.println("-----------------------------------------");
        System.out.println("Shape Name      : " + name);
        System.out.printf("Calculated Area : %.2f\n", calculateArea());
        System.out.printf("Perimeter       : %.2f\n", calculatePerimeter());
    }
}

// Circle Implementation
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Rectangle Implementation
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        super("Rectangle");
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

// Triangle Implementation (Scalene/General using 3 sides for perimeter and heron's formula for area)
class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        super("Triangle");
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculateArea() {
        // Using Heron's Formula
        double s = calculatePerimeter() / 2.0;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }
}

public class ShapeCalculatorDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("     SHAPE CALCULATOR USING ABSTRACTION  ");
        System.out.println("=========================================");

        // Polymorphic array of abstract Shape references
        Shape[] shapes = {
            new Circle(7.0),
            new Rectangle(10.5, 4.0),
            new Triangle(3.0, 4.0, 5.0)
        };

        for (Shape shape : shapes) {
            shape.displayInfo();
        }

        System.out.println("=========================================");
        System.out.println("Shape calculations executed successfully!");
    }
}