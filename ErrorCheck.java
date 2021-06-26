package numbers;

import static java.lang.System.out;

public class ErrorCheck {

    public static void printFirstArgumentError() {
        out.println("The first parameter should be a natural number or zero.\n");
    }
    public static void printSecondArgumentError() {
        out.println("The second parameter should be a natural number.\n");
    }

    public static boolean containsInvalidProperty(String[] propertyList) {
        for (String s : propertyList) {
            if (!Property.stringInPropertyList(s)) {
                return true;
            }
        }
        return false;
    }

    public static String getFalseProperties(String[] propertyList) {
        StringBuilder falseProperties = new StringBuilder();
        for (String s : propertyList) {
            if (!Property.stringInPropertyList(s)) {
                falseProperties.append(s + ", ");
            }
        }
        return falseProperties.substring(0, falseProperties.length() - 2);
    }

}
