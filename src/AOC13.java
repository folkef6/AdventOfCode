import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.*;

public class   AOC13 {
    
    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC13input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int part1(){
        String[] parts = input.get(1).split(",");
        int departure = Integer.parseInt(input.get(0));
        HashMap<Integer,Integer> answers = new HashMap<>();
        for (String s :
                parts) {
            if(!s.equals("x")){

                System.out.println(s);
                System.out.println(Integer.parseInt(s)-departure%Integer.parseInt(s));
               answers.put(Integer.parseInt(s),(Integer.parseInt(s)-departure%Integer.parseInt(s)));

            }
        }

        return Collections.min(answers.entrySet(),Map.Entry.comparingByValue()).getKey()*
                answers.get(Collections.min(answers.entrySet(),Map.Entry.comparingByValue()).getKey());
        
    }

    public static long part2(){

        long t;
        long counter=1;
        String[] parts = input.get(1).split(",");
        HashMap<Integer,Integer> numbers = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            if(!parts[i].equals("x")){
                numbers.put(i,Integer.parseInt(parts[i]));
            }
        }


        long current=1;

        ArrayList<Integer> sorted = new ArrayList<>((numbers.keySet()));
        Collections.sort(sorted);
        for (Integer i :
                sorted) {
            System.out.println("offset: " + i + " number: "+ numbers.get(i) + " current " + current);
            current=findMultiple(current,numbers.get(i),i);
           // System.out.println(current);
        }

        return current;
    }


    public static long findMultiple (long x, long y, int offSet){

        long newX = x;
        while(true){
       //     System.out.println(newY);

            if((newX+offSet)%y == 0) {
                return newX;
            }else{
                newX+=x;

            }

        }


    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long phi(long n)
    {
        int result = 1;
        for (int i = 2; i < n; i++)
            if (gcd(i, n) == 1)
                result++;
        return result;
    }

    public static long chineseRemainder(){

        String[] parts = input.get(1).split(",");
        HashMap<Integer,Integer> numbers = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            if(!parts[i].equals("x")){
                numbers.put(i,Integer.parseInt(parts[i]));
            }
        }
        System.out.println(numbers);
        long N =1;
        for (Integer key:
             numbers.keySet()) {
            N*=numbers.get(key);
        }

        BigInteger n = new BigInteger(String.valueOf(N));
        System.out.println("N is " + N);
        long sum = 0;

        for (Integer key:
             numbers.keySet()) {
            BigInteger moduluNum = new BigInteger(numbers.get(key).toString());
            BigInteger Ndivn = new BigInteger(String.valueOf(n.divide(moduluNum)));
         //   System.out.println(Ndivn%moduluNum);
           // System.out.println(moduluNum);
            //System.out.println(Math.pow(Ndivn%moduluNum,phi(moduluNum)-1));
            BigInteger exponent = new BigInteger(String.valueOf(phi(moduluNum.longValue())-1));
            BigInteger bi = new BigInteger(String.valueOf(Ndivn.pow((int) phi(moduluNum.longValue())-1).mod(moduluNum)));
            long ai = (moduluNum.intValue() -key) % moduluNum.longValue();

           // System.out.println("ai " + ai + " bi " + bi + " modnum " + moduluNum + " fi " + phi((int) moduluNum));
           // System.out.println((N/moduluNum));
            sum+=ai*bi.longValue()*Ndivn.longValue();
            System.out.println("sum is " + sum);
        }


        return sum%N;

    }

    public static void main(String[] args) {
       // System.out.println(findMultiple(325,641,13));
        //System.out.println(part2());


       // long ans = chineseRemainder();
       // System.out.println(ans);

        System.out.println(part2());

    }
    
}
