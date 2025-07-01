package animals;

import data.ColorData;

public abstract class AbsAnimal {

    private String name = "";

    private int age = -1;

    private int weight = -1;

    private ColorData color = null;

    public AbsAnimal(String name, int age, int weight, ColorData colorData) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public ColorData getColor() {
        return color;
    }

    public void say(){
        System.out.println("Я говорю!");
    }

    public void go(){
        System.out.println("Я иду!");
    }

    public void drink(){
        System.out.println("Я пью!");
    }
    public void eat(){
        System.out.println("Я ем!");
    }
    public String toString() {
        return String.format("Привет, меня зовут - " + name + ", мне - " + age + getYearPadej() + ", я вешу - " + weight + " кг, мой цвет - " + color.getName());
    }

    private String getYearPadej() {
        if(age >= 11 && age <= 14) {
            return " лет";
        }
        int ostatok = age % 10;
        if(ostatok == 0) {
            return " лет";
        }

        if(ostatok == 1) {
            return " год";
        }

        if(ostatok >= 2 && ostatok <= 4) {
            return " года";
        }
        return " лет";
    }
}
