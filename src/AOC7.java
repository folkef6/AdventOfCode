import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class AOC7 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC7input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static HashMap<String,String[]> bags = new HashMap<>();

    public static void createHashMap(){
        for (String line :
                input) {
            String color = line.substring(0, line.indexOf("contain") - 1)
                    .substring(0,line.indexOf("bag")-1);
            String[] contains = new String[0];


            if(!line.contains("no other bags")){
                String subString = line.substring(line.indexOf("contain")+7,line.indexOf('.'));
                contains = subString.split(",");

                for (int i = 0; i < contains.length; i++) {

                    contains[i] = contains[i].substring(1,contains[i].indexOf("bag")-1);
                }

            }

            bags.put(color,contains);

        }


    }

    public static boolean checkBag(String color){
    //    String color = rule.substring(0,rule.indexOf("contain")-1);

        boolean retval;
        if(bags.get(color) == null){
            return false;
        }else {
            for (String s :
                    bags.get(color)) {
                System.out.println(s);
                if (s.equals("shiny gold"))
                    return true;

                retval = checkBag(s);
                if (retval)
                    return retval;
            }
        }
        return false;
    }

    public static int countBags(String bag){
        int sum=1;
        if (bags.get(bag) == null) {
            return 1;
        }
        for (String s:
             bags.get(bag)) {
            System.out.println(s);
            sum += Integer.parseInt(s.substring(0,1))*countBags(s.substring(2));

        }

        return sum;
    }

    public static void main(String[] args) {
        createHashMap();

        int sum=0;
     /*   for (String color:
             bags.keySet()) {
            if(checkBag(color))
                sum++;
        }

       System.out.println(sum);
*/
        System.out.println(countBags("shiny gold"));

    }
}
