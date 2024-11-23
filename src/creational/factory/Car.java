package creational.factory;

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started: Engine rumbling...");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped.");
    }
}
