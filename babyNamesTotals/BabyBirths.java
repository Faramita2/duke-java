/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalNames(FileResource fr) {
        int totalFemale = 0;
        int totalMale = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(1).equals("M")) {
                totalMale++;
            }
            else {
                totalFemale++;
            }
        }

        System.out.println("female girls = " + totalFemale);
        System.out.println("male boys = " + totalMale);
    }
    
    public void testTotalNames () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalNames(fr);
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        // according given year, open specific file
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int rank = 0;
        // for each record to look up the record match name and gender
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank++;
                // if find it, return the rank
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        // else return -1
        return -1;
    }
    
    public int getRank(String name, String gender) {
        // according given year, open specific file
        FileResource fr = new FileResource();
        int rank = 0;
        // for each record to look up the record match name and gender
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank++;
                // if find it, return the rank
                if (rec.get(0).equals(name)) {

                    break;
                }
            }
        }
        // else return -1
        System.out.println("name: " + name + ", gender: " + gender + " rank: " + rank);
        return rank;
    }
    
    public void testGetRank() {
        // System.out.println(getRank(2012, "Mason", "M"));
        // System.out.println(getRank(2012, "Mason", "F"));
        // getRank("Emily", "F");
        getRank("Frank", "M");
    }
    
    public String getName(int year, int rank, String gender) {
        // init currentRank to zero
        int currentRank = 0;
        // open specific year file
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {
            // search the record that equals gender
            if (record.get(1).equals(gender)) {
                // if record exists, rank plus one
                currentRank++;
                if (currentRank == rank) {
                // if currentRank equals rank, return current record's name
                    return record.get(0);
                }
            }
            // otherwise, return "NO NAME"
        }
        return "NO NAME";
    }
    
    public String getName(int rank, String gender) {
        // init currentRank to zero
        int currentRank = 0;
        // open specific year file
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        String name = "NO NAME";

        for (CSVRecord record : parser) {
            // search the record that equals gender
            if (record.get(1).equals(gender)) {
                // if record exists, rank plus one
                currentRank++;
                if (currentRank == rank) {
                // if currentRank equals rank, return current record's name
                    name = record.get(0);
                    break;
                }
            }
            // otherwise, return "NO NAME"
        }
        
        System.out.println("rank: " + rank + ", gender: " + gender + ", name: " + name);
        return name;
    }
    
    public void testGetName() {
        // System.out.println(getName(2012, 1, "M"));
        // System.out.println(getName(2013, 4, "F"));
        // System.out.println(getName(2014, 7, "F"));
        getName(350, "F");
        getName(450, "M");
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        // based on the same popularity

        String genderStr = gender.equals("M") ? "he": "she";
        // get name's rank in year
        int rank = getRank(year, name, gender);
        // then get newYear same rank's name assign to newName
        String newName = getName(newYear, rank, gender);
        if (newName.equals("NO NAME")) {
            System.out.println("No name matches.");
        } else {
            System.out.println(name + " born in " + year + " would be " + newName + " if " + genderStr + " was born in " + newYear + ".");
        }
    }
    
    public void whatIsNameInYear(String name, String gender) {
        // based on the same popularity
        // get name's rank in year
        int rank = getRank(name, gender);
        // then get newYear same rank's name assign to newName
        String newName = getName(rank, gender);
        if (newName.equals("NO NAME")) {
            System.out.println("No name matches.");
        } else {
            System.out.println(name + " born in new year would be " + newName);
        }
    }
    
    public void testWhatIsNameInYear() {
        // whatIsNameInYear("Isabella", 2012, 2014, "F");
        // whatIsNameInYear("Susan", "F");
        whatIsNameInYear("Owen", "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        // new an directory resource, init rank = 0, year = -1
        DirectoryResource dr = new DirectoryResource();
        int rank = Integer.MAX_VALUE;
        int year = -1;
        
        // for each file get rank for specific name and gender
        // if current rank not equals to -1 and larger than rank
        // set year to current file year
        for (File file : dr.selectedFiles()) {
            String fileName = file.getName();
            int currentYear = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);

            if (currentRank != -1 && currentRank < rank) {
                year = currentYear;
                rank = currentRank;
            }
        }
        
        // year is the answer
        return year;
    }
    
    public void testYearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        
        int year = yearOfHighestRank(name, gender);
        if (year != -1) {
            System.out.println("name: " + name + ", gender: " + gender + " 's highest rank in " + year + ".");
        } else {
            System.out.println("Not found");
        }
        
        name = "Mich";
        gender = "M";
        year = yearOfHighestRank(name, gender);
        if (year != -1) {
            System.out.println("name: " + name + ", gender: " + gender + " 's highest rank in " + year + ".");
        } else {
            System.out.println("Not found");
        }
    }
    
    public double getAverageRank(String name, String gender) {
        // new an directory resource, init rank = 0, year = -1
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int yearCount = 0;
        
        // for each file get rank for specific name and gender
        // if current rank not equals to -1 and larger than rank
        // set year to current file year
        for (File file : dr.selectedFiles()) {
            String fileName = file.getName();
            int currentYear = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            
            if (currentRank != -1) {
                yearCount++;
                totalRank += currentRank;
            }
        }
        
        // the answer
        
        return (double)totalRank / yearCount;
    }
    
    public void testGetAverageRank() {
        String name = "Mason";
        String gender = "M";
        name = "Susan";
        gender = "F";
        double avgRank = getAverageRank(name, gender);
        if (avgRank != 0) {
            System.out.println("name: " + name + ", gender: " + gender + " 's average rank is " + avgRank + ".");
        } else {
            System.out.println("Not found");
        }
        
        name = "Robert";
        gender = "M";
        avgRank = getAverageRank(name, gender);
        if (avgRank != 0) {
            System.out.println("name: " + name + ", gender: " + gender + " 's average rank is " + avgRank + ".");
        } else {
            System.out.println("Not found");
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        // for each row in year file 
        // open specific year file
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {
            // search the record that equals gender
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    // if current name equals name, return total
                    return total;
                }
                // if current gender equals gender, then add to total
                int currentBirths = Integer.parseInt(record.get(2));
                total += currentBirths;
            }
        }

        return total;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        String name = "Emily";
        String gender = "F";
        gender = "M";
        name = "Drew";
        int year = 1990;
        int total = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("name: " + name + ", gender: " + gender + " 's total births ranked higher is " + total + ".");
    }
}
