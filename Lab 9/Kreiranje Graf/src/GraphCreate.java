import java.util.Scanner;
import java.util.Stack;

class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    public Graph(int num_nodes, E[] nodes) {
        this.num_nodes = num_nodes;
        this.nodes = nodes;
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    int adjacent(int x,int y)
    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }

    void addEdge(int x,int y)
    {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }

    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }

    // Moze i ne mora?
    E get_node_value(int x)
    {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, E a)
    {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x]=a;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }


    @Override
    public String toString() {
        String ret="\n";
        for(int i=0;i<num_nodes;i++){
            for(int j=0;j<num_nodes;j++)
                ret+=adjMat[i][j]+" ";
            ret+="\n";
        }
        return ret;
    }


}

public class GraphCreate{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.next();
        int nodes = scan.nextInt();
        Character niza[] = new Character[nodes];
        char c = 'A';
        for(int i = 0;i<nodes;i++){
            niza[i] = c++;
        }
        Graph<Character> g = new Graph<>(nodes, niza);

        for(int i = 0;i<N;i++){
            String line = scan.nextLine();
            String parts[] = line.split(" ");
            switch(parts[0]){
                case "ADDEDGE": g.addEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "DELETEEDGE": g.deleteEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "ADJACENT": System.out.println(g.adjacent(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                    break;
                case "PRINTMATRIX": System.out.print(g.toString());
                    break;
                case "PRINTNODE": System.out.println(g.get_node_value(Integer.parseInt(parts[1])));
                    break;
            }
        }
    }
}