public class UF{
    public int id[];
    private int size[];

    public UF (int N){
        id = new int[N];
        for (int i = 0; i < N; ++i)
            id[i] = i;
        size = new int[N];
        for (int i = 0; i < N; ++i)
            size[i] = 0;
    }
    public boolean connected (int p, int q){
        return root(p) == root(q);
    }
    public void union (int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (size[i] < size[j])
            id[i] = j;
        else if (size[i] > size[j])
            id[j] = i;
        else{
            id[j] = i;
            size[i]++;
        }

    }
    private int root(int i){
        while (i != id[i])
            i = id[i];
        return i;
    }

}