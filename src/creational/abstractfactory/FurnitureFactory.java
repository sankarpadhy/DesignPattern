package creational.abstractfactory;

import creational.abstractfactory.furniture.Chair;
import creational.abstractfactory.furniture.Sofa;
import creational.abstractfactory.furniture.Table;

/**
 * Abstract Factory interface declaring creation methods for each distinct product
 */
public interface FurnitureFactory {
    Chair createChair();
    Table createTable();
    Sofa createSofa();
}
