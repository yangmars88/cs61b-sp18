public class OffByOne implements CharacterComparator {

    public boolean equalChars(Character a, Character b) {
        int diff = a-b;
        if(Math.abs(diff)==1){
            return true;
        }
        return false;
    }
}