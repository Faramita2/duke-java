
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int start, String stopCodeon) {
        int stop = dna.indexOf(stopCodeon, start);
        // else look for first type stopCodeon, check if it equals to -1
        if (stop != -1 && 
            (stop - start) % 3 == 0 ) {
            return stop;
        }
        // if it equals to -1, return dna.length() else return it
        return dna.length();
    }
    
    public String findGene(String dna, int start) {
        int startIndex = dna.indexOf("ATG", start);
        // System.out.println("startIndex: " + startIndex);
        if (startIndex == -1) {
            return "";
        }
        int minStop = dna.length();
        int taaStop = findStopCodon(dna, startIndex, "TAA");
        if (taaStop != -1 && minStop > taaStop) {
            minStop = taaStop;
        }
        int tagStop = findStopCodon(dna, startIndex, "TAG");
        if (tagStop != -1 && minStop > tagStop) {
            minStop = tagStop;
        }
        int tgaStop = findStopCodon(dna, startIndex, "TGA");
        if (tgaStop != -1 && minStop > tgaStop) {
            minStop = tgaStop;
        }
        // System.out.println("minStop " + minStop);
        // save min stop codon index in minStop to renew min index
        // find the first stop codon "TAA" after start codon, and it's index must multiple of
        // three away from the start codon's index.
        // do these steps for "TAG" and "TGA" the same.
        // find the closest stop codon and print the gene, if there is no valid stop codon
        // and therefor no gene, return the empty string.
        if (minStop != dna.length()) {
            return dna.substring(startIndex, minStop+3);
        }
        
        return "";
    }
    
     public int findAllGenes(String dna) {
        int startIndex = dna.indexOf("ATG");
        int count = 0;
        if (startIndex == -1) {
            return 0;
        }
        
        while (true) {
            String gene = findGene(dna, startIndex);
            // System.out.println("current gene: " + gene);
            if (gene.isEmpty()) {
                break;
            }
            count++;
            System.out.println("curGene " + gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            // System.out.println("startIndex: " + startIndex);
        }
        
        return count;
    }
    
    public int countGenes(String dna) {
        return findAllGenes(dna);
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        int ans = 2;
        System.out.println(dna + " has " + ans + " genes: " + (ans == countGenes(dna)));
        
        dna = "AAAAAAAAAAAA";
        ans = 0;
        System.out.println(dna + " has " + ans + " genes: " + (ans == countGenes(dna)));
        
        dna = "ATGTAAGATGCCCTAGTATGTGAATGTAGATGATT";
        ans = 4;
        System.out.println(dna + " has " + ans + " genes: " + (ans == countGenes(dna)));
    }
}
