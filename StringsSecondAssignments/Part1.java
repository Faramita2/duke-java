
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
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
    
    public void testFindStopCodon() {
        String dna = "NNNNATGGGGUUUIESGNTATTTAAAAAATTTT";
        String stopCodeon = "TAA";
        System.out.println("dna: " + dna + "dna's length is " + dna.length());
        System.out.println("stopCodeon: " + stopCodeon);
        System.out.println("stopCodeon in " + findStopCodon(dna, 0, stopCodeon));
        
        stopCodeon = "TAG";
        dna = "AAAAAAAAAAAAAAAAAAAAAAAAAAA";
        System.out.println("dna: " + dna + "dna's length is " + dna.length());
        System.out.println("stopCodeon: " + stopCodeon);
        System.out.println("stopCodeon in " + findStopCodon(dna, 0, stopCodeon));
        
        dna = "AAAAAAAAAAATGGGGGGGGGGGGGATAG";
        System.out.println("dna: " + dna + "dna's length is " + dna.length());
        System.out.println("stopCodeon: " + stopCodeon);
        System.out.println("stopCodeon in " + findStopCodon(dna, 0, stopCodeon));
        
        stopCodeon = "TGA";
        dna = "TTTTTTTTTGGGGGGAGATTGATGAAAAAAAAAAAAATGA";
        System.out.println("dna: " + dna + "dna's length is " + dna.length());
        System.out.println("stopCodeon: " + stopCodeon);
        System.out.println("stopCodeon in " + findStopCodon(dna, 0, stopCodeon));
    }
    
    public String findGene(String dna) {
        return findGene(dna, 0);
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
    
    public void testFindGene() {
        // DNA with no “ATG”
        String dna = "AAAAAAAAAAAAAAAAAAAAAAAA";
        System.out.println("dna: " + dna);
        System.out.println("closest gene in dna: " + findGene(dna));
        
        // DNA with “ATG” and one valid stop codon
        dna = "AAAAAAAAAAATTTTTTTATGGGGTGA";
        System.out.println("dna: " + dna);
        System.out.println("closest gene in dna: " + findGene(dna));
        
        // DNA with “ATG” and multiple valid stop codons
        dna = "AAAAAAAAAATGTAAAAAGGGGGTAGGGGGGGGGTGANNNNSSSS";
        System.out.println("dna: " + dna);
        System.out.println("closest gene in dna: " + findGene(dna));
        
        dna = "AAAAAAATGTAATATAGTGAGGGTAGGGGGGGGGTGANNNNSSSS";
        System.out.println("dna: " + dna);
        System.out.println("closest gene in dna: " + findGene(dna));
        
        // DNA with “ATG” and no valid stop codons
        dna = "ATGGDFSDFSAGAGABAGADFAJFIAFJAI";     
        System.out.println("dna: " + dna);
        System.out.println("closest gene in dna: " + findGene(dna));
    }
    
    public void findAllGenes(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return;
        }
        
        while (true) {
            String gene = findGene(dna, startIndex);
            // System.out.println("current gene: " + gene);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            // System.out.println("startIndex: " + startIndex);
        }
    }
    
    public void testFindAllGenes() {
        String dna = "ATGTAAATGTAGATGTGAATGGGGGGGGGG";
        System.out.println("dna: " + dna);
        System.out.println("all genes in dna: ");
        findAllGenes(dna);
        
        
    }
}
