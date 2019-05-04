package quick_union;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 21:26
 * @Version 3.0
 */

/** 并查集的优化本质都是让树的深度降低
 *  基于size的优化
 * 可以在V2的基础上对并查集进行优化
 * 在V2中两个节点进行合并的根本在于找到让两个节点的根节点进行合并
 * 这里的不足在于，没有根据两个节点所在的子树结构进行合并，而是自定义的将一个子树和合并到另一个子树之上
 * 这样可能会让树的高度变得越来越深，基于size的优化可以根据两棵子树size的不同而选择合并的顺序
 */
public class UnionFind3  implements UF {
    //    数组中的索引表示各个元素，数组索引对应的值保存着该元素的父节点
    private int[]parent;
//    该数组用于保存每一个节点包含的子节点个数
    private int[]sz;

    public UnionFind3(int size){
        parent=new int[size];
        sz=new int[size];
//        初始化数组，每个索引保存和索引相同的元素，表示每个元素的父节点指向自己
        for(int i=0;i<size;i++){
            parent[i]=i;
            sz[i]=1;  //初始状态每一个节点包含子节点的个数都为1
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

//        让size较小的根节点合并到size较大的根节点上
       if(sz[pRoot]>sz[qRoot]){
           pRoot=parent[qRoot];
           sz[pRoot]+=sz[qRoot];
       }else{
            qRoot=parent[pRoot];
            sz[qRoot]+=sz[qRoot];
       }
    }
    @Override
    public int getSize() {
        return parent.length;
    }
}

