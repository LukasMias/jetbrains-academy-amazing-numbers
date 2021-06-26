package numbers;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.out;

public class Client {

    public void singleArgument(String[] inputList) {
        Number num = new Number(parseLong(inputList[0]));

        if (num.value < 0) {
            ErrorCheck.printFirstArgumentError();
        }
        else if (num.value == 0) {
            out.println("Goodbye!");
            System.exit(0);
        } else {
            out.printf("Properties of %d\n", num.value);
            for (Property property : Property.values()) {
                out.printf("%s: %b\n", property, num.hasProperty(property));
            }
        }
    }


    public void multipleArguments(String[] inputList) {
        long startNum = parseLong(inputList[0]);
        int iterationCount = parseInt(inputList[1]);
        String[] propertyList = getInputProperties(inputList);

        if (noErrors(startNum, iterationCount, propertyList)) {
            int propertyCounter = 0;
            int loopCounter = 0;
            if(propertyList.length == 0) {
                for(int i = 0; i < iterationCount; i++) {
                    Number num = new Number(startNum + i);
                    num.printProperties();
                }
                return;
            }
            while (propertyCounter < iterationCount) {
                Number num = new Number(startNum + loopCounter);
                boolean hasAllProperties = true;
                for(String propertyString : propertyList) {
                    //make caps so the enum understands it
                    String propertyInCaps = propertyString.toUpperCase();
                    if(propertyInCaps.charAt(0) == '-') {
                        String negatedInCaps = propertyInCaps.substring(1, propertyString.length());
                        if (num.hasProperty(Property.valueOf(negatedInCaps))) {
                            hasAllProperties = false;
                        }
                    } else if (!num.hasProperty(Property.valueOf(propertyInCaps))) {
                            hasAllProperties = false;
                    }
                }
                if(hasAllProperties) {
                    propertyCounter++;
                    num.printProperties();
                }
                loopCounter++;
            }
        }
    }

    public boolean noErrors(long startNum, int iterationCount, String[] propertyList) {

        if (startNum < 0) {
            ErrorCheck.printFirstArgumentError();
            return false;
        }
        if (iterationCount <= 0) {
            ErrorCheck.printSecondArgumentError();
            return false;
        }
        if (ErrorCheck.containsInvalidProperty(propertyList)) {
            String falseProperties = ErrorCheck.getFalseProperties(propertyList);
            if(falseProperties.split(" ").length == 1) {
                out.println("The property [" + falseProperties+ "] is wrong.");
            } else {
                out.println("The properties [" + falseProperties + "] are wrong.");
            }
            Property.printAvailableProperties();
            return false;
        }
        if (Property.containsMutuallyExclusive(propertyList)) {
            String[] contradictionList = Property.getMutuallyExclusive(propertyList);
            out.println("The request contains mutually exclusive properties: ["
                    + contradictionList[0]
                    + ", "
                    + contradictionList[1]
                    + "]");
            return false;
        }
        return true;
    }


    public void greeting() {
        out.println("Welcome to Amazing Numbers!\n");
        out.println("Supported requests:\n"
                + "- enter a natural number to know its properties;\n"
                + "- enter two natural numbers to obtain the properties of the list:\n"
                + " * the first parameter represents a starting number;\n"
                + " * the second parameter shows how many consecutive numbers are to be printed;\n"
                + "- two natural numbers and properties to search for;\n"
                + "- a property preceded by minus must not be present in numbers;\n"
                + "- separate the parameters with one space;\n"
                + "- enter 0 to exit.");
    }

    public String[] getInput() {
        Scanner scanner = new Scanner(System.in);
        out.println("Enter a request:");
        String input = scanner.nextLine();

        String[] inputList = input.split(" ");
        for(int i = 0; i < inputList.length; i++) {
            inputList[i] = inputList[i].toLowerCase();
        }
        return inputList;

    }

    public static String[] getInputProperties(String[] inputList) {
        String[] inputProperties = new String[inputList.length - 2];
        for (int i = 2; i < inputList.length; i++) {
            inputProperties[i - 2] = inputList[i].toLowerCase();
        }
        return inputProperties;
    }
}
