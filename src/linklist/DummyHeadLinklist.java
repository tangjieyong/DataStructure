package src.linklist;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/29 10:40
 * @Version 1.0
 */
public class DummyHeadLinklist<V> {
    private class Node<V>{
        V value;
        Node next;

        public Node() {  value=null; next=null; }
        public Node(V value,Node next) { this.next=next; this.value=value; }
    }

    //创建虚拟头节点
      private Node dummyHead;
    public DummyHeadLinklist(){
        dummyHead=new Node();
    }

    //虚拟头节点是绝对的头节点，因此不管是向链表的首部添加元素还是向链表的其他位置添加数据操作都是一样
    public void insertFirst(V value){
        dummyHead.next=new Node(value,dummyHead.next);
    }

    public void  iterate(){
        for(Node cur=this.dummyHead.next;cur.next!=null;cur=cur.next){
            System.out.println(cur.value);
        }
    }

    public static void main(String[] args) {
        LinkList<Integer> linkList=new LinkList<>();
        for(int i=0;i<11;i++){
            linkList.insertFirst(i);
        }
        linkList.iterate();
    }

}
