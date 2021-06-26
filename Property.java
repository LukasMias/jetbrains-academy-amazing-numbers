package numbers;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public enum Property  {
    BUZZ,
    DUCK,
    EVEN,
    GAPFUL,
    JUMPING,
    ODD,
    PALINDROMIC,
    SPY,
    SQUARE,
    SUNNY,
    HAPPY,
    SAD;

    public static boolean stringInPropertyList(String string) {
        string = string.toUpperCase();
        for(Property property : Property.values()) {
            if (string.equals(property.name()) || string.equals("-" + property.name()) ) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsMutuallyExclusive(String[] propertyArray) {

        List<String> propertyList = Arrays.asList(propertyArray);

        //todo: make all of this case-invariant, e.g. write case-independent "contains"
        //todo: same with getMutuallyExclusive
        for(Property[] contraList : hardCodedContradictions()) {
            String contradiction1LowerCase = contraList[0].name().toLowerCase();
            String contradiction2LowerCase  = contraList[1].name().toLowerCase();
            if(propertyList.contains(contradiction1LowerCase)
                    && propertyList.contains(contradiction2LowerCase)) {
                return true;
            }
            if(propertyList.contains("-" + contradiction1LowerCase)
                    && propertyList.contains("-" + contradiction2LowerCase)) {
                return true;
            }
        }

        for(Property property : Property.values()) {
            String propertyNameLowercase = property.name().toLowerCase();
            if(propertyList.contains(propertyNameLowercase) && propertyList.contains("-" + propertyNameLowercase)) {
                return true;
            }
        }
        return false;
    }

    public static String[] getMutuallyExclusive(String[] propertyArray) {
        List propertyList = Arrays.asList(propertyArray);
        String[] contradictionsInInput = new String[2];

        for(Property[] contraList : hardCodedContradictions()) {
            String contradiction1LowerCase = contraList[0].name().toLowerCase();
            String contradiction2LowerCase = contraList[1].name().toLowerCase();
            if (propertyList.contains(contradiction1LowerCase)
                    && propertyList.contains(contradiction2LowerCase)) {
                contradictionsInInput[0] = contradiction1LowerCase;
                contradictionsInInput[1] = contradiction2LowerCase;
                return contradictionsInInput;
        } else if (propertyList.contains("-" + contradiction1LowerCase)
                    && propertyList.contains("-" + contradiction2LowerCase)) {
                contradictionsInInput[0] = "-" + contradiction1LowerCase;
                contradictionsInInput[1] = "-" + contradiction2LowerCase;
                return contradictionsInInput;
            }
        }
        for(Property property : Property.values()) {
            if(propertyList.contains(property.name().toLowerCase())
                    && propertyList.contains("-" + property.name().toLowerCase()) ) {
                contradictionsInInput[0] = property.name();
                contradictionsInInput[1] = "-" + property.name();
                break;
            }
        }
        return contradictionsInInput;
    }


    public static Property[][] hardCodedContradictions() {
        int amountOfContradictions = 4;
        Property[][] contradictionsList = new Property[amountOfContradictions][2];

        contradictionsList[0] = new Property[] {EVEN, ODD};
        contradictionsList[1] = new Property[] {DUCK, SPY};
        contradictionsList[2] = new Property[] {SQUARE, SUNNY};
        contradictionsList[3] = new Property[] {HAPPY, SAD};

        return contradictionsList;
    }

    public static void printAvailableProperties() {
        System.out.print("Available properties: [");
        StringBuilder availableProperties = new StringBuilder();
        for (Property property : Property.values()) {
            availableProperties.append(property.name().toUpperCase() + ", ");
        }
        out.print(availableProperties.substring(0, availableProperties.length() - 2));
        out.print("]\n");
    }
}