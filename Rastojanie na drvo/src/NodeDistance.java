import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

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


    @Override
    public String toString() {
        return "" + info;
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

    public BNode<E> findNode(BNode<E> root, E x){
        if(root == null)
            return null;
        if(root.info.equals(x))
            return root;
        BNode<E> left = findNode(root.left, x);
        if(left!=null)
            return left;
        else
            return findNode(root.right,x);
    }

    public BNode<E> find(E x){
        return findNode(root, x);
    }

    public BNode<E> LCA(BNode<E> root, BNode<E> temp1, BNode<E> temp2) {
        if(root == null)
            return null;
        if(root == temp1 || root == temp2)
            return root;
        BNode<E> left = LCA(root.left, temp1, temp2);
        BNode<E> right = LCA(root.right, temp1, temp2);
        if(left!= null && right!=null)
            return root;
        if(left!=null)
            return left;
        return right;
    }

    public int rastojanie(BNode<E> lca, BNode<E> node) {
        if(lca == null)
            return 1000000;
        if(lca== node)
            return 0;
        else
            return Math.min((rastojanie(lca.right,node)+1),(rastojanie(lca.left,node)+1));
    }
}

public class NodeDistance {

    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<String> nodes[] = new BNode[N];
        BTree<String> tree = new BTree<String>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<String>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = st.nextToken();
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


        int cases = Integer.parseInt(br.readLine());
        for (int l = 0; l < cases; l++) {
            String split[] = br.readLine().split(" ");             //??
            String from = split[0];
            String to = split[1];
            BNode<String> temp1 = null;
            BNode<String> temp2 = null;
            // Vasiot kod ovde
            for (int w = 0; w < nodes.length; w++) {
                if (nodes[w].info.equals(from))
                    temp1 = nodes[w];
                if (nodes[w].info.equals(to))
                    temp2 = nodes[w];
            }
            if (temp1 == null || temp2 == null) {
                System.out.println("ERROR");
                continue;
            }

            BNode<String> lca = tree.LCA(tree.root, temp1, temp2);
            System.out.println((tree.rastojanie(lca, temp1)+tree.rastojanie(lca, temp2))*2);
        }
        br.close();


    }

}
