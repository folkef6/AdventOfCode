import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AOC18 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC18input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long part1(String infix){

        Stack<String> operators = new Stack<>();
        ArrayList<String> postfix = new ArrayList<>();

        String fixedInfix = "";
        for (int i = 0; i < infix.length(); i++) {
            if(infix.charAt(i) == ')') {
                fixedInfix += " " + infix.charAt(i);
            }else if(infix.charAt(i) == '(') {
                fixedInfix += infix.charAt(i) + " ";
            }else
                fixedInfix+=infix.charAt(i);
        }

        String[] parts = fixedInfix.split(" ");

        for(String s : parts){


            switch (s) {
                case "(" -> operators.push(s);
                case ")" -> {
                    String c = operators.pop();
                    while (!c.equals("(")) {
                        postfix.add(c);
                        c = operators.pop();
                    }
                }
                case "+" -> {
                    if (!operators.empty()) {
                        while (operators.peek().equals("+")) {
                            postfix.add(operators.pop());
                            if (operators.empty())
                                break;
                        }
                    }
                    operators.push(s);
                }
                case "*" -> {
                    if (!operators.empty()) {
                        while (!operators.peek().equals("(")) {
                            postfix.add(operators.pop());
                            if (operators.empty())
                                break;
                        }
                    }
                    operators.push(s);
                }
                default -> postfix.add(s);
            }
        }
        while (!operators.empty()){
            postfix.add(operators.pop());
        }
        System.out.println(postfix);
        System.out.println(operators);

        //Calculate postfix

        Stack<Long> operands = new Stack<>();
        for (String part:
             postfix) {

            if(part.equals("+")){
                long num1= operands.pop();
                long num2= operands.pop();
                operands.push(num1+num2);
            }else if(part.equals("*")){
                long num1= operands.pop();
                long num2= operands.pop();
                operands.push(num1*num2);
            }else
                operands.push(Long.parseLong(part));

        }

        return operands.pop();

    }


    public static void main(String[] args) {
       // System.out.println(part1("1 + 2 * 3 + 4 * 5 + 6"));
        long sum=0;
        for (String line:
             input) {
            sum+=part1(line);
        }
        System.out.println(sum);


       // input.stream().mapToInt(AOC18::part1).reduce(Integer::sum).ifPresent(System.out::println);
    }
}
