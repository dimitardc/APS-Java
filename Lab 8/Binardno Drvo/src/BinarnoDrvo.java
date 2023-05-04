import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree<E extends Comparable<E>> {
    private BNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public BNode<E> getRoot() {
        return root;
    }

    private BNode<E> insert(E x, BNode<E> t) {
        //System.out.print(" -1- ");
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ;// Duplicate: do nothing
        //System.out.println(t.info);
        return t;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private BNode<E> findMax(BNode<E> t) {
        if (t == null)
            return null;
        else if (t.right == null)
            return t;
        return findMax(t.right);
    }

    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;
        if (x.compareTo(t.info) < 0)
            return find(x, t.left);
        else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else
            return t;//equals =  finded !!
    }

    public BNode<E> find(E x) {
        return find(x, root);
    }

    private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
            return t;
        if (x.compareTo(t.info) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else if (t.left != null)
            return t.left;
        else
            return t.right;
        return t;
    }

    public void remove(E x) {
        root = remove(x, root);
    }

    public int height() {
        BNode<Integer> tmp = (BNode<Integer>) this.root;
        return findHeight(tmp);
    }

    public int heightOf(E elem) {
        BNode node = find(elem, root);
        return findHeight(node);
    }

    public int findDepth(int level) {
        BNode<Integer> tmp = (BNode<Integer>) this.root;
        return findDepth(tmp, 0, level);
    }

    public int findDepth(BNode<Integer> node, int current, int entered) {
        if (node == null) {
            return 0;
        }
        if (current == entered)
            return 1;
        return findDepth(node.left, current + 1, entered) + findDepth(node.right, current + 1, entered);

    }

    public int findHeight(BNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        else {
            int leftHeight = findHeight(node.left);
            int rightHeight = findHeight(node.right);
            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }

    }

    public void preorder() {
        System.out.print("PREORDER ");
        BNode<Integer> tmp = (BNode<Integer>) root;
        preorderR(tmp);
    }

    public void preorderR(BNode<Integer> n) {
        if (n == null)
            return;
        System.out.print(n.info + " ");
        //System.out.println(n.left.info);
        preorderR(n.left);
        preorderR(n.right);
    }
}

public class BinarnoDrvo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int N = Integer.parseInt(br.readLine());
        int array[] = new int[N];
        for (int i = 0; i < N; i++) {
            int z = Integer.parseInt(br.readLine());
            tree.insert(z);
            array[i] = z;
        }
        int baran = Integer.parseInt(br.readLine());
        int level = tree.heightOf(baran);
        System.out.println(level);
        System.out.println(tree.findDepth(level));

    }
}