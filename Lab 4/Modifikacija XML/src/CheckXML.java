import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckXML {
    /*private static int CheckMethod(String[] redovi) {                         //copied
        Stack<String> stack = new Stack();

        for(int i = 0; i < redovi.length; ++i) {
            if (redovi[i].startsWith("[") && redovi[i].charAt(1) != '/') {
                stack.push(redovi[i]);
            }
            else if (redovi[i].startsWith("[") && redovi[i].charAt(1) == '/') {
                String sb = "[" + redovi[i].substring(2);
                if (sb.equals(stack.peek())) {
                    stack.pop();
                }
            }
        }

        if (stack.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }*/


    private static int CheckMethod(String[] redovi) {                       //mine
        Stack<String> stack = new Stack<>();
        for(int i = 0;i<redovi.length;i++){
            if(redovi[i].charAt(0)!= '[')
                continue;
            String line = redovi[i];
            line = line.replace("[","");
            line = line.replace("]","");
            if(line.charAt(0) != '/'){
                stack.push(line);
            }
            if(line.charAt(0) == '/'){
                line =line .replace("/", "");
                if(stack.peek().equals(line)){
                    stack.pop();
                }
                else{
                    return 1;
                }
            }
        }
        if(stack.isEmpty())
            return 1;
        return 0;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        int valid;
        for(valid = 0; valid < n; ++valid) {
            redovi[valid] = br.readLine();
        }

        valid = CheckMethod(redovi);
        System.out.println(valid);
        br.close();
    }
}