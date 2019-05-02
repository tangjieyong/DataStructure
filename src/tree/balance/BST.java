package tree.balance;


/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/4/28 15:14
 * @Version 1.0
 */
public class BST<K extends Comparable<K>,V > {
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
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

}























