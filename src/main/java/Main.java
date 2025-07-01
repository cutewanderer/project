import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import tools.EnumReturn;
import tools.NumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner input = new Scanner(System.in);
    static NumberValidator numberValidator = new NumberValidator();

    public static void main(String[] args) {

        List<AbsAnimal> animals = new ArrayList<>(); //хранение наследников

        List<String> commandNames = new ArrayList<>(); // хранение команд

        EnumReturn enumReturn = new EnumReturn();

        for(CommandData commandData: CommandData.values()) {
           commandNames.add(commandData.name().toLowerCase());
        }


        while (true) {
            System.out.println(String.format("Введите команду: %s", String.join("/", commandNames)));

            CommandData userMenu = (CommandData) enumReturn.getEnumFromString(
                    CommandData.class,
                    input,
                    commandNames,
                    "Неверная команда, повторите ввод."
            );

            switch (userMenu) {
                case ADD:{
                    List<String> animalTypeNames = new ArrayList<>();

                    for(AnimalTypeData animalTypeData: AnimalTypeData.values()) {
                        animalTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                   System.out.println(String.format("Введите тип животного: %s", String.join("/",animalTypeNames)));

                   AnimalTypeData animalTypeData = (AnimalTypeData) enumReturn.getEnumFromString(
                                AnimalTypeData.class,
                                input,
                                animalTypeNames,
                                "Неверная команда, повторите ввод."
                        );

                    System.out.println("Введите имя животного:");
                    String name = input.next();


                    int animalAge = getAnimalAgeWeight("Введите возраст животного:", "Вы ввели неверное значение. Повторите ввод");
                    int weightAnimal = getAnimalAgeWeight("Введите вес животного:", "Вы ввели неверное значение. Повторите ввод");

                    List<String> animalColor = new ArrayList<>();//хранение цвета
                    for(ColorData colorData: ColorData.values()) {
                        animalColor.add(colorData.name().toLowerCase());
                    }

                    System.out.println(String.format("Введите цвет животного: %s", String.join("/", animalColor)));

                    ColorData colorData = (ColorData) enumReturn.getEnumFromString(
                            ColorData.class,
                            input,
                            animalColor,
                            "Вы ввели неверный цвет. Повторите ввод."
                    );


                    AbsAnimal animal = new AnimalFactory(name, animalAge, weightAnimal, colorData).create(animalTypeData);
                    animals.add(animal);
                    break;
                }

                case LIST: {
                    for (AbsAnimal animal : animals) {
                        System.out.println(animal.toString());
                    }
                }
                    break;
                case EXIT: {
                    System.exit(0);
                }
            }
        }
    }
    private static int getAnimalAgeWeight(String message, String error) {
        while (true){
            System.out.println(message);
            String animalAgeInput = input.next();
            if (!numberValidator.isNumber(animalAgeInput)){
                System.out.println(error);
                continue;
            }
            return Integer.parseInt(animalAgeInput);
        }
    }

}
