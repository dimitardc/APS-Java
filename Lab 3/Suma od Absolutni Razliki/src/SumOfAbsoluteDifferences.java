import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

    private static int solve(int[] numbers, int N, int K) {
        int[][] m = new int[N][K];
        int res = 0;

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < K; j++) {
                for(int k = 0; k < i; k++) {
                    int abs = Math.abs(numbers[i] - numbers[k]);
                    if(m[i][j] < m[k][j-1] + abs)
                        m[i][j] = m[k][j-1] + abs;
                }
                if(m[i][j] > res) res = m[i][j];
            }
        }
        return res;
    }
}
