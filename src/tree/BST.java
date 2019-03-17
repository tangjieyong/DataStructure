package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*如果只是单纯的比较两个数是否相同可以直接重写hashCode()方法
 但若要区分两个数之间的大小则必须让类继承Comparable,并使用compareto方法去比较
* */
public class BST<E extends Comparable<E>>{
    /*java基础知识回忆：
    *     若类中使用自定义参数化类型E，那么类的声明上也要加上相应的泛型限定*/
    private class Node{
          E e;
          Node left;
          Node right;
  /*向bst中添加元素都是往末尾添加所以有参构造上不用为左右孩子赋值*/
         public Node(E e){
             this.e=e;
             left=null;
             right=null;
         }
    }
    private Node root;
    private int size;

    public BST(){
        root=null;
        size=0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /*向BST中添加元素采用递归的方式*/
    public void add(E e){
        root=add(root,e);
    }
    private Node add(Node node,E e){
        if (node == null){
            size++;
            return new Node(e);
        }


        /*如果节点为空就返回一个新的节点，可能是bst为空或者递归到树的末尾*/

        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } /*else {  如果是这种逻辑那么小于或者等于当前的值都会被添加到树的左边
            node.left = add(node.left, e);
        }*/
        else if(e.compareTo(node.e)<0){//而这种逻辑则会保持树中元素的唯一性
            node.left=add(node.left,e);
        }
        return node;

    }
    /*使用递归的方式来检测树中是否包含某个数据
    * 从根节点出发如果当前节点为所要数据就返回true,如果不是就根据比较情况查看其左右子树
    * 如果当前的节点为空表示遍历整个树都没有与此相同的节点便可以返回false*/
    public boolean contains(E e){
        if (isEmpty()){
            throw new RuntimeException("bst is null!!!!");
        }else {
            return contains(root,e);
        }
    }
    private boolean contains(Node node,E e){
        if (node==null)
            return false;
        if(e.compareTo(node.e)==0)
            return true;
        if (e.compareTo(node.e)>0)
            return contains(node.right,e);
        else{return contains(node.left,e);}
    }

    /*前序遍历*/
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node==null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /*bst的中序遍历本质上是将树从小到大升序排列*/
    public void midOrder(){
        midOrder(root);
    }
    private void midOrder(Node node){
        if(node==null)
            return;
        midOrder(node.left);
        System.out.println(node.e);
        midOrder(node.right);
    }

    /*bst的后序遍历*/
    public void tailOrder(){

    }
    private void tailOrder(Node node){
        if(node==null)
            return;
        tailOrder(node.left);
        tailOrder(node.right);
        System.out.println(node.e);
    }

    /*基于深度遍历和前序遍历的效果一致*/
    public void deepOrder(){
        //在遍历之前先判断树是否为空
        if(isEmpty())
            throw new RuntimeException("bst为空无法遍历！！");
        Stack<Node> stack=new Stack<>();
        stack.push(root);//首先将头放到栈中
        while (!stack.isEmpty()){
            Node del = stack.pop();//注意此处的写法，取出此时栈顶的元素，再将栈顶元素的左右孩子依次入栈
            System.out.println(del.e);
            if(del.right!=null){
                stack.push(del.right);
            }
           if(del.left!=null){
               stack.push(del.left);
           }

        }
    }

    /*基于广度遍历（层序遍历）*/
    public void levelOrder(){
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node del = queue.remove();
            System.out.println(del.e);
            if(del.left!=null)
                queue.add(del.left);
            if(del.right!=null)
                queue.add(del.right);
        }
    }

    /*找出bst中最大值*/
    public E getMax(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty");
        }
        Node max = getMax(root);
        return max.e;
    }

    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return getMax(node.right);
        }
    }

    /*获取bst中的最小值*/
    public E getMin(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty");
        }
        Node min = getMin(root);
        return min.e;
    }
    private Node getMin(Node node){
        if(node.left==null){
            return node;
        }else{
            return getMin(node.left);
            /*意此处只是简单的将最后一个元素进行返回，对于bst而言并没有在结构上的变化因此不需要使用node.left来接收*/
        }
    }

    /*删除bst中的最小值*/
    public E removeMin(){
        E min = getMin();
        root=removeMin(root);
        return min;
    }
    /*删除bst中最值所在的节点类似于链表中删除某个元素
     * bst中采取的策略是递归回归时跳过*/
    private Node removeMin(Node node){
        /*if(node.left.left==null){   这种思路是错误的因为如果第一个node.next为空便会产生空指针异常
            return node.left.right;*/
        if(node.left==null){
            size--;
            return node.right;
        }else{
           node.left=removeMin(node.left);
           /*此处删除树中的节点树的结构会进行重组，因此要使用node.left来接收*/
           return node;
        }
    }

    /*删除bst中的最大值节点*/
    public E removeMax(){
        E max = getMax();
        root=removeMax(root);
        return max;
    }
    private Node removeMax(Node node){
        if(node.right==null){
            size--;
            return node.left;
        }else{
            node.right=removeMax(node.right);
            return node;
        }
    }

    /*删除bst中任意的数值对应的节点*/
    public void remove(E e){

          root=remove(root,e);
    }
    private Node remove(Node node,E e){
        if( node == null )
            return null;
        //先通过递归找到待删除元素的位置
       if(e.compareTo(node.e)>0){
           node.right=remove(node.right,e);
           return node;
       }
       else if(e.compareTo(node.e)<0){
           node.left=remove(node.left,e);
           return node;
       }else{//找到了这个数据执行删除操作
           if(node.left==null){//待删除的数据是最小值
               size--;
               return node.right;
           }else if (node.right==null){//待删除的数据是最大值
               size--;
               return node.left;
           }else{//当待删除的数据左右子树都存在时
               Node delNode=getMin(node.right);
               /*以下两条代码的顺序不能倒置否则会产生逻辑错误*/
               delNode.right=removeMin(node.right);
               delNode.left=node.left;
               return delNode;
           }

       }

    }

    public static void main(String[] args) {
        BST<Integer> bst=new BST<>();
        int[]nums={5,7,2,6,4,8,9,1};
        for(int i:nums){
            bst.add(i);
        }
      bst.remove(7);
      //  bst.removeMax();
        bst.midOrder();

    }
}
