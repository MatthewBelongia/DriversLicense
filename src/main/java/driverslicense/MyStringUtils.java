package driverslicense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by matthewb on 5/23/16.
 */
public class MyStringUtils {

    public static String combineChars(char[] listofChar){
        String result = "";
        for(int c =0;c<listofChar.length;c++){
            result+=listofChar[c];
        }
        return result;
    }

    public static String[] splitStringByLine(String input){
        return input.split("\\n");
    }

    public static String reverseCapitalize(String input){
        input=input.toUpperCase();
        StringBuilder buffer = new StringBuilder(input);
        Pattern p = Pattern.compile("\\b([A-Z])");
        Matcher m = p.matcher(buffer);
        while(m.find()){
            buffer.replace(m.start(),m.end(),m.group().toLowerCase());
        }
        input = buffer.toString();
        return input;
    }

    public static String reverseWord(String input){
        String reversed = "";
        for(int i=input.length()-1;i>=0;i--){
            reversed += input.charAt(i);
        }
        return reversed;
    }

    public String reverseAllWords(String input){
        String result = "";
        for(String ss : input.split("\\s")){
            result += reverseWord(ss)+" ";
        }
        /*
        List<String> listResult = Arrays.stream(input.split(" ")).map(i -> reverseWord(i)).collect(Collectors.toList());
        for(String s : listResult){
            result += s + " ";
        }
        */
        result = result.trim();
        System.out.println(result);
        return result;
    }

    public String removeSpaceAndNewLine(String input){
        input.trim();
        input = input.replace(" ","\n");
        return input;
    }

    public ArrayList<String> allSubstrings(String input){
        ArrayList<String> results = new ArrayList<>();
        for(int i = 0;i<input.length();i++){
            //results.add(input.substring(i));
            for(int q =input.length();q>i;q--){
                results.add(input.substring(i,q));
            }
        }
        for(String s: results){
            System.out.println(s);
        }
        return results;
    }

    public String optionalChallenge(String[] arrayOfStrings){
        String result = "";
        for(String s : arrayOfStrings){
            int smallerLength = Math.min(s.length(),80);
            String hexaLength = Integer.toHexString(s.length());
            result += String.format("%80s",s.substring(0,smallerLength)) + "\t" + hexaLength +  "\n";
        }
        return result;
    }
}
