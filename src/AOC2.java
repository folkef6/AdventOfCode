import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.imageio.plugins.tiff.TIFFField;
import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.imageio.plugins.tiff.TIFFTag;
import javax.sound.midi.MidiFileFormat;
import java.util.ArrayList;

public class AOC2 {


    public static boolean checkPassword1(String input){

        int n1 = Integer.parseInt(input.substring(0,input.indexOf("-")));
        int n2 = Integer.parseInt(input.substring(input.indexOf("-")+1,input.indexOf(" ")));
        Character key = input.charAt(input.indexOf(":")-1);

        String password = input.substring(input.indexOf(":")+1);

        int occurence = 0;
        for(char c: password.toCharArray()){
            if(key.equals(c))
                occurence++;
        }


        return occurence<=n2 && occurence >=n1;
    }
    public static boolean checkPassword2(String password){

        String[] parts0 = password.split("-");
        String[] parts1 = parts0[1].split(" ");
        String[] parts2 = password.split(":");

        int lowerBound = Integer.parseInt(parts0[0]);
        int upperBound = Integer.parseInt(parts1[0]);

        Character character = parts1[1].charAt(0);

        String passwordPart = parts1[2];

        return character.equals(passwordPart.charAt(lowerBound-1)) ^ character.equals(passwordPart.charAt(upperBound-1));

    }

    public static void main(String args[]){

        int size=0;
        for(String s: AOC2input.input){
            if(checkPassword1(s)) size++;
        }

        System.out.println(size);
    }
}


