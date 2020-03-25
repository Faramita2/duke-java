
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringA, String stringB) {
        int startIndex = 0;
        int counter = 0;
        int index;
        
        while (true) {
            index = stringB.indexOf(stringA, startIndex);
            System.out.println("Index is " + index);
            if (index == -1) {
                break;
            }
            startIndex = index + stringA.length();
            if (++counter == 2) {
                return true;
            }
        }
        
        return false;
    }
    
    public String lastPart(String stringA, String stringB) {
        int index = stringB.indexOf(stringA);
        if (index != -1) {
            return stringB.substring(index + stringA.length(), stringB.length());
        }
        
        return stringB;
    }
    
    public void testTwoOccurrences() {
        String stringA = "by";
        String stringB = "A story by Abby Long";
        System.out.println(stringA + " has occured two times in " + stringB +
            " is " + twoOccurrences(stringA, stringB));
            
        stringA = "atg";
        stringB = "ctgtatgta";
        System.out.println(stringA + " has occured two times in " + stringB +
            " is " + twoOccurrences(stringA, stringB));
    }
    
    public void testLastPart() {
        String stringA = "an";
        String stringB = "banana";
        System.out.println(stringB + " remaining part is '" + this.lastPart(stringA, stringB) + "' after " + 
            stringA);
            
        stringA = "I";
        stringB = "I love you";
        System.out.println(stringB + " remaining part is '" + this.lastPart(stringA, stringB) + "' after " + 
            stringA);
            
        stringA = "zoo";
        stringB = "forest";
        System.out.println(stringB + " remaining part is '" + this.lastPart(stringA, stringB) + "' after " + 
            stringA);
    }
}
