import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOC5 {
    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC5input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRow(String s){

        s = s.substring(0,7);
        StringBuilder sb = new StringBuilder();
        for (char c:
             s.toCharArray()) {
            if(c == 'F')
                sb.append('0');
            if(c=='B')
                sb.append('1');
        }

        return Integer.parseInt(sb.toString(),2);
    }

    public static int getCol(String s){

        s=s.substring(7);
        StringBuilder sb = new StringBuilder();
        for (char c :
                s.toCharArray()) {
            if(c=='R')
                sb.append('1');
            if(c=='L')
                sb.append('0');
        }

        return Integer.parseInt(sb.toString(),2);
    }

    public static int getSeatId(String s){
       return getRow(s)*8 + getCol(s);

    }

    public static void main(String[] args) {

       //input.stream().mapToInt(x-> getRow(x)*8+getCol(x)).max().ifPresent(System.out::println);
        int[] seats = input.stream().mapToInt(AOC5::getSeatId).sorted().toArray();

        for (int i = 0; i < seats.length-1; i++) {
            if(seats[i+1]-seats[i] == 2) {
                System.out.println(seats[i] + 1);
                break;
            }
        }


    }
}
