package creational.abstractfactory.furniture.modern;

import creational.abstractfactory.furniture.Table;

public class ModernTable implements Table {
    @Override
    public void putOn(String item) {
        System.out.println("Placing " + item + " on a glass and steel modern table");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}
