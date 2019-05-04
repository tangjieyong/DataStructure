package quick_union;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 15:55
 * @Version 2.0
 */

/**
 * V1版本的弊端：所需要的只是寻找到有相同值的索引元素，没有必要遍历整个数组
 * V2版本：将连接的元素串在一起，索引元素保存连接着他的元素的索引
 * 两个元素进行连接本质上连接的是两个元素所在的根元素（索引与值相同的节点）
 */
public class UnionFind2  implements UF {
//    数组中的索引表示各个元素，数组索引对应的值保存着该元素的父节点
    private int[]parent;

    public UnionFind2(int size){
        parent=new int[size];
//        初始化数组，每个索引保存和索引相同的元素，表示每个元素的父节点指向自己
        for(int i=0;i<size;i++)
            parent[i]=i;
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

        parent[pRoot]=qRoot;


    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
