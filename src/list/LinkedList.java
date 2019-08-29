package src.list;

/**
 * 用递归向链表中添加数据链表中不用设立虚拟头节点
 * 用迭代的方式往链表的任意位置添加数据推荐使用虚拟头节点
 * @param <E>
 */
public class LinkedList<E> {
    private class Node{
        private Node next;
        private E e;

        public Node(Node next,E e){
            this.next=next;
            this.e=e;
        }

        public Node(E e){
            this(null,e);
        }
    }

    private Node head;

    public LinkedList(){
        head=null;
    }

    public void add(E e){
            head=adds(head,e);
    }
    private Node adds(Node node, E e){//node为当前的节点
        if(node==null)//当链表的为空或者链表到达链表的最后一项时就创建一个节点
            return new Node(e);
         else{//如果当前节点不为空就寻找下一个节点，递归判断下一个节点是否为空
             node.next=adds(node.next,e);//因为可能存在return新的节点所以使用node.next来接收
             return node;//接收到此次递归返回的节点然后返回给上一次调用

        }
    }

    public void print(){
        Node node=head;
        while (node!=null){
            System.out.println(node.e);
            if (node.next==null){
                break;//缺少此处的逻辑判断会导致空指针异常
            }
            node=node.next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list= new LinkedList<>();
        for (int i=1;i<=5;i++){
            list.add(i);
        }
        list.print();
    }
}
