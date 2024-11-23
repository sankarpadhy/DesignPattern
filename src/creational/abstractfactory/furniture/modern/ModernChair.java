package creational.abstractfactory.furniture.modern;

import creational.abstractfactory.furniture.Chair;

public class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a sleek, minimalist modern chair");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}
