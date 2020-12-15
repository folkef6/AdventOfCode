import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class AOC12 {



    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC12input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int traverse(){

        int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
        int directionIndex=0;
        int posX = 0;
        int posY = 0;

        for (String s :
                input) {


            switch (s.charAt(0)) {
                case 'R' -> directionIndex += Integer.parseInt(s.substring(1)) / 90;
                case 'L' -> directionIndex -= Integer.parseInt(s.substring(1)) / 90;
                case 'F' -> {
                    posX += direction[((directionIndex%4)+4)%4][0]*Integer.parseInt(s.substring(1));
                    posY += direction[((directionIndex%4)+4)%4][1]*Integer.parseInt(s.substring(1));
                }
                case 'S' -> posY += Integer.parseInt(s.substring(1));
                case 'N' -> posY -= Integer.parseInt(s.substring(1));
                case 'W' -> posX -= Integer.parseInt(s.substring(1));
                case 'E' -> posX += Integer.parseInt(s.substring(1));
            }

        }


        return Math.abs(posX) + Math.abs(posY);

    }

    public static int part2 (){

        int[] waypoint = {10,-1};
        //Rotate by swapping and *-1 on the y value;
        int posX=0;
        int posY=0;

        for (String s :
                input) {
            System.out.println(waypoint[0] + " " + waypoint[1]);
            System.out.println(posX + " " + posY);
            switch (s.charAt(0)) {
                case 'R' -> {
                    for (int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
                        int temp = waypoint[0];
                        waypoint[0] = waypoint[1] * -1;
                        waypoint[1] = temp;
                    }
                }
                case 'L' -> {
                    for (int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
                        int temp = waypoint[1];
                        waypoint[1] = waypoint[0] * -1;
                        waypoint[0] = temp;
                    }
                }
                case 'F' -> {
                    posX += waypoint[0]*Integer.parseInt(s.substring(1));
                    posY += waypoint[1]*Integer.parseInt(s.substring(1));
                }
                case 'S' -> waypoint[1]+=Integer.parseInt(s.substring(1));
                case 'N' -> waypoint[1]-=Integer.parseInt(s.substring(1));
                case 'W' -> waypoint[0]-=Integer.parseInt(s.substring(1));
                case 'E' -> waypoint[0]+=Integer.parseInt(s.substring(1));
            }

        }

        return Math.abs(posX)+Math.abs(posY);
    }

    public static void main(String[] args) {
        System.out.println(traverse());
        System.out.println(part2());
    }
}
