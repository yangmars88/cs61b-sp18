/**
 * Created by Yang on 2020/7/19.
 */
public class LinkedListDeque<T> implements Deque<T> {
    //class Node defines the structure
    private class Node {
      T item;
      Node prev, next;
      public Node(T i, Node p, Node n) {
          item=i;
          prev=p;
          next=n;
      }
    }
    //class head and tail are an variable reference of node structure
    private Node head, tail;
    private int size;

    //instantiation of node structure: create an empty node.
    public LinkedListDeque(){
        this.head=null;
        this.tail=null;
        size=0;
    }
    @Override
    public boolean isEmpty(){
        if(size==0){return true;}
        else {return false;}
    }
    @Override
    public int size(){return size;}
    @Override
    public void printDeque() {
        //have to define a reference to points to head first, so head doesn't have to change
        Node currentNode = head;
        while(currentNode!=null){
            System.out.print(currentNode.item+ " ");
            currentNode=currentNode.next;
        }
        System.out.println();
    }
    @Override
    public void addFirst(T item) {
        Node newNode=new Node(item, null, null);

        //also if current linked list is empty, tail need to point to this node too.
        if(isEmpty()){
            tail=newNode;
        }else{
            //if current linked list is empty, then head has no prev and next, so no need to change head.prev to this newNode.
            head.prev=newNode;
        }
        newNode.next=head;
        head=newNode;
        size+=1;
    }
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, null, null);
        if(isEmpty()){
            head=newNode;
        }else {
            tail.next=newNode;
            newNode.prev=tail;
        }
        tail=newNode;
        size+=1;
    }
    @Override
    public T removeFirst() {
        //if head doesn't point to first valid item at all, means it's empty
        if(head==null){return null;}
        Node first=head;
        //if there is only one item, need to update tail to null after remove the item
        if(head.next==null){tail=null;}
        else {head.next.prev=null;}
        head=head.next;
        size-=1;
        return first.item; //return the removed node.item.
    }
    @Override
    public T removeLast() {
        //if list is null, then tail is null
        if(tail==null){return null;}
        //if there is only one item, need to update head to null after remove last item.
        Node last = tail;
        if(head.next==null){head=null;}
        else {tail.prev.next=null;}
        tail=tail.prev;
        size-=1;
        return last.item;
    }
    @Override
    public T get(int index){
        //have to go through whole list to find specific node
        if(index>=size){return null;}  //in here, index starting from 0, so the max index should be 1 less than size
        Node currentNode = head;
        while(index!=0){
             currentNode=currentNode.next;
             index-=1;
        }
        return currentNode.item;
    }

    public T getRecursive(int index){
        if(index>=size){return null;}
        return getRecursiveHelper(head, index);
    }

    private T getRecursiveHelper(Node node, int index)
    {
        if(index==0){return node.item;}
        return getRecursiveHelper(node.next,index-1);
    }

//https://www.netjstech.com/2019/03/deque-implementation-in-java-using-doubly-linked-list.html



}
