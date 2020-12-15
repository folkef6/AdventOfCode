import java.util.ArrayList;
import java.util.HashMap;

public class AOC15 {

   static int[] input = {8,13,1,0,18};

    public static int part1(){

        HashMap<Integer,Integer> numbers = new HashMap<>();
        ArrayList<Integer> numbersSpoken = new ArrayList<>();

        for(int i=1; i<=input.length; i++){
                numbers.put(input[i-1],i);
                numbersSpoken.add(input[i-1]);
        }
        int lastNum=9;
        numbersSpoken.add(lastNum);

        for(int i=input.length+1;i <=30000000; i++){

            if(!numbers.containsKey(lastNum)){
                numbers.put(lastNum,i);
                numbersSpoken.add(0);
                lastNum=0;
            }else{
                Integer temp= numbers.get(lastNum);
                numbers.put(lastNum,i);
                lastNum=i-temp;
                numbersSpoken.add(lastNum);
            }
        }

        return numbersSpoken.get(30000000-1);
    }


    public static void main(String[] args) {

        System.out.println(part1());
        System.out.println(System.currentTimeMillis());
    }
}
