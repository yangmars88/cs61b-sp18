//https://sp18.datastructur.es/materials/proj/proj1b/proj1b
public class Palindrome {
    //so wordToDeque take a string and return an arrayDeque or linkedListDeque
    //and when remove from deque head, the order is same as insert, so call addLast when inserting.
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i=0; i<word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word) {
        // request to use Deque to implement this method, so need to use removeFirst & removeLast to
        // compare first and last char of word, if it's odd length, one char left, if even length, no char left
        Deque<Character> deque = wordToDeque(word);
        while(deque.size()>1){
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if(first!=last){
                return false;
            }
        }
        return true;
    }
     // cc will just be superType variable of subType, because interface has to be implemented to use.
    // in TestPalindrome.java, we passed in OffByOne.java as it implemented cc, so actually with cc type variable,
    // we can use different type of comparators like offByOne or offByN..
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = new wordToDeque(word);
        while(deque.size()>1){
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if(!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

}