import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class AOC6 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC6input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countYesAnswers(){

        int sum=0;
        int members=0;
        HashMap<Character,Integer> yes = new HashMap<>();
        for (String line:
             input) {
            if(line.equals("")){
                for (Character c:
                     yes.keySet()) {
                    if(yes.get(c) == members)
                        sum++;
                }
                System.out.println(sum);
                yes = new HashMap<>();
                members = 0;
            }else{
                members++;
                for (char c:
                     line.toCharArray()) {

                        if(yes.containsKey(c)){
                            Integer i = yes.get(c) + 1;
                            yes.put(c,i);
                        }else {
                            yes.put(c, 1);
                        }
                }
            }
        }
        return sum+=yes.size();

    }

    public static void main(String[] args) {
        System.out.println(countYesAnswers()) ;
    }
}
