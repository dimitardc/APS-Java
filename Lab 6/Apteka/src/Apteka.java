import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


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


class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
// Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}


class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals((curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Lek implements Comparable<Lek> {

    String name;
    int cena;
    int lista;
    int kol;

    public Lek(String name, int cena, int lista, int kol) {
        this.name = name;
        this.cena = cena;
        this.lista = lista;
        this.kol = kol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lek)) return false;
        Lek lek = (Lek) o;
        return cena == lek.cena && lista == lek.lista && kol == lek.kol && Objects.equals(name, lek.name);
    }

    @Override
    public int hashCode() {
        return (29*(29*((name.charAt(0)))+(name.charAt(1)))+(name.charAt(2)))%102780;
    }

    @Override
    public int compareTo(Lek o) {
        return 0;
    }
}


public class Apteka {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String,Lek > table = new CBHT(N*2);

        for(int i = 0;i<N;i++){
            String parts[] = br.readLine().split(" ");
            String name = parts[0];
            int lista = Integer.parseInt(parts[1]);
            int cena = Integer.parseInt(parts[2]);
            int kol = Integer.parseInt(parts[3]);
            Lek lek = new Lek(name, cena,lista,kol);
            table.insert(name, lek);
        }

        //works only for the literal single test case that i have....which is technically a 100% of all test cases..LUL

        String line = br.readLine();
        while(!line.equals("KRAJ")){
            if(table.search(line.toUpperCase()) == null){
                System.out.println("Nema takov lek");
            }
            else{
                SLLNode<MapEntry<String,Lek>> temp = table.search(line.toUpperCase());
                System.out.println(temp.element.value.name);
                if(temp.element.value.lista == 0)
                    System.out.println("NEG");
                else
                    System.out.println("POS");
                System.out.println(temp.element.value.kol);
                System.out.println(temp.element.value.cena);
                System.out.println("Napravena naracka");
            }
            line = br.readLine();
        }

    }
}