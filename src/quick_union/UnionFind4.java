package quick_union;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 21:47
 * @Version 4.0
 */

/**
 * 基于rank对并查集的优化，让层数少（深度较小）的树拼接到深度较深的树
 */
public class UnionFind4  implements UF {
    //    数组中的索引表示各个元素，数组索引对应的值保存着该元素的父节点
    private int[]parent;
//    用于保存树的层数
    private int[]rank;

    public UnionFind4(int size){
        parent=new int[size];
        rank=new int[size];
//        初始化数组，每个索引保存和索引相同的元素，表示每个元素的父节点指向自己
        for(int i=0;i<size;i++){
            parent[i]=i;
            rank[i]=1;
        }

    }

    /**
     * 查找元素p对应的集合，即根节点的值
     * @param p
     * @return
     */
    private int find(int p){
        if(p<0 || p>=parent.length)
            throw new IllegalArgumentException("wrong index");
//        如果P索引对应的值不等于自身，就寻找他指向的那个节点，循环直到查询到根节点
        while (p!=parent[p]){
            p=parent[p];
        }
        return p;

    }
    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot=find(p);
        int qRoot=find(q);
        if(pRoot==qRoot)
            return;

//        进行合并的时候让层数低的树合并到层数高的树下
        if(rank[pRoot] > rank[qRoot]){
            pRoot=parent[qRoot];
//            低层树合并到高层的树下其层数不会发生改变
        }else if(rank[pRoot]<rank[qRoot]){
             qRoot=parent[pRoot];
        }else{
//            若两棵树的高度相同，任意合并即可，但要维护层数
            qRoot=parent[pRoot];
            rank[qRoot]++;
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
