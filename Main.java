package numbers;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        client.greeting();

        while (true) {
            String[] inputList = client.getInput();
            switch (inputList.length) {
                case 0:
                    continue;
                case 1:
                    client.singleArgument(inputList);
                    break;
                default:
                    client.multipleArguments(inputList);
                    break;
            }
        }
    }
}




