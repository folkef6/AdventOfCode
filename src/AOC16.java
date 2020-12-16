import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class AOC16 {
    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC16input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<String> validTickets = new ArrayList<>();
    static HashMap<String,HashSet<Integer>> fields = new HashMap<>();
    public static int part1(){

        HashSet<Integer> validNumbers = new HashSet<>();


        for(String line : input){
            if(line.equals(""))
                break;

            HashSet<Integer> currentFormRange = new HashSet<>();
            for(int i=Integer.parseInt(line.substring(line.indexOf(":")+2,line.indexOf("-")));
                i <= Integer.parseInt(line.substring(line.indexOf("-")+1,line.indexOf(" or ")));
                i++){
                validNumbers.add(i);
                currentFormRange.add(i);
            }
            for(int i=Integer.parseInt(line.substring(line.indexOf(" or ")+4,line.lastIndexOf("-")));
                i <= Integer.parseInt(line.substring(line.lastIndexOf("-")+1));
                i++){
                validNumbers.add(i);
                currentFormRange.add(i);
            }
            fields.put(line.substring(0,line.indexOf(":")),currentFormRange);
            
        }


        boolean isNerabyTickets = false;

        int sum = 0;
        outer:
        for (String line :
                input) {
            if(line.equals("nearby tickets:")) {
                isNerabyTickets = true;
                continue;
            }
            if(!isNerabyTickets)
                continue;

            String[] parts = line.split(",");
            for (String num :
                    parts) {
                if(!validNumbers.contains(Integer.parseInt(num))) {
                    sum+=Integer.parseInt(num);
                    continue outer; //added for part 2
                }
            }
            validTickets.add(line);
        }
        return sum;

    }

    public static long part2(){

        HashMap<String,ArrayList<Integer>> formIndex = new HashMap<>();
        for (int i = 0; i < validTickets.get(0).split(",").length; i++) {
            HashSet<Integer> thisIndex = new HashSet<>();
            for (String validTicket : validTickets) {
                //   System.out.println(Integer.parseInt(validTickets.get(j).split(",")[i]));
                thisIndex.add(Integer.parseInt(validTicket.split(",")[i]));
            }


            for (String s:
                 fields.keySet()) {
                if(fields.get(s).containsAll(thisIndex)){
                    if(!formIndex.containsKey(s))
                        formIndex.put(s,new ArrayList<>());

                    formIndex.get(s).add(i);

                }
            }



            }

        HashMap<String,Integer> correctIndex = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            String current = null;
            for (String key :
                    formIndex.keySet()) {
                if(formIndex.get(key).contains(i)){
                    if(current==null || formIndex.get(key).size()<formIndex.get(current).size())
                        current=key;
                }
            }
            correctIndex.put(current,i);
        }

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("your ticket:")){
                long product=1;

                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure track")]);
                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure time")]);
                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure station")]);
                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure date")]);
                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure platform")]);
                product*=Integer.parseInt(input.get(i+1).split(",")[correctIndex.get("departure location")]);

                return product;
            }
        }

        return -1;

    }
    
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }
}
