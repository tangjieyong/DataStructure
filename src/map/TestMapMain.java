package map;

import hashtable.HashTable;
import tree.balance.FileOperation;
import tree.balance.RBtree;

import java.util.ArrayList;

public class TestMapMain {

    private static double testMap(HashTable<String, Integer> table, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words){
                if(table.contains(word))
                    table.set(word, table.get(word) + 1);
                else
                    table.add(word, 1);
            }

            System.out.println("Total different words: " + table.getSize());
            System.out.println("Frequency of PRIDE: " + table.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + table.get("prejudice"));
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    private static double testMap(Map<String, Integer> map, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words){
                if(map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        HashTable<String, Integer> avlMap = new HashTable<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("HashTable: " + time3 + " s");

        System.out.println("============================");

        AVLMap<String,Integer> avlMap1=new AVLMap<>();
        double time4 = testMap(avlMap1, filename);
        System.out.println("AVL Map: " + time4 + " s");

        System.out.println("============================");

        RBTreeMap<String,Integer> avlMap2=new RBTreeMap<>();
        double time5 = testMap(avlMap2, filename);
        System.out.println("RBTreeMap: " + time5 + " s");

        System.out.println("============================");

        BSTMap<String,Integer> avlMap3=new BSTMap<>();
        double time6 = testMap(avlMap3, filename);
        System.out.println("RBTreeMap: " + time6 + " s");
    }
}
