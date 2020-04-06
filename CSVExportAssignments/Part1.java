
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.apache.commons.csv.*;
import edu.duke.*;
public class Part1 {
    
    void tester() {
        FileResource fr = new FileResource();
        
        // test countryInfo
        CSVParser parser = fr.getCSVParser();
        // System.out.println(countryInfo(parser, "Germany"));
        // parser = fr.getCSVParser();
        // System.out.println(countryInfo(parser, "Nauru"));
        
        // test listExportersTwoProducts
        // parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        // test numberOfExporters
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        
        // test bigExporters
        // parser = fr.getCSVParser();
        // bigExporters(parser, "$999,999,999");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    String countryInfo(CSVParser parser, String country) {
        // for loop to lookup "Country" column whether equals to country
        for (CSVRecord record : parser) {
            // if find it, return it with it's info
            if (record.get("Country").equals(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }

        // return "NOT FOUND"
        return "NOT FOUND";
    }
    
    void listExportersTwoProducts(
                    CSVParser parser,
                    String exportItem1,
                    String exportItem2) {
        // for loop all records, find if record "Exports" contains exportItem1 and exportItem2
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            // if true, print it
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }

    }
    
    int numberOfExporters(CSVParser parser, String exportItem) {
        // init num = 0
        int num = 0;
        // for loop look up every record column "Exports" if contains exportItem
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            // if true, num plus 1
            if (exports.contains(exportItem)) {
                num++;
            }
        }

        // return num
        return num;
    }
    
    void bigExporters(CSVParser parser, String amount) {
        // for loop every record check record's column "Value (dollars)" if longer than amount
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            // if it's true, print record's country with column "Value (dollars)"
            if (value.length() > amount.length()) {
                System.out.println(country + " " + value);
            }
        }

    }
}
