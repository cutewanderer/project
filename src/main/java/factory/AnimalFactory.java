package factory; //фабрика, для того чтобы не создавать каждый раз объекты в майне

import animals.AbsAnimal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalTypeData;
import data.ColorData;

public class AnimalFactory {

    private String name = "";
    private int age = -1;
    private int weight = -1;
    private ColorData color = null;
    private int id = -1;
    private String type = "";

    public AnimalFactory(String type, String name, int age, int weight, ColorData colorData, int id) {

        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
        this.id = id;
        this.type = type;
    }

    public AbsAnimal create(AnimalTypeData animalTypeData) {
        switch (animalTypeData) {
            case DOG: {
                return new Dog(type,name,age,weight,color,id);
            }
            case CAT:{
                return new Cat(type,name,age,weight,color,id);
            }
            case DUCK:{
                return new Duck(type,name,age,weight,color,id);
            }
        }
        throw new RuntimeException(String.format("Animals %s не поддерживается", animalTypeData.name().toLowerCase()));
    }
}
