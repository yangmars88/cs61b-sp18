package synthesizer;

import java.util.Iterator;

interface BoundedQueue<T> extends Iterable<T>  {

    public int capacity();     //return size;

    public int fillCount();    //return count of current items;

    public void enqueue(T x);  //add x to queue;

    T dequeue();

    T peek();

    @Override
    Iterator<T> iterator();  // this is the right format in interface, because we cannot implement iterator() method in interface

    //java 8 feature: default method is implemented method in interface which can be used directly by implementing class
    //no need to override
    default boolean isEmpty() {
        if(fillCount()== 0){
            return true;
        }
        return false;
    }

    default boolean isFull() {
        if(fillCount()==capacity()){
            return true;
        }
        return false;
    }
}