package map;

import tree.balance.AVL;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVL<K, V> avl;

    public AVLMap(){
        avl = new AVL<>();
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }

    @Override
    public void add(K key, V value){
        avl.add(key, value);
    }

    @Override
    public boolean contains(K key){
        return avl.contains(key);
    }

    @Override
    public V get(K key){
        return avl.getValue(key);
    }

    @Override
    public void set(K key, V newValue){
        avl.setValue(key,newValue);
    }

    @Override
    public V remove(K key){
        return avl.remove(key);
    }
}
