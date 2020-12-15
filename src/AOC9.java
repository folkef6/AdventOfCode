import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AOC9 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC9input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkNumber (int n, List<String> list){

        HashSet<Integer> sum = new HashSet<>();
        for (String s :
                list) {
            int num = Integer.parseInt(s);
            if(sum.contains(num))
                return true;
            sum.add(n-num);
        }
        return false;
    }

    public static long findWeakness(int index){
        List<Long> longInput = input.stream().map(Long::parseLong).collect(Collectors.toList());

        int hi = 1;
        int lo = 0;
        Long sum = longInput.subList(lo,hi+1).stream().reduce((long) 0,Long::sum);

        while(!sum.equals(longInput.get(index))){

            if(longInput.get(index) > longInput.subList(lo,hi+1).stream().reduce((long) 0,Long::sum)){
                hi++;
            }else if(longInput.get(index) < longInput.subList(lo,hi+1).stream().reduce((long) 0,Long::sum)){
                lo++;
            }

            sum= longInput.subList(lo,hi+1).stream().reduce((long) 0,Long::sum);

        }

        System.out.println(lo + " " + hi);
        return Collections.max(longInput.subList(lo,hi+1))+Collections.min(longInput.subList(lo,hi+1));

    }

    public static void main(String[] args) {

        for(int i=25; i<input.size();i++){

            if(!checkNumber(Integer.parseInt(input.get(i)),input.subList(i-25,i))) {
                System.out.println(findWeakness(i));
                break;
            }

        }

    }
}
