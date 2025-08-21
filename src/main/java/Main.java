import animals.AbsAnimal;
import connector.MySqlConnector;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import database.AnimalTable;
import factory.AnimalFactory;
import tools.EnumReturn;
import tools.NumberValidator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Main {

    static Scanner input = new Scanner(System.in);
    static NumberValidator numberValidator = new NumberValidator();

    public static void main(String[] args) throws SQLException, IOException {

        List<AbsAnimal> animals = new ArrayList<>(); //хранение наследников

        List<String> commandNames = new ArrayList<>(); // хранение команд

        EnumReturn enumReturn = new EnumReturn();

        for(CommandData commandData: CommandData.values()) {
           commandNames.add(commandData.name().toLowerCase());
        }
        AnimalTable table = new AnimalTable("animal");
        MySqlConnector mySqlConnector = new MySqlConnector();

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
                    String type = String.valueOf(animalTypeData);
                    System.out.println("Введите имя животного:");
                    String name = input.next();
                    int id = 0;


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

                    AbsAnimal animal = new AnimalFactory(type, name, animalAge, weightAnimal, colorData, id)
                            .create(animalTypeData);

                    animals.add(animal);
                    Map<String, String> animalData = new HashMap<>();
                    animalData.put("type", animal.getType());
                    animalData.put("name", animal.getName());
                    animalData.put("age", String.valueOf(animal.getAge()));
                    animalData.put("weight",String.valueOf(animal.getWeight()));
                    animalData.put("color", animal.getColor().getName());

                    table.insert(animalData);
                    break;
                }
                case UPDATE: {
                    System.out.println("Укажите колонку и новое значение:");
                    Map<String, String> changeableValue = new HashMap<>();
                    changeableValue.put(input.next(), input.next());

                    System.out.println("Укажите условие (колонка и значение):");
                    Map<String, String> valueWhere = new HashMap<>();
                    valueWhere.put(input.next(), input.next());

                    table.update(changeableValue ,valueWhere);
                  System.out.println("Запись успешно обновлена!");

                    break;
                }

                case LIST: {
                    for (AbsAnimal animal : animals) {
                        System.out.println(animal.toString());
                    }
                  ResultSet result = table.select();
                  System.out.printf("%-3s %-15s %-15s %-3s %-6s %-15s%n", "id", "type", "name", "age", "weight", "color");
                  System.out.println("---------------------------------------------------");
                    while (result.next()) {
                      System.out.printf("%-3s %-15s %-15s %-3s %-3s %-15s%n",
                              result.getInt("id"),
                              result.getString("type"),
                              result.getString("name"),
                              result.getInt("age"),
                              result.getInt("weight"),
                              result.getString("color")
                      );
                  }
                  System.out.println("Введите тип животного: cat, dog, duck:");
                  String type = input.next();
                  try (ResultSet resultByType = table.selectFilterByType(type)){
                    System.out.printf("%-3s %-15s %-15s %-3s %-6s %-15s%n", "id", "type", "name", "age", "weight", "color");
                    System.out.println("---------------------------------------------------");
                    while (resultByType.next()) {
                      System.out.printf("%-3s %-15s %-15s %-3s %-3s %-15s%n",
                              resultByType.getInt("id") ,
                                      resultByType.getString("name"),
                                      resultByType.getString("color"),
                                      resultByType.getInt("age"),
                                      resultByType.getString("type"),
                                      resultByType.getInt("weight")
                      );
                    }
                  }


                }
                    break;
                case EXIT: {
                  mySqlConnector.closeConnection();
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
