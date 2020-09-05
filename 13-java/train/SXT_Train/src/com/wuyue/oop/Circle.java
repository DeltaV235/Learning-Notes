package com.wuyue.oop;

public class Circle {
    private double radius;

    public Circle() {
        this.radius = 10;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public void show() {
        System.out.printf("Area is %.2f\n", getArea());
        System.out.printf("Perimeter is %.2f\n", getPerimeter());
    }

    private class Cylinder {
        private double height;

        private Cylinder() {
            this.height = 10;
        }

        private Cylinder(double height) {
            this.height = height;
        }

        private double getVolume() {
            return getArea() * this.height;
        }

        private void showVolume() {
            System.out.printf("The Cylinder Volume is %.2f\n", getVolume());
        }
    }

    public static void main(String[] args) {
        Circle circle = new Circle(3.4);
        Circle.Cylinder cylinder = circle.new Cylinder(29);
        circle.show();
        cylinder.showVolume();

        new Circle().new Cylinder().showVolume();
    }
}
