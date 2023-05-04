import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();
    }

    private static int najdiNajdolgaCikCak(int[] a) {
        int tempMax=0,MAX = 0;
        boolean blockPoz = true, blockNeg = true;
        for(int i = 0;i<a.length;i++){
            if(a[i] > 0 && blockPoz == true){
                tempMax++;
                blockPoz = false;
                blockNeg = true;
                if(tempMax > MAX)
                    MAX = tempMax;
            }
            else if(a[i] < 0 && blockNeg == true){
                tempMax++;
                blockNeg = false;
                blockPoz = true;
                if(tempMax > MAX)
                    MAX = tempMax;
            }
            else{
                if(a[i] == 0){
                    tempMax = 0;
                    blockNeg = true;
                    blockPoz = true;
                }
                else{
                    tempMax = 1;
                }
            }
        }
        return MAX;
    }
}
