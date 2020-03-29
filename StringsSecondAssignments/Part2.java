
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        // initial startIndex stringa start point, count = 0
        int startIndex = 0, count = 0;
        // while loop, until there is no stringb in stringa
        while (true) {
            int pos = stringb.indexOf(stringa, startIndex);
            if (pos == -1) {
                break;
            }
            // if stringa find stringb, then count ++ and startIndex = stringb's length 
            // and stringa.indexOf(stringb, startIndex)
            count++;
            startIndex = stringa.length() + pos;
        }

        System.out.println("count: " + count);
        // return count
        return count;
    }
    
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringa, stringb) == 3);
        
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println(howMany(stringa, stringb) == 2);
    }
}
