import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;
    char ltag;
    char rtag;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }
}

class BTree<E> {

    public BNode<E> head;

    public BTree() {
        head = new BNode<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BNode<E> makeRoot(E elem) {
        BNode<E> tmp = new BNode<E>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {

            if (node.ltag == '+')   // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+')   // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> insertRight(BNode<E> parent, E info) {

        BNode<E> child = new BNode<E>(info);

        child.ltag = '-';
        child.left = parent;
        child.rtag = parent.rtag;
        child.right = parent.right;

        parent.right = child;
        parent.rtag = '+';

        if (child.rtag == '+') {
            BNode<E> temp = child.right;
            while (temp.ltag == '+')
                temp = temp.left;
            temp.left = child;
        }

        return child;
    }

    public BNode<E> predecessorInorder(BNode<E> node) {

        if (node.ltag == '-')
            return node.left;

        BNode<E> p = node.left;
        while (p.rtag == '+')
            p = p.right;

        return p;
    }

    public BNode<E> successorInorder(BNode<E> node) {

        if (node.rtag == '-')
            return node.right;

        BNode<E> p = node.right;
        while (p.ltag == '+')
            p = p.left;

        return p;
    }

    public void inorderNonRecursive() {

        if (head.ltag == '-')      // drvoto e prazno
            return;

        System.out.print("INORDER (nonrecursive): ");

        BNode<E> p = head.left;

        while (p.ltag == '+')
            p = p.left;

        while (p != head) {
            System.out.print(p.info.toString()+" ");
            p = successorInorder(p);
        }
        System.out.println();

    }

    public void inorder() {
        System.out.print("INORDER: ");

        if (head.ltag == '+')
            inorderR(head.left);

        System.out.println();
    }

    void inorderR(BNode<E> n) {

        if (n.ltag == '+')
            inorderR(n.left);

        System.out.print(n.info.toString()+" ");

        if (n.rtag == '+')
            inorderR(n.right);

    }

    public void preorder() {
        System.out.print("PREORDER: ");

        if (head.ltag == '+')
            preorderR(head.left);

        System.out.println();
    }

    void preorderR(BNode<E> n) {

        System.out.print(n.info.toString()+" ");

        if (n.ltag == '+')
            preorderR(n.left);

        if (n.rtag == '+')
            preorderR(n.right);

    }

    BNode<E> successorPreorder(BNode<E> node) {
        if (node.ltag == '+')
            return node.left;
        if (node.rtag == '+')
            return node.right;

        // ako nema nitu levo nitu desno potsteblo, nаslednik e korenot
        // na desnoto potsteblo vo cie levo posteblo se naoga p
        // (se odi se po niski dodeka ne se dojde do vrska)
        BNode<E> p = node;
        while (p.rtag == '-')
            p = p.right;

        if (p == head)
            return head;    // sme stignale do vodacot, moze, no i ne mora
        else
            return p.right;
    }

    public void preorderNonRecursive() {
        if (head.ltag == '-')      // drvoto e prazno
            return;

        System.out.print("PREORDER (nonrecursive): ");

        BNode<E> p = head.left;

        while (p != head) {
            System.out.print(p.info.toString()+" ");
            p = successorPreorder(p);
        }
        System.out.println();
    }

    public void postorder() {
        System.out.print("POSTORDER: ");

        if (head.ltag == '+')
            postorderR(head.left);

        System.out.println();
    }

    void postorderR(BNode<E> n) {

        if (n.ltag == '+')
            postorderR(n.left);

        if (n.rtag == '+')
            postorderR(n.right);

        System.out.print(n.info.toString()+" ");

    }

    BNode<E> predecessorPostorder(BNode<E> node) {
        if (node.rtag == '+')
            return node.right;
        while (node.ltag == '-')
            node = node.left;
        return node.left;
    }

    public void postorderOppNonRecursive() {
        if (head.ltag == '-')      // drvoto e prazno
            return;

        System.out.print("POSTORDER OPPOSITE (nonrecursive): ");

        BNode<E> p = head.left;

        while (true) {
            System.out.print(p.info.toString()+" ");
            p = predecessorPostorder(p);
            if (p == head.left)
                break;
        }
        System.out.println();

    }

}

public class ConsecutiveNumbers {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BTree<Integer> tree = new BTree<>();
        BNode<Integer> nodes[] = new BNode[N];
        for(int i = 0;i<N;i++){
            String line = br.readLine();
            String parts[] = line.split(" ");
            int index = Integer.parseInt(parts[0]);
            int value = Integer.parseInt(parts[1]);
            String where = parts[2];
            if(where.equals("ROOT")){
                nodes[index] = tree.makeRoot(value);
            }
            else{
                int whereTo = 1;
                if(where.equals("RIGHT"))
                    whereTo = 2;
                int whichNode = Integer.parseInt(parts[3]);
                nodes[index] = tree.addChild(nodes[whichNode], whereTo, value);
            }
        }

        System.out.println(check(tree));
    }

    private static boolean check(BTree<Integer> tree) {
        BNode<Integer> now = tree.head.left;
        while (now.ltag == '+'){
            now = now.left;
        }
        BNode<Integer> succ = tree.successorInorder(now);
        while (succ!=tree.head){
            if(succ.info - now.info != 1){
                return false;
            }
            now = succ;
            succ = tree.successorInorder(now);
        }
        return true;
    }

}
