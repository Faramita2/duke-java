//video Coding StorageResource class in lesson Using the StorageResource Class

/**
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class AllGenesStored {
    public int findStopCodon(String dnaStr,
                             int startIndex, 
                             String stopCodon){                                 
            int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
            while (currIndex != -1 ) {
               int diff = currIndex - startIndex;
               if (diff % 3  == 0) {
                   return currIndex;
               }
               else {
                   currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
               }
            }
            return -1;
        
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    public StorageResource getAllGenes(String dna) {
      //create an empty StorageResource, call it geneList
      StorageResource geneList = new StorageResource();
      //Set startIndex to 0
      int startIndex = 0;
      //Repeat the following steps
      while ( true ) {
          //Find the next gene after startIndex
          String currentGene = findGene(dna, startIndex);
          //If no gene was found, leave this loop 
          if (currentGene.isEmpty()) {
              break;
          }
          //Add that gene to geneList
          geneList.add(currentGene);
          //Set startIndex to just past the end of the gene
          startIndex = dna.indexOf(currentGene, startIndex) +
                       currentGene.length();
        }
      //Your answer is geneList
      return geneList;
    }
    public void testOn(String dna) {
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
        public void printGenes( StorageResource sr ) {
        
        int sixtyCharQty = 0;
        int highCgRatioQty = 0;
        float cgRatioConst = (float) 0.35;
        int maxLen = 0;
        
        for ( String s : sr.data() ) {
            
             if ( s.length() > 60 ) {
                 System.out.println( "String longer than 60 characters: "+s );
                 sixtyCharQty++;
             }
             
             if ( maxLen < s.length() ) {
                 maxLen = s.length();
             }
             
             
             if ( cgRatio(s) > cgRatioConst ) {
                System.out.println( "String with C-G-ratio higher than 0.35: "+s );
                highCgRatioQty++;
             }
             
        }
        
        System.out.println( "60 characters qty: "+sixtyCharQty );
        System.out.println( "Max length: "+maxLen );
        System.out.println( "Strings with C-G-ratio higher than 0.35: "+highCgRatioQty );
        
    }
    
        public float cgRatio( String dna ) {
        String dnaLow = dna.toLowerCase();
        int cgCount = 0;
        int start = 0;
        
        while (true) {
            int pos = dnaLow.indexOf("c", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        
        while (true) {
            int pos = dnaLow.indexOf("g", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        
        return ( (float) cgCount ) / dna.length();
        
    }
    
    void printCTGNum(String dna) {
        int start = 0;
        int count = 0;
        while (true) {
            int startIndex = dna.indexOf("CTG", start);
            if (startIndex == -1) {
                break;
            }
            count++;
            start = startIndex+3;
        }

        System.out.println("CTG count: " + count);
    }
    
    public void test() {
        //      ATGv  TAAv  ATG   v  v  TGA   
        FileResource dnaFile = new FileResource();
        StorageResource genesFound = getAllGenes( dnaFile.asString().toUpperCase() );
        System.out.println( "Number of genes found: "+genesFound.size() );
        printGenes( genesFound );
        printCTGNum(dnaFile.asString().toUpperCase());
    }
}
