public class AOC1 {

    public static void main(String args[]){

        for(int i=0;i<AOC1input.input.length-1;i++)
            for(int j=i+1;j<AOC1input.input.length;j++)
                for(int k=j+1;k<AOC1input.input.length;k++)
                    if(AOC1input.input[i]+AOC1input.input[j]+AOC1input.input[k] == 2020)
                        System.out.println(AOC1input.input[i]*AOC1input.input[j]*AOC1input.input[k]);


    }


}
