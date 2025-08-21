package animals.pets;

import animals.AbsAnimal;
import data.ColorData;

public class Cat extends AbsAnimal {
    public Cat(String type,String name, int age, int weight, ColorData colorData, int id) {
        super( name, age, weight, colorData,id, type);
    }

    public void say(){
        System.out.println("МЯУ!");
    }
}
