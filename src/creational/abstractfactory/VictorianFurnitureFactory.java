package creational.abstractfactory;

import creational.abstractfactory.furniture.Chair;
import creational.abstractfactory.furniture.Sofa;
import creational.abstractfactory.furniture.Table;
import creational.abstractfactory.furniture.victorian.VictorianChair;
import creational.abstractfactory.furniture.victorian.VictorianSofa;
import creational.abstractfactory.furniture.victorian.VictorianTable;

/**
 * Concrete factory implementing Victorian furniture creation
 */
public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}
