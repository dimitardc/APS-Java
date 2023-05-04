import java.util.NoSuchElementException;
import java.util.Scanner;

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

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

class Gragjanin {
    String ime;
    int lKarta;
    int pasos;
    int vozacka;

    public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
        ime = imePrezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    @Override
    public String toString() {
        return ime;
    }

}

public class MVR {

    public static void sort(LinkedQueue<Gragjanin> redica){
        LinkedQueue<Gragjanin> licna = new LinkedQueue<>() ;
        LinkedQueue<Gragjanin> pasos = new LinkedQueue<>() ;
        LinkedQueue<Gragjanin> vozacka = new LinkedQueue<>() ;

        while(!redica.isEmpty()) {

            Gragjanin covek = redica.dequeue();

            // dali e za licna
            if (covek.lKarta == 1) {
                licna.enqueue(covek);
            } else if (covek.pasos == 1) {
                pasos.enqueue(covek);
            } else if (covek.vozacka == 1) {
                vozacka.enqueue(covek);
            }
        }

        while(!licna.isEmpty()){
            Gragjanin covek = licna.dequeue();
            if(covek.vozacka == 0 && covek.pasos == 0){
                System.out.println(covek.ime);
            }
            else if(covek.pasos == 1){
                pasos.enqueue(covek);
            }
            else if(covek.vozacka == 1){
                vozacka.enqueue(covek);
            }
        }

        while(!pasos.isEmpty()){
            Gragjanin covek = pasos.dequeue();
            if(covek.vozacka == 0){
                System.out.println(covek.ime);
            }else{
                vozacka.enqueue(covek);
            }
        }

        while(!vozacka.isEmpty()){
            System.out.println(vozacka.dequeue().ime);
        }

    }

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);
        LinkedQueue<Gragjanin>  redica = new LinkedQueue<>();

        int N = Integer.parseInt(br.nextLine());
        for (int i = 0; i < N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            redica.enqueue(covek);
        }
        sort(redica);
    }
}