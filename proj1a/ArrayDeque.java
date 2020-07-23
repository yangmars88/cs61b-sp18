/**
 * Created by Yang on 2020/7/21.
 */
public class ArrayDeque<T> {
    //so array deque is to create an array, we want to use generic type, so can use this class to define any kinds of array deque.
    private static int initialCapacity=8;
    private static int eFactor = 2; // Expanding factor
    private static int mCapacity = 16; // The minimum capacity for contraction resizing
    private static double mRatio = 0.25; // The minimum usage ratio before contraction
    private static int cFactor = 2; // Contracting factor
    private int capacity; // The length of array
    private T[] items;  //generic type cannot create array directly, has to create Object array first, then convert to array.
    private int nextFirst;
    private int nextLast;
    private int size;

    //constructor
    public ArrayDeque(){
        capacity=initialCapacity;
        items = (T[]) new Object[initialCapacity];
        nextFirst = capacity - 1;// this array suppose the head is the last index: capacity-1,
        nextLast = 0; //this array suppose the tail is the first index 0
        size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){return size;}

    /** Prints the items in the deque from front to last, separated by a space */
    public void printDeque() {
        int currentIndex = plusOne(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = plusOne(currentIndex);
        }
        System.out.println();
    }

    public void addFirst(T item){
        items[nextFirst]=item;  //insert item from front end at available position.
        nextFirst=minusOne(nextFirst);  //when insert at current position, need to move the nextFirst one position left
        size+=1;
        expand();
    }


     private int minusOne(int index) {
            if (index == 0) {
                return capacity - 1;  //why set nextFirst to be head again if insert at rear end.
            } else {
                return index - 1;
            }
      }

    public void addLast(T item){
        items[nextLast]=item;
        nextLast=plusOne(nextLast);
        size+=1;
        expand();
    }

    private int plusOne(int index) {
        if (index == capacity - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int indexFromFront = nextFirst + 1 + index;
        if (indexFromFront >= capacity) {
            indexFromFront -= capacity;
        }
        return items[indexFromFront];
    }

    /** Resize the original array to a new array with given capacity. */
    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];

        int currentFirst = plusOne(nextFirst);
        int currentLast = minusOne(nextLast);

        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(items, currentFirst, newItems, 0, length);
            nextFirst = newCapacity - 1;
            nextLast = length;
        } else {
            int lengthFirsts = capacity - currentFirst;
            int newCurrentFirst = newCapacity - lengthFirsts;
            int lengthLasts = nextLast;
            System.arraycopy(items, currentFirst, newItems, newCurrentFirst, lengthFirsts);
            System.arraycopy(items, 0, newItems, 0, lengthLasts);
            nextFirst = newCapacity - lengthFirsts - 1;
        }

        capacity = newCapacity;
        items = newItems;
    }
    /** Checks whether the array needs expansion, and if so, executes it. */
    private void expand() {
        if (size == capacity) {
            int newCapacity = capacity * eFactor;
            resize(newCapacity);
        }
    }
    /** Checks whether the array needs contraction, and if so, executes it. */
    private void contract() {
        double ratio = (double) size / capacity;
        if (capacity >= mCapacity && ratio < mRatio) {
            int newCapacity = capacity / cFactor;
            resize(newCapacity);
        }
    }


    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int currentFirst = plusOne(nextFirst);
        T removed = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null
     * @Rule: A single operation should be executed in constant time,
     *  except during resizing operation.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int currentLast = minusOne(nextLast);
        T removed = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }


    //https://www.geeksforgeeks.org/implementation-deque-using-circular-array/

}
