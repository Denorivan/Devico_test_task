package ua.com.task3;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FarmerTask {
    private static final int PIGS_LEGS = 4;
    private static final int COWS_LEGS = 4;
    private static final int CHICKEN_LEGS = 2;

    private static File test(String filename) throws IOException{
        String text = Files.readString(Paths.get(filename));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(text);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        long pigs = (long) jsonObject.get("pigs");
        long chickens = (long) jsonObject.get("chickens");
        long cows = (long) jsonObject.get("cows");

        long total = pigs * PIGS_LEGS + chickens * CHICKEN_LEGS + cows * COWS_LEGS;

        JSONObject result = new JSONObject();
        result.put("Total legs by all animals", total);

        return createOutputFile(result.toJSONString());
    }

    private static File createOutputFile(String text) throws FileNotFoundException {
        File file = new File("output.json");

        PrintWriter pw = new PrintWriter(file);
        pw.print(text);
        pw.close();

        return file;
    }

    public static void main(String[] args) throws IOException {
        String filename = "animals.json";

        if(args.length != 0){
            filename = args[0];
        }
        test(filename);
    }
}