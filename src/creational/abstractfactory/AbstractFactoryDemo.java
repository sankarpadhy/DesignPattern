package creational.abstractfactory;

import creational.abstractfactory.furniture.Chair;
import creational.abstractfactory.furniture.Sofa;
import creational.abstractfactory.furniture.Table;

/**
 * Demonstration of the Abstract Factory pattern
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // Create a modern furniture factory
        System.out.println("Creating modern furniture set:");
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        
        // Create modern furniture pieces
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();
        Sofa modernSofa = modernFactory.createSofa();

        // Use modern furniture
        modernChair.sitOn();
        modernTable.putOn("laptop");
        modernSofa.lieOn();
        System.out.println();

        // Create a Victorian furniture factory
        System.out.println("Creating Victorian furniture set:");
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        
        // Create Victorian furniture pieces
        Chair victorianChair = victorianFactory.createChair();
        Table victorianTable = victorianFactory.createTable();
        Sofa victorianSofa = victorianFactory.createSofa();

        // Use Victorian furniture
        victorianChair.sitOn();
        victorianTable.putOn("tea set");
        victorianSofa.lieOn();
    }
}
