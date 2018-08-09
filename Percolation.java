
public class Percolation {
    private int n;
    private UF uf;
    private int open;
    private boolean[] state;

// public methods 
    // constructor
    public Percolation(int grid){
        n = grid;
        uf = new UF(grid + 2);
        open = 0;
        state = new boolean[n * n + 2];
        for (int i = 0; i < n * n; ++i){
            state[i] = false;
        }
        state[n * n] = true;
        state[n * n + 1] = true;
    }
    // takes row, col and change state to open and unite with nearby open cells
    public void open(int row, int col){
        int[] near = nearby(row, col);
        int index = toIndex(row, col);
        if (!state[index]){
            state[index] = true;
            ++open;
            for (int i = 0; i < 4; ++i){
                int neighbor = near[i];
                if (state[neighbor]){
                    uf.union(index, neighbor);
                }
            }
        }
    }
    public boolean isOpen(int row, int col){
        return state[toIndex(row, col)];
    }
    public boolean isFull(int row, int col){
        return uf.connected(toIndex(row, col), n * n);
    }
    public int numberOfOpenSites(){
        return open;
    }
    public boolean percolates(){
        return uf.connected(n * n, n * n + 1);
    }
    public static void main(String[] args){

    }

// private helper methods

    // takes row and column, returns index
    private int toIndex(int r, int c){
        return (r - 1) * n + (c - 1);
    }
    // takes index, returns row and column
    private int[] toGrid(int i){
        int ret[] = new int[2];
        ++i;
        ret[0] = (i - 1 - ((i - 1) % n)) / n + 1;
        ret[1] = i % n;
        return ret;
    }
    // takes row and column and returns nearby cells
    private int[] nearby(int r, int c){
        int i = toIndex(r, c);
        int[] rst = new int[4];
        int[] grid = toGrid(i);
        if (grid[0] == 1){
            if (grid[1] == 1){
                rst[0] = n * n; 
                rst[1] = i + 1;
                rst[2] = i + n;
                rst[3] = i;
            }else if(grid[1] == n){
                rst[0] = n * n; 
                rst[1] = i;
                rst[2] = i + n;
                rst[3] = i - 1;
            }else{
                rst[0] = n * n; 
                rst[1] = i + 1;
                rst[2] = i + n;
                rst[3] = i - 1;
            }
        }else if(grid[0] == n){
            if (grid[1] == 1){
                rst[0] = i - n; 
                rst[1] = i + 1;
                rst[2] = n * n + 1;
                rst[3] = i;

            }else if(grid[1] == n){
                rst[0] = i - n; 
                rst[1] = i;
                rst[2] = n * n + 1;
                rst[3] = i - 1;
            }else{
                rst[0] = i - n; 
                rst[1] = i + 1;
                rst[2] = n * n + 1;
                rst[3] = i - 1;
            }
        }else{
            if (grid[1] == 1){
                rst[0] = i - n; 
                rst[1] = i + 1;
                rst[2] = i + n;
                rst[3] = i;
            }else if(grid[1] == n){
                rst[0] = i - n; 
                rst[1] = i;
                rst[2] = i + n;
                rst[3] = i - 1;
            }else{
                rst[0] = i - n; 
                rst[1] = i + 1;
                rst[2] = i + n;
                rst[3] = i - 1;
            }
        }
        return rst;
    }
}