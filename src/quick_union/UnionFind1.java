package quick_union;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 15:35
 * @Version 1.0
 */

/**  V1版本的本质在于在数组中把所有连接的元素都 "打上相同的标签"
 * v1版本的并查集实现思路是，初始状态下所有的元素都相互独立，因此对应在数组中，每一个索引保存着不同的值
 * 若两个元素连接就遍历数组，让数组中与其中一个元素值相等的元素的值也与另外一个元素相等    O(n) 复杂度
 * 判断两个元素（索引）是否连接只要判断索引处的值是否相同即可
 */
public class UnionFind1 implements UF {
    private int[]ids;

    public UnionFind1(int size){
        ids=new int[size];
        for(int i=0;i<size;i++)
            ids[i]=i;
    }

    private int find(int id){
       if(id<0 || id>=ids.length)
           throw new IllegalArgumentException("index is Illegal!");
       return ids[id];
    }
    @Override
    public int getSize() { return ids.length; }


    @Override
    public boolean isConnected(int p, int q) { return find(p)==find(q); }

    @Override
    public void unionElements(int p, int q) {
             int pid=find(p);
             int qid=find(q);
             if(pid==qid) return;
        for(int i=0;i<ids.length;i++){
            if(ids[i]==pid)
                ids[i]=qid;
        }
    }
}
