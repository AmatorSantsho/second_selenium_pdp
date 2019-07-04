package utils;

public class TestHelper {
    public static int convertStringToInt(String price){
        String[] all = price.split(" ");
        return Integer.parseInt(all[0]);
    }
}
