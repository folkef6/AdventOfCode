import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AOC8 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC8input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int traverseProgram(){



        for (int i = 0; i < input.size(); i++) {

            HashSet<Integer> hasExecuted = new HashSet<>();
            int accumulator = 0;
            int index = 0;
            List<String> changedList = new ArrayList<>(input);
            String[] parts1 = changedList.get(i).split(" ");
            if(parts1[0].equals("jmp")) {
                changedList.set(i, "nop " + parts1[1]);
            }else if(parts1[0].equals("nop")){
                changedList.set(i, "jmp " + parts1[1]);
            }

            System.out.println(changedList.get(i));

            while (true) {
                if (index >= input.size())
                    return accumulator;
                String[] parts = changedList.get(index).split(" ");
                if (hasExecuted.contains(index)) {
                    break;
                } else {
                    hasExecuted.add(index);
                }

                switch (parts[0]) {
                    case "acc" -> {
                        accumulator += Integer.parseInt(parts[1]);
                        index++;
                    }
                    case "jmp" -> {
                        index += Integer.parseInt(parts[1]);

                    }
                    case "nop" -> {
                        index++;
                    }
                }


            }

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(traverseProgram());
    }

}
