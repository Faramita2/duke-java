
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf(stopCodon, startIndex+3);
        if (endIndex == -1) {
            return "";
        }
        
        if ((endIndex - startIndex) % 3 != 0) {
            return "";
        }
        
        return dna.substring(startIndex, endIndex+3);
    }
    
    public String findSimpleGene(String dna) {
        return this.findSimpleGene(dna, "ATG", "TAA");
    }
    
    public void printGene(String dna) {
        System.out.println("The dna fragment is: " + dna);
        System.out.println("First gene is " + this.findSimpleGene(dna));
    }
    
    public void testSimpleGene() {
        String dna = "ATTTTTTAA";
        this.printGene(dna);
        dna = "ATGGGGGGGGG";
        this.printGene(dna);
        dna = "ATGGGGGGGGG";
        this.printGene(dna);
        dna = "ATGGGGTTGAAATAGGGGTAA";
        this.printGene(dna);
        dna = "ATGGGGTTGAAATAGGGGTTAA";
        this.printGene(dna);
    }
}
