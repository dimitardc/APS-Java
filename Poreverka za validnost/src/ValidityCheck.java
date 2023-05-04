import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        }
        else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public int ValidityCheck(BNode<Integer> root) {
        if (root != null) {
            if(root.left != null)
                if(root.left.info > root.info)
                    return 1;
            if(root.right != null)
                if(root.right.info > root.info)
                    return 1;
            return (ValidityCheck(root.left) + ValidityCheck(root.right));
        }
        else {
            return 0;
        }
    }
}

public class ValidityCheck {

    /*public static boolean checkValidity(BNode<Integer> node){
        if(node == null){
            return true;
        }
        if(node.left == null && node.right == null){
            return true;
        }
        // 2 deca
        if(node.left != null && node.right != null && node.info >= node.left.info && node.info >= node.right.info){
            return checkValidity(node.left) && checkValidity(node.right);
        }
        //levo dete
        if(node.left != null && node.right == null && node.info >= node.left.info){
            return checkValidity(node.left);
        }
        //desno dete
        if(node.left == null && node.right != null && node.info >= node.right.info){
            return checkValidity(node.right);
        }

        return false;

    }*/

    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        br.close();
        int killme;
        // vasiot kod ovde
        killme = tree.ValidityCheck(tree.root);
        if(killme >= 1)
            System.out.println("false");
        else
            System.out.println("true");


        /*if(checkValidity(tree.root) == true){
            System.out.println("true");
        }else{
            System.out.println("false");
        }*/

    }
}

