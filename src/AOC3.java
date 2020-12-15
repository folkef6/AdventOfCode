import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AOC3 {

   static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC3input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int traverse(int slopex, int slopey){
        int posx = 0;
        int posy = 0;
        int count=0;
        while(posy<input.size()-1){
            posx+=slopex;
            posy+=slopey;
            if(input.get(posy).charAt(posx%input.get(0).length()) == '#')
                count++;


        }

        return count;
    }


    public static void main(String[] args) {
        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        Arrays.stream(slopes)
                .map(s-> traverse(s[0],s[1]))
                .mapToLong(x->x)
                .reduce((x1,x2)->x1*x2).ifPresent(System.out::println);
    }
}
