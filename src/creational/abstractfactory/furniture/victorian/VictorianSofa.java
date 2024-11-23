package creational.abstractfactory.furniture.victorian;

import creational.abstractfactory.furniture.Sofa;

public class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a luxurious Victorian sofa with tufted upholstery");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}
