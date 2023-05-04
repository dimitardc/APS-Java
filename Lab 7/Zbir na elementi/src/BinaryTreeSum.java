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
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    /*public BNode<E> findNode(BNode<E> root, E x){
            if(root == null)
                return null;
            if(root.info.equals(x))
                return root;
            BNode<E> left = findNode(root.left, x);
            if(left!=null)
                return left;
            else
                return findNode(root.right,x);
        }*/

}

public class BinaryTreeSum {


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

        for (i=0; i<N; i++)
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

        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod

        BNode<Integer> foundNode = findNode(tree.root, baranaVrednost);

        //another method for finding a specific node in a tree. Implemented inside the BTree<E> class
        //BNode<Integer> foundNode = tree.find(tree.root, baranaVrednost);

        System.out.print( lesserNodesSum( foundNode.left, foundNode.info ) + " ");
        System.out.print( greaterNodesSum( foundNode.right, foundNode.info) );
    }

    static  BNode<Integer> findNode( BNode<Integer> node, int value ) {     //METHOD FOR FINDING A CARTAIN VAL IN A TREE

        if ( node == null ) // ako stigne do list vrati null
            return null;

        if( node.info.equals(value) ) // ako ja najde vr zemi go
            return node;

        return findNode( node.left, value ) != null ? findNode( node.left, value ) : findNode( node.right, value );
        // ili prviot, ili vtoriot , ili i dvara se null zatoa sto ne go sodrzat el

    }

    static  int greaterNodesSum( BNode<Integer> pok, int value) {

        if ( pok == null ) // ako stigne do list vrati null
            return 0;

        if( pok.info > value ) // ako najde pogolema vr od baranata
            return pok.info + greaterNodesSum( pok.left, value ) + greaterNodesSum( pok.right, value );

        return greaterNodesSum( pok.left, value ) + greaterNodesSum( pok.right, value );

    }

    static  int lesserNodesSum( BNode<Integer> pok, int value) {

        if ( pok == null ) // ako stigne do list vrati null
            return 0;

        if( pok.info < value ) // ako najde pomala vr od baranata
            return pok.info + lesserNodesSum( pok.left, value ) + lesserNodesSum( pok.right, value );

        return lesserNodesSum( pok.left, value ) + lesserNodesSum( pok.right, value );

    }
}