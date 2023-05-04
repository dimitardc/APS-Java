import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;

    public LinkedStack () {
        // Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void clear () {
        // Go prazni stekot.
        top = null;
    }

    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
    }

    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        top = top.succ;
        return topElem;
    }

    public boolean peekTruth(){
        if (top == null)
            return false;
        return true;
    }

}


public class PostFixEvaluation {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();


        System.out.println(Postfix(exp));


        br.close();

    }

    private static Double Postfix(char[] exp) {
        LinkedStack<Double> stack = new LinkedStack<>();
        Double r = null;
        for(int i = 0 ; i<exp.length;i++){
            char c = exp[i];
            if(c == ' ')
                continue;
            else if(Character.isDigit(c)){
                stack.push((double)c - '0');
            }
            else{
                if(stack.peekTruth()){          //treba stack.sise()>=2 ama nema size() f-ja vo klasata
                    Double posledenbr = stack.pop();
                    Double predposledenbr = stack.pop();
                    switch (c) {
                        case '+':
                            stack.push(posledenbr + predposledenbr);
                            break;
                        case '-':
                            stack.push(posledenbr - predposledenbr);
                            break;
                        case '*':
                            stack.push(posledenbr * predposledenbr);
                            break;
                        case '/':
                            stack.push(posledenbr / predposledenbr);
                            break;
                    }
                }
                else{
                    System.out.println("Imash greska - nedostasuva brojka ");
                }
            }
        }
        if(!stack.peekTruth()){                 //treba stack.sise()!=1 ama nema size() f-ja vo klasata
            System.out.println("Imash greska - nedostasuva operator ");
        }
        else {
            r = stack.pop();
        }
        return r;
    }
}