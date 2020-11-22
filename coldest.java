import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Décrivez votre classe coldest ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class coldest {
   public CSVRecord coldestHourInFile(CSVParser parser) {
       CSVRecord coldest = null;
       for (CSVRecord currentRow: parser) {
           if (coldest == null) {
              coldest= currentRow;
            }
           else {
            double currentTemp= Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp= Double.parseDouble(coldest.get("TemperatureF"));
            if (currentTemp < lowestTemp) {
               coldest=currentRow; 
            } }
      }
      return coldest; }
   
   public void testColdestHourInFile(){
    FileResource fr= new FileResource();
    CSVRecord coldest= coldestHourInFile( fr.getCSVParser());
    System.out.println("coldest temperature was " + Double.parseDouble(coldest.get("TemperatureF")) + " at" + coldest.get("DateUTC"));
   }
   
   public String fileWithColdestTemperature() {
       String name= "";
       CSVRecord lowest= null;
    DirectoryResource dr= new DirectoryResource();
    for (File f: dr.selectedFiles()) {
        FileResource fr= new FileResource(f);
        CSVRecord currentRow= coldestHourInFile(fr.getCSVParser());
        if ( lowest == null) {
          lowest= currentRow; 
          name= f.getName();
        }
        else {
        double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
        double lowestTemp=Double.parseDouble(lowest.get("TemperatureF"));
        if (currentTemp < lowestTemp) {
           lowest= currentRow;
           name = f.getName();
        }}
   }
    return name;}
    
   public void testFileWithColdestTemperature() {
       System.out.println("le file qui a le coldest temperature est " + fileWithColdestTemperature());
       
    }
   
   public CSVRecord lowestHumidityInFile(CSVParser parser) {
      CSVRecord low = null;
       for (CSVRecord currentRow: parser) {
           if (low == null) {
              low= currentRow;
            }
           if ((currentRow.get("Humidity")).equals("N/A")) {
            break;}
           else {
            double currentHum= Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum= Double.parseDouble(low.get("Humidity"));
            if (currentHum < lowestHum) {
               low=currentRow; 
            } }
      }
      return low; }
      
   public void testLowestHumidityInFile() {
      FileResource fr = new FileResource();
      CSVParser parser = fr.getCSVParser();
      CSVRecord csv = lowestHumidityInFile(parser);
      System.out.println("the lowest humidity est " + Double.parseDouble(csv.get("Humidity")) + "at " + csv.get("DateUTC")); }
    
   public CSVRecord lowestHumidityInManyFiles() {
       CSVRecord lowest= null;
       DirectoryResource dr= new DirectoryResource();
       for (File f: dr.selectedFiles()) {
        FileResource fr= new FileResource(f);
        CSVRecord currentRow= lowestHumidityInFile(fr.getCSVParser());
        if ( lowest == null) {
          lowest= currentRow; 
        }
        else {
        double currentTemp=Double.parseDouble(currentRow.get("Humidity"));
        double lowestTemp=Double.parseDouble(lowest.get("Humidity"));
        if (currentTemp < lowestTemp) {
           lowest= currentRow;
           
        }}
   }
    return lowest;}
    
   public void testLowestHumidityInManyFiles() {
         System.out.println("le file qui a le lowest humidity est " + (lowestHumidityInManyFiles().get("Humidity")) + " and was in " + (lowestHumidityInManyFiles().get("DateUTC")));
        }
    
   public double averageTemperatureInFile(CSVParser parser) {
       double s=0;
       double n=0;
       for (CSVRecord record: parser) {
           n=n+1;
           s=s + Double.parseDouble(record.get("TemperatureF"));
       }
       return (s/n); 
     }
   
   public void testAverageTemperatureInFile() {
         FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
         System.out.println("l'average des temperatures est " + averageTemperatureInFile(parser));}
   
   
   public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
       double s=0;
       double n=0;
         for (CSVRecord record : parser) {
             if (Integer.parseInt(record.get("Humidity")) >= value ) {
                 n=n+1;
                 s= s+ Double.parseDouble(record.get("TemperatureF"));
              }
         }
          if (s==0) { n=1;}
          return (s/n);
   }
   
   
   public void testAverageTemperatureWithHighHumidityInFile(){
          FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
         double K= averageTemperatureWithHighHumidityInFile(parser, 80);
         if (K==0) { System.out.println("No temperatures with that humidity");}
         System.out.println("l'average de ces temperetures avec la condition sur l'humidity est : " + K);
         
         
    
    
    
    
    }
    
    }
     
    
    
    
    

    
    
    
    

