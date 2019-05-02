package tree.balance;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/4/28 15:15
 * @Version 1.0
 */
public class AVL<K extends Comparable<K>,V > {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;//节点的高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.height=1;//高度默认为1
        }
    }

    private Node root;
    private int size;

    public AVL() {
        this.root = null;
        this.size = 0;
    }


    public Boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public void add(K key, V value) {
        root = add(key, value, root);

    }

    private Node add(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = add(key, value, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(key, value, node.left);
        } else {
            node.value = value;
        }
//        每次添加新的节点都回溯更新
//         每次添加新的节点都回溯更新先先前节点的高度值
//        前节点的平衡因子
        node.height=getHeight(node)+1;
        int balanceFactor = getBalanceFactor(node);
//        如果节点当前节点的平衡因子大于1对树进行旋转

        if(balanceFactor>1 && getBalanceFactor(node.left)>=0){ //RR
            return getRightRotate(node);
        }

        if(balanceFactor<-1 && getBalanceFactor(node.right)<=0){//LL
            return getLeftRotate(node);
        }

        if(balanceFactor>1 && getBalanceFactor(node.left)<0){ //LR
                 node.left= getLeftRotate(node.left);
                 return getRightRotate(node);
        }
        if(balanceFactor<-1 && getBalanceFactor(node.right)>0){   //RL
             node.right = getRightRotate(node.right);
             return getLeftRotate(node);
        }
        return node;
    }

    public V getValue(K key) {
        Node node = getValue(key, root);
        return (node == null) ? null : node.value;
    }

    public void setValue(K key, V value) {
        Node node = getValue(key, root);
        if (node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException("不存在对应的键对应的节点");
        }
    }

    private Node getValue(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            return getValue(key, node.right);
        } else {
            return getValue(key, node.left);
        }
    }

    public boolean contains(K key) {
        return getValue(key) == null ? false : true;
    }



    private Node getMin(Node node) {
        if (node.left == null)
            return node;
        return getMin(node.left);

    }

    private Node getMax(Node node) {
        if (node.right == null)
            return node;

        return getMax(node.right);
    }

    private Node removeMin(Node node){
        if(node.left==null){
            Node delNode=node.right;
            node.right=null;
            size--;
            return delNode;
        }else{
            node.left= removeMin(node.left);
            return node;
        }
    }

    /**
     * 返回节点的高度值
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node!=null){
            return node.height;
        }
          return 0;
    }

    /**
     * 返回节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node!=null){
            return getBalanceFactor(node.left)-getBalanceFactor(node.right);
        }
        return 0;
    }

    /**对树进行RR操作
     *
     * @param y
     * @return
     */
    private Node getRightRotate(Node y){
        Node x=y.left;
        Node T3=x.right;

        x.right=y;
        y.left=T3;

        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        return  x;
    }

    /**
     * 对树进行LL操作
     * @param y
     * @return
     */
    private Node getLeftRotate(Node y){
        Node x=y.right;
        Node T3=x.left;

        x.left=y;
        y.right=T3;

        x.height=Math.max(getHeight(x.left),getHeight(x.right));
        y.height=Math.max(getHeight(y.left),getHeight(y.right));
        return x;
    }

    public V remove(K key){
       Node node= remove(root,key);
       if(node !=null){
           root=remove(root,key);
           return node.value;
       }
         return null;
    }

    private Node remove(Node node,K key){
        if(node==null)
            return null;

           Node retNode;
        if(key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
            retNode= node;
        }else if(key.compareTo(node.key)<0){
            node.left= remove(node.left,key);
            retNode= node;
        } else {
            if(node.left==null){//max
                Node delNode=node.right;
                node.right=null;
                size--;
                retNode= delNode;
            }
            if(node.right==null){//min
                Node delNode=node.left;
                node.left=null;
                size--;
                retNode= delNode;

            }

            Node replaceNode=getMin(node.right);
            replaceNode.right=removeMin(node.right);
            replaceNode.left=node.left;
            node.left=node.right=null;
            retNode= replaceNode;
        }

        if(retNode==null)
            return null;

        retNode.height=getHeight(retNode)+1;

        if(getBalanceFactor(retNode)>1 && getBalanceFactor(retNode.left)>=0){//RR
            return getRightRotate(retNode);
        }

        if(getBalanceFactor(retNode)<-1 && getBalanceFactor(retNode.right)<0){//LL
            return getLeftRotate(retNode);
        }

        if(getBalanceFactor(retNode)>1 && getBalanceFactor(retNode.left)<0){//LR
             retNode.left=getLeftRotate(retNode.left);
             return getRightRotate(retNode);
        }

        if(getBalanceFactor(retNode)<-1 && getBalanceFactor(retNode.right)>=0){//RL
               retNode.right=getRightRotate(retNode.right);
               return getLeftRotate(retNode);
        }
        return retNode;
    }

}

