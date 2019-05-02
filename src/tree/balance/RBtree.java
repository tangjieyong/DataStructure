package tree.balance;

import java.util.ArrayList;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/2 11:55
 * @Version 1.0
 */
public class RBtree<K extends Comparable<K>,V > {

    public static final  boolean RED=true;
    public static final  boolean BLACK=false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        /**
         * 红黑树是基于2-3树的思想实现的，在2-3树中添加一个新的节点不是直接添加到空节点的位置，而是在已有的
         * 2-节点中融合出一个3-节点，再添加一个节点，在3-节点的基础上暂时形成4-节点，再将4-节点分化为三个2-节点
         * 对应的在红黑树中新创建的节点都是红色的
         * @param key
         * @param value
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.color=RED;
        }
    }

    private Node root;
    private int size;

    public RBtree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 判断当前节点的颜色，
     * @param node 当前节点
     * @return  返回当前节点的颜色，若节点为空就返回BLACK
     */
    private boolean isRed(Node node){
        if(node==null)
            return BLACK;
        return node.color;
    }

    /**
     * 将以当前节点为根节点的子树进行左旋转
     * @param node 当前节点
     * @return 返回旋转后新的当前节点
     */
    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
          Node x=node.right;
//          左旋转
          node.right=x.left;
          x.left=node;
//          变色
        x.color=node.color;//x处在node之前的位置因此x的颜色要变成node的颜色
        node.color=RED;//node此时为当前子树的新节点因此需要变成红色

        return x;
    }

    /**
     * 右旋转
     * @param node
     * @return
     *
     */
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
         Node x=node.left;
         node.left=x.right;
         x.right=node;
         x.color=node.color;
         node.color=RED;
         return  x;
    }

    /**
     * 颜色变换
     * 模拟的是2-3树中的4-节点分解为3个2-节点，4-节点两侧的节点在原先的位置上分化为两个新节点
     * 中间的节点与父节点进行融合，反应在红黑树中就是当前节点变成红色，左右子节点变成黑色
     * @param node
     */
    private void flipColors(Node node){
         node.color=RED;
         node.left.color=BLACK;
         node.right.color=BLACK;
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


        /**
         * 添加完新的节点之后进行树的回溯，根据当前节点的属性执行变换链
         */

        if(isRed(node.right) && !isRed(node.left))
               node=leftRotate(node);

        if(isRed(node.left) && isRed(node.left.left))
            node=rightRotate(node);

        if(isRed(node.left) && isRed(node.right))
            flipColors(node);

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
            return  getValue(key, node.left);

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
            node.left=removeMin(node.left);
            return node;
        }
    }

    public void remove(K key){
        remove(root,key);

    }

    private Node remove(Node node,K key){
        if(node==null)
            return null;

        if(key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
            return node;
        }
        else if(key.compareTo(node.key)<0){
            node.left=remove(node.left,key);
            return node;
        } else {
            if(node.left==null){//max
                Node delNode=node.right;
                node.right=null;
                size--;
                return delNode;
            }
            if(node.right==null){//min
                Node delNode=node.left;
                node.left=null;
                size--;
                return delNode;

            }

            Node replaceNode=getMin(node.right);
            replaceNode.right=removeMin(node.right);
            replaceNode.left=node.left;
            node.left=node.right=null;
            return replaceNode;
        }
    }



    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBtree<String, Integer> map = new RBtree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.setValue(word, map.getValue(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.getValue("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.getValue("prejudice"));
        }

        System.out.println();
    }
}






