package creational.factory;

public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike started: Pedaling...");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped.");
    }
}
