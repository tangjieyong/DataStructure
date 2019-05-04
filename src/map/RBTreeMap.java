package map;

import tree.balance.RBtree;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 9:16
 * @Version 1.0
 */
public class RBTreeMap <K extends Comparable<K>, V> implements Map<K, V> {
      private RBtree btree;

      public  RBTreeMap(){
          this.btree=new RBtree();
      }
    @Override
    public void add(K key, V value) {
        btree.add(key,value);
    }

    @Override
    public boolean contains(K key) {
        return btree.contains(key);
    }

    @Override
    public V get(K key) {
        return (V) btree.getValue(key);
    }

    @Override
    public void set(K key, V newValue) {
          btree.setValue(key,newValue);

    }

    @Override
    public V remove(K key) {
      return null;
    }

    @Override
    public int getSize() {
        return btree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
