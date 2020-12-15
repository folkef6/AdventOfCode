import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class AOC14 {
    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC14input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static long traverse(){

        String mask = "hej";
        HashMap<Long,Long> memory = new HashMap<>();

        String bit36 = "0".repeat(36);
        for (String s :
                input) {
            System.out.println(s);
            System.out.println(mask);
            if(s.startsWith("mas")) {
                mask = s.substring(s.indexOf('=')+2);
                continue;
            }
            Long num = Long.valueOf(s.substring(s.indexOf("=")+2));
            String adress = Long.toBinaryString(Long.parseLong(s.substring(s.indexOf("[")+1,s.indexOf("]"))));

            adress = bit36.substring(0,36-adress.length()) + adress;

            int numberOfX=0;
            for (int i = 0; i < 36; i++) {

                switch (mask.charAt(i)){
                    case 'X' ->  {adress = adress.substring(0,i)+mask.charAt(i)+adress.substring(i+1);
                                    numberOfX++;}
                    case '1' ->  adress = adress.substring(0,i)+mask.charAt(i)+adress.substring(i+1);

                }

            }
            for(int i=0;i<Math.pow(2,numberOfX);i++){
                System.out.println("numberOfX " + numberOfX);
                String zero = "0".repeat(numberOfX);
                String binary = Integer.toBinaryString(i);
                binary = zero.substring(0,zero.length()-binary.length()) + binary;
                System.out.println(binary);
                int n=binary.length()-1;
                String tempAdress = new String(adress);
                System.out.println("adress " +adress);

                for (int j=0; j<adress.length(); j++) {
                    if(adress.charAt(j)=='X'){
                        if(n>=0){

                            System.out.println(binary.charAt(n));
                            tempAdress = tempAdress.substring(0,j)+binary.charAt(n)+tempAdress.substring(j+1);
                            n--;
                            System.out.println(tempAdress);
                        }

                    }

                }
                if(n>=0)
                    System.out.println("ERROR " + n);
                memory.put(Long.valueOf(tempAdress,2),num);

            }


        }



        long sum=0;
        for (Long key:
                memory.keySet()) {
            sum+=memory.get(key);
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(traverse());
    }
}
