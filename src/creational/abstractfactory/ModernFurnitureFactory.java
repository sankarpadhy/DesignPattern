package creational.abstractfactory;

import creational.abstractfactory.furniture.Chair;
import creational.abstractfactory.furniture.Sofa;
import creational.abstractfactory.furniture.Table;
import creational.abstractfactory.furniture.modern.ModernChair;
import creational.abstractfactory.furniture.modern.ModernSofa;
import creational.abstractfactory.furniture.modern.ModernTable;

/**
 * Concrete factory implementing modern furniture creation
 */
public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}
