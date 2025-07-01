package tools;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Enum.valueOf;

public class EnumReturn {

    public <E extends Enum> Enum getEnumFromString(Class<E> enumClass, Scanner input, List<String> names, String error){

        String consoleValue = "";
        names = names.stream().map(name -> name.trim().toUpperCase()).collect(Collectors.toList());
        while (true){
            consoleValue = input.next().trim().toUpperCase();

            if (names.contains(consoleValue)){
                break;
            }
            System.out.println(error);
        }

        return Enum.valueOf(enumClass, consoleValue);
    }
}
