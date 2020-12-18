import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AOC17 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC17input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1(){

        HashMap<Integer, ArrayList<String>> space = new HashMap<>();
        space.put(0,new ArrayList<>(input));
        HashMap<Integer,HashMap<Integer,ArrayList<String>>> hyperSpace = new HashMap<>();
        hyperSpace.put(0,space);
        
        for (int i = 0; i < 6; i++) {
            for (Integer key1 :
                    hyperSpace.keySet()) {

                for (Integer key :
                        hyperSpace.get(key1).keySet()) {
                    for (int j = 0; j < hyperSpace.get(key1).get(key).size(); j++) {
                        hyperSpace.get(key1).get(key).set(j, "." + hyperSpace.get(key1).get(key).get(j) + ".");
                    }

                    hyperSpace.get(key1).get(key).add(0, ".".repeat(hyperSpace.get(key1).get(key).get(0).length()));
                    hyperSpace.get(key1).get(key).add(".".repeat(hyperSpace.get(key1).get(key).get(0).length()));
                //    System.out.println(hyperSpace.get(key1).get(key));
                }
                ArrayList<String> inactive = new ArrayList<>();
                for (int j = 0; j < hyperSpace.get(key1).get(0).size(); j++) {
                    inactive.add(".".repeat(hyperSpace.get(0).get(0).get(0).length()));
                }
                hyperSpace.get(key1).put((i + 1), new ArrayList<>(inactive));
                hyperSpace.get(key1).put(-1 * (i + 1), new ArrayList<>(inactive));
            }
            HashMap<Integer,ArrayList<String>> inactiveSpace = new HashMap<>();
            ArrayList<String> inactive = new ArrayList<>();
            for (int j = 0; j < hyperSpace.get(0).get(0).size(); j++) {
                inactive.add(".".repeat(hyperSpace.get(0).get(0).get(0).length()));
            }

            for (int key= -1*(i+1); key<=i+1;key++) {
                inactiveSpace.put(key,new ArrayList<>(inactive));
            }

            System.out.println(inactiveSpace);
            hyperSpace.put(i+1,new HashMap<>(inactiveSpace));
            hyperSpace.put(-1*(i+1), new HashMap<>(inactiveSpace));

            HashMap<Integer,HashMap<Integer,ArrayList<String>>> nextHyperspace = new HashMap<>();
            for (Integer key1:
                    hyperSpace.keySet()){
                HashMap<Integer, ArrayList<String>> nextSpace = new HashMap<>();
                for (Integer key :
                        hyperSpace.get(key1).keySet()) {
                    System.out.println(key1 + " " +key);
                    nextSpace.put(key, new ArrayList<>(hyperSpace.get(key1).get(key)));
                }
                nextHyperspace.put(key1,nextSpace);
            }
            for(Integer key1: hyperSpace.keySet()) {
                for (Integer key :
                        hyperSpace.get(key1).keySet()) {

                    for (int j = 0; j < hyperSpace.get(key1).get(key).size(); j++) {
                        for (int k = 0; k < hyperSpace.get(key1).get(key).get(j).length(); k++) {
                            int numberOfActive = 0;
                            for(int w= key1-1; w<=key1+1;w++) {
                                for (int z = key - 1; z <= key + 1; z++) {
                                    for (int y = j - 1; y <= j + 1; y++) {
                                        for (int x = k - 1; x <= k + 1; x++) {
                                            if (x == k && y == j && z == key && w==key1)
                                                continue;
                                         /*   if (!hyperSpace.containsKey(w) || !hyperSpace.get(w).containsKey(z) || y < 0 ||
                                                    y >= hyperSpace.get(key1).get(key).size() || x < 0 || x >= hyperSpace.get(key1).get(key).get(j).length())
                                                continue;

                                          */
                                            try {
                                                if (hyperSpace.get(w).get(z).get(y).charAt(x) == '#')
                                                    numberOfActive++;
                                            }catch (Exception ignore){}
                                        }
                                    }
                                }
                            }
                            System.out.println(numberOfActive + " "+ key1 + " " + key + " " + j + " " + k);
                            String nextString = nextHyperspace.get(key1).get(key).get(j);
                            if (nextString.charAt(k) == '#'
                                    && !(numberOfActive == 3 || numberOfActive == 2)) {
                                nextHyperspace.get(key1).get(key).set(j, nextString.substring(0, k) + '.' + nextString.substring(k + 1));
                            } else if (nextString.charAt(k) == '.' && numberOfActive == 3) {
                                nextHyperspace.get(key1).get(key).set(j, nextString.substring(0, k) + '#' +
                                        nextString.substring(k + 1));
                            }


                        }
                    }

                }
            }
            hyperSpace = new HashMap<>(nextHyperspace);
         //   System.out.println(hyperSpace);
        }

        int active = 0;
        for (Integer key1:
                hyperSpace.keySet()){
            for (Integer key :
                    hyperSpace.get(key1).keySet()) {
                for (String s :
                        hyperSpace.get(key1).get(key)) {
                    for (char c :
                            s.toCharArray()) {
                        if (c == '#')
                            active++;
                    }
                }
            }
        }
        return active;
    }

    public static void main(String[] args) {

        System.out.println(part1());
    }

}
