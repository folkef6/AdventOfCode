import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class AOC10 {

    static List<String> input;
    static List<Integer> sorted;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC10input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sorted = input.stream().mapToInt(Integer::parseInt).sorted()
                .boxed().collect(Collectors.toList());
    }

    public static int traverseAdapters(){

        int last = 0;
        int numberOf3=1;
        int numberOf1=0;

        for (Integer integer : sorted) {

            switch (integer - last) {
                case 1 -> numberOf1++;
                case 3 -> numberOf3++;
            }

            last = integer;

        }

        return numberOf3*numberOf1;
    }

    public static long numberOfWays(){

        long numberOfWays = 1;
        int concurrent=1;
        for (int i = 0; i < sorted.size()-1; i++) {

            if(sorted.get(i+1)-sorted.get(i) == 1){
                concurrent++;
            }else{
                System.out.println(sorted.get(i) + " " + concurrent +" "+ numberOfWays);
                if(concurrent>4)
                    numberOfWays*= Math.pow(2,concurrent-2)-1;
                else if(concurrent>1)
                    numberOfWays*= Math.pow(2,concurrent-2);
                concurrent=1;
            }

        }
        if(concurrent>4)
            numberOfWays*= Math.pow(2,concurrent-2)-1;
        else if(concurrent>1)
            numberOfWays*= Math.pow(2,concurrent-2);

        return numberOfWays;
    }
    public static int factorial(int n){
        int product=1;
        while(n>0){
            product*=n--;
        }
        return product;
    }

    public static void main(String[] args) {
        System.out.println(sorted);
  //      System.out.println(traverseAdapters());
        System.out.println(numberOfWays());
    }
}
