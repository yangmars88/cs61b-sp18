import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOneLowerChar(){
        assertTrue(offByOne.equalChars('a','b'));
        assertFalse(offByOne.equalChars('a','c'));
    }

    @Test
    public void testOffByOneUpperChar(){
        assertTrue(offByOne.equalChars('A','B'));
        assertFalse(offByOne.equalChars('A','C'));
    }

    @Test
    public void testOffByOneMix(){
        assertTrue(offByOne.equalChars('A','%'));
        assertFalse(offByOne.equalChars('%','#'));
    }
}
