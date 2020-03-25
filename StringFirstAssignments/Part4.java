import edu.duke.URLResource;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    /*
     * s = youtube.com
        start;
        urlStart;
        urlEnd;

        for loop:
    	start = url.indexOf(s)
    	if  s != -1
	urlStart = url.lastIndexOf(“\””, start);
	urlEnd = url.indexOf(“\”’, start);
	System.out.println(url.substring(urlStart, urlEnd);
     */
    public void findUrls() {
        String address = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        String target = "youtube.com";
        char quote = '"';
        int index, start, end;
        
        URLResource ur = new URLResource(address);
        
        for (String word : ur.words()) {
            index = word.toLowerCase().indexOf(target.toLowerCase());
            if (index != -1) {
                start = word.lastIndexOf(quote, index);
                end = word.indexOf(quote, index);
                
                String url = word.substring(start+1, end);
                
                System.out.println(url);
            }
        }
    }
    
    public void testFindUrls() {
        this.findUrls();
    }
}
