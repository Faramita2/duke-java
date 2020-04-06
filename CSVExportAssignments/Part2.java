
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Part2 {
    CSVRecord coldestHourInFile(CSVParser parser) {
        // init coldest temperature record to null
        CSVRecord coldestRecord = null;
        // for each record from parser, if coldest record equals to null, assign it to current record
        for (CSVRecord currentRecord : parser) {
            if (coldestRecord == null) {
                coldestRecord = currentRecord;
            } else {
                // otherwise, compare current record's temperature to coldest record's temperature
                // assign the lower to coldest record
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                // ignore invalid temperature
                if (currentTemp != -9999 && currentTemp < coldestTemp) {
                    coldestRecord = currentRecord;
                }
            }
        }
        
        // coldest record is the answer
        return coldestRecord;
    }
    
    void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest hour is " 
            + record.get("TemperatureF") + " at " + record.get("DateUTC"));
    }
    
    String fileWithColdestTemperature() {
        // init coldestFile to null
        String coldestFilename = "";
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        // for each file from selected files, get CSVParser
        for (File currentFile : dr.selectedFiles()) {
            // if coldestFilename is empty, assign it to current file
            if (coldestFilename.isEmpty()) {
                coldestFilename = currentFile.getName();
                coldestFile = currentFile;
            } else {
                // otherwise, get coldest temperature from parser by calling coldestHourInFile
                FileResource fr = new FileResource(currentFile);
                CSVParser parser = fr.getCSVParser();
                CSVRecord currentColdestRecord = coldestHourInFile(parser);
                // compare current file coldest temperature to coldest file temperature
                FileResource coldestFr = new FileResource(coldestFile);
                CSVParser coldestParser = coldestFr.getCSVParser();
                CSVRecord coldestRecord = coldestHourInFile(coldestParser);
                // assign coldestFile to current file if current file temperature is colder
                Double currentColdest = Double.parseDouble(currentColdestRecord.get("TemperatureF"));
                Double coldest = Double.parseDouble(coldestRecord.get("TemperatureF"));
                if (currentColdest < coldest) {
                    coldestFilename = currentFile.getName();
                    coldestFile = currentFile;
                }
            }
        }
        
        System.out.println("Coldest day was in file " + coldestFilename);
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(parser).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
        // file's name is the answer
        return coldestFilename;
    }
    
    void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
    }
    
    CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestRecord = null;
        // for loop each record from parser
        for (CSVRecord record : parser) {
            // if lowestRecord is null, assign it to current record
            if (lowestRecord == null || lowestRecord.get("Humidity").equals("N/A")) {
                lowestRecord = record;
            } else {
                // otherwise, get record's Humidity column
                String humidityStr = record.get("Humidity");
                // check it's value it not "N/A", compare it to lowestRecord's Humidity value
                if (!humidityStr.equals("N/A")) {
                    Double humidityValue = Double.parseDouble(humidityStr);
                    Double lowestHumidity = Double.parseDouble(lowestRecord.get("Humidity"));
                    // reassign lowestRecord to current record if current record is lower one.
                    if (humidityValue < lowestHumidity) {
                        lowestRecord = record;
                    }
                }

            }
            
        }
        // the lowestRecord is the answer
        return lowestRecord;
    }
    
    void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        // for each file to find every file loweset humidity record
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
            // if lowest is null, assign it to current record
            if (lowest == null) {
                lowest = record;
            } else {
                // otherwise, compare current record to lowest humidity record
                Double currentHumidity = Double.parseDouble(record.get("Humidity"));
                Double lowestHumidity = Double.parseDouble(lowest.get("Humidity"));
                // reassign lowest to current record if current record humidity is lower than loweset
                if (currentHumidity < lowestHumidity) {
                    lowest = record;
                }
            }
        }
        // the lowest is the answer
        return lowest;
    }
    
    void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    double averageTemperatureInFile(CSVParser parser) {
        // init total temperature to 0.0, record's num to 0;
        double total = 0.0;
        int num = 0;
        
        // for each record from parser
        for (CSVRecord record : parser) {
            String currentTemp = record.get("TemperatureF");
            // if record's temperature is not "-9999", add humidity's value to total humidity
            num++;
            if (!currentTemp.equals("-9999")) {
                total += Double.parseDouble(currentTemp);
            }
        }
        
        // averageTemperature is total divide record's num
        return total / num;
    }
    
    void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0.0;
        int num = 0;
        // for each record from parser
        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            String tempStr = record.get("TemperatureF");
            // if record's humidity is not "N/A" and it is greater than value
            // and record's temperature is not "-9999" 
            if (!humidityStr.equals("N/A") &&
                    Double.parseDouble(humidityStr) > value &&
                        !tempStr.equals("-9999")){
                // total add record's temperature, num plue one
                total += Double.parseDouble(tempStr);
                num++;
            }
        }

        System.out.println("Total temperature is " + total);
        System.out.println("Total temperature number is " + num);
        // total divide num is the answer
        return total / num;
    }
    
    void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avg = averageTemperatureWithHighHumidityInFile(parser, value);
        if (avg > 0) {
            System.out.println("Average Temp when high Humidity is " + avg);
        } else {
            System.out.println("No temperatures with that humidity");  
        }
    }
}
