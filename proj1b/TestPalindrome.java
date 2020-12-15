import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparartor offBy1 = new OffByN(1);
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class.
    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void testIsPalindrome0Or1() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0));
        assertTrue(palindrome.isPalindrome(s1));
    }

    @Test
    public void testIsPalindromeOdd() {
        String sTrue = "racecar";
        String sFalse = "horse";
        assertTrue(palindrome.isPalindrome(sTrue));
        assertFalse(palindrome.isPalindrome(sFalse));
    }

    @Test
    public void testIsPalindromeEven() {
        String sTrue = "noon";
        String sFalse = "rancor";
        assertTrue(palindrome.isPalindrome(sTrue));
        assertFalse(palindrome.isPalindrome(sFalse));
    }

    @Test
    public void testIsPalidromeOffByOne() {
        String  sTrue = "flake";
        String sFalse = 'frake';
        assertTrue(palindrome.isPalindrome(sTrue, offByOne));
        assertFalse(palindrome.isPalindrome(sFalse, offByOne));
    }
}
