package hashtable;

import java.util.TreeMap;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/3 10:37
 * @Version 1.0
 */
public class HashTable<K,V> {
   private TreeMap<K,V>[] hashTable;   //数组的每个索引位置保存的是TreeMap
   private int size;                   //TreeMap中元素的数量
   private int M;                      //数组的大小

   public static final int initCapacity=7;
   public static final int maxCapacity=10;
   public static final int minCapacity=2;


   public HashTable(){
       this.M=initCapacity;
       this.size=0;
       hashTable=new TreeMap[M];
       for(int i=0;i<hashTable.length;i++)  //为数组每个索引中的TreeMap初始化
           hashTable[i]=new TreeMap<>();
   }

   private int hash(K key){
       return (key.hashCode()& 0x7fffffff)%M;
   }

   public int getSize(){
       return  this.size;
   }


//   向哈希表中添加元素首先在数组中找到对应的查找表，根据的是哈希值取模后的结果
   public void add(K key, V value){
       TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
       if(kvTreeMap.containsKey(key))
           kvTreeMap.put(key,value);

        else{
            kvTreeMap.put(key,value);
            size++;
       }
        if(size >= M* maxCapacity)
            resize(2*M);
   }
   
   public V remove(K key){
       TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
       if(kvTreeMap.containsKey(key)){
            size--;
           if(size>0 && size<minCapacity *M)
               resize(M /2);
            return  kvTreeMap.remove(key);
       }else{  return  null;}
   }

   public void set(K key,V value){
       TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
       if(kvTreeMap.containsKey(key))
           kvTreeMap.put(key,value);
       else{ throw new IllegalArgumentException("对应的值不存在！");}
   }

   public boolean contains(K key){
       return hashTable[hash(key)].containsKey(key);
   }

   public V get(K key){
       TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
       return  kvTreeMap.get(key);
   }

   private void resize(int newCapacity){
//       创建一个临时的更大的数组
         TreeMap<K,V>[] newHashTable=new TreeMap[newCapacity];
//         为该数组的每一个索引位置初始化
         for(int i=0;i<newCapacity;i++)
             newHashTable[i]=new TreeMap<>();

             this.M=newCapacity;

//        把原数组中的值复制当该数组中
         for(int i=0;i<hashTable.length;i++){
//             newHashTable[i]=hashTable[i];   不能这样直接的赋值，因为查找表在原数组的索引位置与在新数组的索引位置不一样
             for(K key:hashTable[i].keySet()){
//        获取原数组中的key值，结果哈希运算后放入新数组的指定位置
                 newHashTable[hash(key)].put(key,hashTable[i].get(key));
             }
         }
         this.hashTable=newHashTable;

   }
}
