package map;

import tree.balance.BST;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 9:37
 * @Version 1.0
 */
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V>{
    private BST<K,V> bst;

    public BSTMap(){
        bst=new BST<>();
    }
    @Override
    public void add(K key, V value) {
        bst.add(key,value);
    }

    @Override
    public boolean contains(K key) {
        return bst.contains(key);
    }

    @Override
    public V get(K key) {
        return bst.getValue(key);
    }

    @Override
    public void set(K key, V newValue) {
           bst.setValue(key,newValue);
    }

    @Override
    public V remove(K key) {
        return  null;
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
