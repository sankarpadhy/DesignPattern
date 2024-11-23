package creational.abstractfactory.furniture.modern;

import creational.abstractfactory.furniture.Sofa;

public class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a low-profile modern sofa");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}
