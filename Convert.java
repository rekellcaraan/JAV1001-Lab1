// Student Name: Rekell Caraan
// Student Number: A00133304
// Program Description: The following program "Convert.java"
// converts the following unit of measure to their corresponding equivalent of measure:
// km <--> mi, in <--> cm, oz <--> g, lbs <--> kg, cup <--> L, and also for temperature measures:
// degree C <--> degree F, degree C <--> degree K, degree F <--> degree K 
// This tool can accept single line input (e.g. 2.11 km, 2.11 lbs, etc.) OR
// Value first then unit of measure after pressing enter.
// Values are rounded up to 2 decimal places.
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Convert {

    private static float convertedValue;
    private static String convertedUnitOfMeasure;
    private static float convertedValue2nd;
    private static String convertedUnitOfMeasure2nd;

    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in); // Gets the user's input
        
        System.out.println(instructionLabel);

        while(true){ // Loop until user exits
            
            // Display the instruction label

            String userInputVariable = userInput.nextLine();

            if(userInputVariable.equalsIgnoreCase("EXIT")){
                break; // Exit only if user input is "EXIT"
            }
            
            // Initialize variables to use
            float valueToConvert = 0.0f;
            String valueToConvertUnitOfMeasure = "";

            // Get and parse user input
            // If user input is a single line, then split
            // Then parse the first string value, then parse the unit of measure
            // Else parse the first string value, then prompt to enter unit of measure
            // Then parse the unit of measure
            String[] splittedUserInput = userInputVariable.split("\\s+");

            if(splittedUserInput.length == 2){ // Single line input
                // Parse and save value
                valueToConvert = Float.parseFloat(splittedUserInput[0].trim());
                // Parse unit of measure
                valueToConvertUnitOfMeasure = splittedUserInput[1].trim();
            }
            else if(splittedUserInput.length == 1){ // value input
                // Parse and save value
                valueToConvert = Float.parseFloat(userInputVariable);

                // Prompt for unit of measure
                System.out.println(unitOfMeasurePrompt);

                String userInputUnitOfMeasure = userInput.nextLine().trim();
                if(userInputUnitOfMeasure.equalsIgnoreCase("EXIT")){
                    break; // Exit only if user input is "EXIT"
                }

                // Parse and save unit of measure
                valueToConvertUnitOfMeasure = userInputUnitOfMeasure;
            }
            else{
                // User input is not in proper format
                System.out.println(improperUserInputFormat);
            }

            // Convert
            String result = convert(valueToConvert, valueToConvertUnitOfMeasure);

            // Display result of conversion
            System.out.println(result);
        }
    }

    private static String instructionLabel = 
        "Thank you for using this Convert.java tool." + System.lineSeparator() +
        "Below are the list of conversion units that are available:" + System.lineSeparator() +
        "km <-> mi, cm <-> in, g <-> oz, cup <-> L,"+System.lineSeparator()+" degree C <-> degree F, degree C<->degree K, degree F<->degree K" + System.lineSeparator() +
        "Please input the value and unit of measure to convert (e.g. 2.11 km, 11 cup, etc.) OR" + System.lineSeparator() +
        "Input the the value to convert and press enter then input the unit of measure and press enter." + System.lineSeparator() + System.lineSeparator() +
        "To exit or quit, key in EXIT and press enter.";

    private static String improperUserInputFormat = "Unable to parse and convert your input. Please follow the correct format";

    private static String unitOfMeasurePrompt = "Please enter the unit of measure and press enter.";

    private static void convertDistance(float valueToConvert, String sourceUnit){

        switch(sourceUnit.toUpperCase()){
            case "KM": { // 1km = 0.62mi
                convertedValue = valueToConvert * 0.62f; 
                convertedUnitOfMeasure = "mi";
                break;
            }
            case "MI" : { // 1mi = 1.61km
                convertedValue = valueToConvert * 1.61f;
                convertedUnitOfMeasure = "km";
                break;
            }
            case "CM" : { // 1cm = 0.39in 
                convertedValue = valueToConvert * 0.39f;
                convertedUnitOfMeasure = "in";
                break;
            }
            default: //"IN"   1in = 2.54cm
                convertedValue = valueToConvert * 2.54f;
                convertedUnitOfMeasure = "cm";
                break;
        }
    }

    private static void convertMass(float valueToConvert, String sourceUnit){
        
        switch(sourceUnit.toUpperCase()){
            case "KG": { // 1kg = 2.2lbs
                convertedValue = valueToConvert * 2.2f; 
                convertedUnitOfMeasure = "lbs";
                break;
            }
            case "LB" :
            case "LBS" : { // 1lbs = 0.45kg
                convertedValue = valueToConvert * 0.45f;
                convertedUnitOfMeasure = "kg";
                break;
            }
            case "OZ" : { // 1oz = 28.35g
                convertedValue = valueToConvert * 28.35f;
                convertedUnitOfMeasure = "g";
                break;
            }
            default: //"G"   1g = 0.04oz
                convertedValue = valueToConvert * 0.04f;
                convertedUnitOfMeasure = "oz";
                break;
        }
    }

    private static void convertVolume(float valueToConvert, String sourceUnit){
        
        switch(sourceUnit.toUpperCase()){
            case "CUP":
            case "CUPS": { // 1cup = 4.17cups
                convertedValue = valueToConvert * 4.17f; 
                convertedUnitOfMeasure = "L";
                break;
            }
            default: //"L"   1cups = 0.24L
                convertedValue = valueToConvert * 0.24f;
                convertedUnitOfMeasure = "cups";
                break;
        }
    }

    private static void convertTemperature(float valueToConvert, String sourceUnit){
        
        switch(sourceUnit.toUpperCase()){
            case "C": { // 1C = 1.8C+32 F and C + 273.15 K
                convertedValue = (1.8f * valueToConvert) + 32; 
                convertedUnitOfMeasure = "F";
                convertedValue2nd = valueToConvert + 273.15f;
                convertedUnitOfMeasure2nd = "K";
                break;
            }
            case "F" : { // 1F = (F-32)/1.8 C and ((F-32)/1.8)+273.15 K
                convertedValue = (valueToConvert - 32) / 1.8f; 
                convertedUnitOfMeasure = "C";
                convertedValue2nd = convertedValue + 273.15f;
                convertedUnitOfMeasure2nd = "K";
                break;
            }
            default: //"K"   1K = K-273.15 C and ((K-273.15)*1.8)+32 F
                convertedValue = valueToConvert - 273.15f; 
                convertedUnitOfMeasure = "C";
                convertedValue2nd = (convertedValue * 1.8f) + 32;
                convertedUnitOfMeasure2nd = "F";
                break;
        }
    }

    private static String convert(float valueToConvert, String sourceUnit){
        
        String result = "";
        String resultAppendages = "";

        // Convert Distance
        if(sourceUnit.toUpperCase().equalsIgnoreCase("KM") ||
           sourceUnit.toUpperCase().equalsIgnoreCase("MI") ||
           sourceUnit.toUpperCase().equalsIgnoreCase("CM") ||
           sourceUnit.toUpperCase().equalsIgnoreCase("IN")){
            convertDistance(valueToConvert, sourceUnit);
           }
        // Convert Mass
        else if(sourceUnit.toUpperCase().equalsIgnoreCase("KG") ||
            sourceUnit.toUpperCase().contains("LB") ||
            sourceUnit.toUpperCase().equalsIgnoreCase("OZ") ||
            sourceUnit.toUpperCase().equalsIgnoreCase("G")){
            convertMass(valueToConvert, sourceUnit);
            }
        // Convert Volume
        else if(sourceUnit.toUpperCase().contains("CUP") ||
            sourceUnit.toUpperCase().equalsIgnoreCase("L")){
            convertVolume(valueToConvert, sourceUnit);
            }
        // Convert Temperature
        else if(sourceUnit.toUpperCase().equalsIgnoreCase("C") ||
            sourceUnit.toUpperCase().equalsIgnoreCase("F") ||
            sourceUnit.toUpperCase().equalsIgnoreCase("K")){
            convertTemperature(valueToConvert, sourceUnit);
            convertedValue2nd = round(convertedValue2nd, 2);  // round up to 2 decimal places
            resultAppendages = " and " + String.valueOf(convertedValue2nd) + String.valueOf(convertedUnitOfMeasure2nd);
            }
        else{
            return "Conversion is not supported.";
        }
        
        convertedValue = round(convertedValue, 2); // round up to 2 decimal places
        
        result = String.valueOf(valueToConvert) + " " + String.valueOf(sourceUnit) + " is equal to " +
                 String.valueOf(convertedValue) + String.valueOf(convertedUnitOfMeasure) + resultAppendages;

        return result;
    }
    
    public static float round(float d, int decimalPlace) {
        // Reference: https://stackoverflow.com/questions/8911356/whats-the-best-practice-to-round-a-float-to-2-decimals
        
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
    
}
