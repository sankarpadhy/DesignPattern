package creational.abstractfactory.furniture.victorian;

import creational.abstractfactory.furniture.Chair;

public class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on an ornate Victorian chair with carved wooden details");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}
