package creational.abstractfactory.furniture.victorian;

import creational.abstractfactory.furniture.Table;

public class VictorianTable implements Table {
    @Override
    public void putOn(String item) {
        System.out.println("Placing " + item + " on an elegant Victorian table with intricate patterns");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}
