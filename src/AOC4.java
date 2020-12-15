

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AOC4 {

    static List<String> input;
     static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC4input.txt").toPath());

            for (int i = 0; i < input.size()-1; i++) {
                if(!input.get(i + 1).equals("") && !input.get(i).equals("")){
                    while(!input.get(i+1).equals("")) {
                        input.set(i, input.get(i).concat(" " + input.get(i + 1)));
                        input.remove(input.get(i+1));
                        if(i+1 > input.size()-1)
                            break;
                    }

                }
                if(input.get(i).equals("")) {
                    input.remove(i);
                    i--;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public static boolean checkPassport(String passport){
         HashMap<String,Boolean> values = new HashMap<>();
         values.put("byr",false);
         values.put("iyr",false);
         values.put("eyr",false);
         values.put("hgt",false);
         values.put("hcl",false);
         values.put("ecl",false);
         values.put("pid",false);
       //  values.put("cid",false);

        String[] parts = passport.split(" ");


         for (String s :
                 parts) {
            values.put(s.substring(0,s.indexOf(':')),true);
         }
         boolean retval = true;
         for(String key: values.keySet()){

             retval = values.get(key)&&retval;
         }

         return retval;
     }

     public static boolean checkPassportValues(String passport){

         String[] parts = passport.split(" ");

         boolean retval = true;
         for (String part :
                 parts) {
             switch (part.substring(0, part.indexOf(":"))) {

                 case "byr": retval = byr(part.substring(part.indexOf(":")+1));
                     break;
                 case "iyr": retval=iyr(part.substring(part.indexOf(":")+1));
                     break;
                 case "eyr": retval=eyr(part.substring(part.indexOf(":")+1));
                     break;
                 case "hgt": retval=hgt(part.substring(part.indexOf(":")+1));
                     break;
                 case "hcl": retval=hcl(part.substring(part.indexOf(":")+1));
                     break;
                 case "ecl": retval=ecl(part.substring(part.indexOf(":")+1));
                     break;
                 case "pid": retval=pid(part.substring(part.indexOf(":")+1));
                     break;
                 case "cid":
                     break;


             }
             if(!retval) {
                 System.out.println(part);
                 return false;
             }
         }

        return true;
     }

     public static boolean byr(String s){
         return Integer.parseInt(s)<=2002 && Integer.parseInt(s)>=1920;
     }


    public static boolean iyr(String s){

        return Integer.parseInt(s)<=2020 && Integer.parseInt(s)>=2010;
    }

    public static boolean eyr(String s){

         return Integer.parseInt(s)<=2030 && Integer.parseInt(s)>=2020;

    }

    public static boolean hgt(String s){

        if(s.indexOf("cm")>0){
            return 150<=Integer.parseInt(s.substring(0,s.indexOf("cm")))
                    && Integer.parseInt(s.substring(0,s.indexOf("cm"))) <=193;
        }else if(s.indexOf("in")>0){
            return 59<=Integer.parseInt(s.substring(0,s.indexOf("in")))
                    && Integer.parseInt(s.substring(0,s.indexOf("in"))) <=76;
        }
        return false;
    }

    public static boolean hcl(String s){

         if(s.charAt(0)!='#' || s.length() <7) {
            // System.out.println("hcl LENTGTH " + s);
             return false;

         }
        for (char c :
                s.substring(1).toCharArray()) {
            if(!(c < 'g')){
              //  System.out.println("hcl" + s);
                return false;
            }

        }

        return true;
    }

    public static boolean ecl (String s){
         String[] validStrings = {"amb","blu","brn","gry","grn","hzl","oth"};
        for (String string :
                validStrings) {
            if(s.equals(string))
                return true;
        }
         return false;
    }

    public static boolean pid (String s){
         if(s.length()!=9)
         { return false; }
        for (char c :
                s.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

     public static void main(String[] args) {

         int count=0;
         for (String s :
                 input) {
             if(checkPassport(s))
                 count++;
         }

         System.out.println(count);

         count=0;
         for (String s :
                 input) {
             //System.out.println(s);
             if(checkPassport(s) && checkPassportValues(s))
                 count++;
         }
         System.out.println(count);

    }
}
