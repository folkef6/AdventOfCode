import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AOC11 {

    static List<String> input;
    static {
        try {
            input = Files.
                    readAllLines(new File("C:\\Users\\folke\\IdeaProjects\\AdventOfCode\\src\\AOC11input.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1(){

        List<String> lastRound = new ArrayList<>(input);
        List<String> nextRound =new ArrayList<>();
        StringBuilder nextRow = new StringBuilder();
        while (true){
            System.out.println(lastRound);
            for (int i = 0; i < lastRound.size(); i++) {
                for (int j = 0; j < lastRound.get(0).length(); j++) {

                    char c = lastRound.get(i).charAt(j);
                    if(c == '.') {
                        nextRow.append(".");
                        continue;
                    }
                    int adcO = 0;
                    for (int y = -1; y <=1; y++) {
                        if(i+y <0 || i+y>lastRound.size()-1)
                            continue;
                        for (int x = -1; x <=1 ; x++) {
                            if(x+j <0 || x+j > lastRound.get(0).length()-1 || (x==0 && y==0))
                                continue;
                            int posx = x+j;
                            int posy = y+i;
                            while(true) {
                                if (lastRound.get(posy).charAt(posx) == '#') {
                                    adcO++;
                                    break;
                                }else if(lastRound.get(posy).charAt(posx) == 'L')
                                    break;
                                posx+=x;
                                posy+=y;
                                if(posx<0 || posx>lastRound.get(0).length()-1 || posy<0 || posy>lastRound.size()-1)
                                    break;
                            }
                        }
                    }

                    if(c=='L') {
                        if (adcO == 0) {
                            nextRow.append('#');
                        }else{
                            nextRow.append('L');
                        }
                    }
                    if(c=='#') {
                        if (adcO >= 5) {
                            nextRow.append('L');
                        }else{
                            nextRow.append('#');
                        }
                    }

                }
                nextRound.add(nextRow.toString());
                nextRow = new StringBuilder();

            }
            if(nextRound.equals(lastRound)){
                break;
            }else{
                lastRound=nextRound;
                nextRound= new ArrayList<>();
            }
        }

        int noOfOcc=0;
        for (String s : lastRound) {
            for (int j = 0; j < lastRound.get(0).length(); j++) {
                if (s.charAt(j) == '#')
                    noOfOcc++;
            }
        }

        return noOfOcc;
    }



    public static void main(String[] args) {

        System.out.println(part1());
    }
}
