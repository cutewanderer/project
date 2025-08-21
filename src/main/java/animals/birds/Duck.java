package animals.birds;

import animals.AbsAnimal;
import data.ColorData;

public class Duck extends AbsAnimal implements IFlying {
    public Duck(String type, String name, int age, int weight, ColorData colorData, int id) {
        super( name, age, weight, colorData, id, type);
    }

    public void fly() {
        System.out.println("Я лечу");
    }
    public void say(){
        System.out.println("КРЯ!");
    }
}
