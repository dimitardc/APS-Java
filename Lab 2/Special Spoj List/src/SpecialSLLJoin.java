import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E extends Comparable<E>> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    public SLLNode(E elem){
        this.element = elem;
        succ = null;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E extends Comparable<E>> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public Iterator<E> iterator () {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

    // //////////Inner class ////////////

    private class LRIterator<E extends Comparable<E>> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public void mirror(){
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }

    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void removeDupes(SLLNode<E> node) {                //removing dupes recursevly
        if(node!=null){
            SLLNode<E> temp = node;
            SLLNode<E> tempNext = node.succ;
            while(tempNext!=null){
                if(temp.element == tempNext.element)
                    delete(tempNext);
                tempNext = tempNext.succ;
            }
            removeDupes(temp.succ);
        }
    }

    public SLL<E> SpecialjoinList(SLL<E> list){
        SLL<E> result = new SLL<>();
        SLLNode<E> temp1 = first;
        SLLNode<E> temp2 = list.first;
        while(temp1!=null && temp1.succ!=null && temp2!=null && temp2.succ!=null){
            result.insertLast(temp1.element);
            result.insertLast(temp1.succ.element);
            result.insertLast(temp2.element);
            result.insertLast(temp2.succ.element);
            temp1 = temp1.succ.succ;
            temp2 = temp2.succ.succ;

        }
        while(temp1!=null){
            result.insertLast(temp1.element);
            temp1 = temp1.succ;
        }
        while(temp2!=null){
            result.insertLast(temp2.element);
            temp2 = temp2.succ;
        }

        //result.removeDupes(result.first);
        //return result;

        /*temp1 = result.first.succ;
        Integer flag = (Integer) result.first.element;
        while(temp1!=null){
            if(flag == temp1.element){
                result.delete(temp1);
            }
            else{
                flag = (Integer) temp1.element;
            }
            temp1 = temp1.succ;
        }*/
        return result;
    }


}


public class SpecialSLLJoin {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String parts[] = br.readLine().split(" ");
        SLL<Integer> list1 = new SLL<>();
        for(int i = 0;i<n;i++){
            list1.insertLast(Integer.parseInt(parts[i]));
        }

        SLL<Integer> list2 = new SLL<>();
        n = Integer.parseInt(br.readLine());
        parts = br.readLine().split(" ");
        for(int i = 0;i<n;i++){
            list2.insertLast(Integer.parseInt(parts[i]));
        }

        SLL<Integer> result =list1.SpecialjoinList(list2);

        System.out.println(result);

    }
}