package src.linklist;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/29 9:47
 * @Version 1.0
 */

/**
 * 链表中必须要要有变量来记录链表中的头节点
 * 若使用头指针的方式，因为可能会向链表的最前面插入数据所以这种方式必须要始终维护头指针
 * 也可以使用虚拟头节点，在链表实例化的时候创建一个值为null指向null的节点，表示链表的头节点
 * 当向链表最前面插入数据其实是向虚拟头节点的后面插入节点
 * 两种情况只是向链表首部添加元素操作会不同，虚拟头节点不管是向链表首部还是其他地方操作的逻辑都是一样的
 * 使用头指针的方式向链表的首部添加数据操作较特殊，其他位置的操作和使用虚拟头节点一样
 */
public class LinkList<V> {
    private class Node<V>{
        V value;
        Node next;

        public Node() {  value=null; next=null; }
        public Node(V value,Node next) { this.next=next; this.value=value; }
    }

    //使用头指针的方式
    private Node head;


    public LinkList() { head=null; }

    public void insertFirst(V value){
        //当链表中没有数据时向链表的首部添加数据只需要让头指针指向当前新节点即可
        if(head==null)
             head=new Node(value,null);

           else  head=new Node(value,head);
    }

    public void  iterate(){
        for(Node cur=this.head;cur.next!=null;cur=cur.next){
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
