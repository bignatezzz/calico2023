package brand_problems;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BrandCheck{

    Rules r;

    class BrandDetails{
        public int year;
        public String name;

        //constructor
        public BrandDetails(int Y, String N) {
            year = Y;
            name = N;
        }
        public String toString() {
            String str = "Brand details \n";
            str += "year - " + year + "\n";
            str += "name - " + name + "\n";
         
            return str;
        }
    }

    public class Rules {
        public List<String> trademarks;
        public int validYear;
        public int maxCharacters;
        public String toString() {
            String str = "Rules \n";
            str += "Valid Year - " + validYear + "\n";
            str += "Max characters - " + maxCharacters + "\n";
            for (String trademark: trademarks) {
                str += "Trademark - " + trademark + "\n";
            }
            return str; 
        }
        //constructor
        public Rules(int V, int M, String[] array) {
            validYear = V;
            maxCharacters = M;
            //trademarks = new ArrayList<>(Arrays.asList(array));
            trademarks = new ArrayList<>();
            for (String str: array) {
                trademarks.add(str);
            }
        }
    }

    public BrandDetails[] getTestCases(String inputFile) {
        BrandDetails arr[] = new BrandDetails[0];
        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFile));
            int numTestCases = Integer.parseInt(allLines.get(0));
            arr = new BrandDetails[numTestCases];
            int j = 0;
            String firstLine, secondLine;
            for (int i = 1; i < allLines.size(); i += 2) {
                firstLine = allLines.get(i);
                secondLine = allLines.get(i + 1);
                arr[j] = new BrandDetails(Integer.parseInt(firstLine),secondLine);     
                j++;
            }
        } catch (Exception e) {
            //System.out.println(inputFile + " not found");
            e.printStackTrace();
            System.exit(9);
        }
        return arr;
    }
    public boolean isValidTrademark(String name, int year) {
        if (name.length() > r.maxCharacters) {
            return false;
        } 
        if (year < r.validYear) {
            //<2010
            return true;
        }
        String[] array = name.split(" ");
        for (String trademark: r.trademarks) {
            //System.out.println("\""+trademark.toUpperCase()+"\""+" "+array[0].toUpperCase());
            if (array[0].equalsIgnoreCase(trademark)) {
                return false;
            }
        }
        return true;
    }

    //constructor
    BrandCheck(int v ,int len, String[] array){
        r = new Rules(v, len, array);
    }

    public static void main(String[] args) {
        String[] array = {"California", "Cal", "Berkeley"};
        int v =2010;
        int len = 50;

        BrandCheck rso = new BrandCheck(v, len, array);
        BrandDetails[] arrOfTestCases = rso.getTestCases(args[0]);
        //System.out.println(rso.r);
        //int i = 0;
        // System.out.println("*******************");
        for (BrandDetails obj : arrOfTestCases) {
            /*
            System.out.println(" Test Case " + i);
            System.out.println(obj);
            i++;
            */
            if (rso.isValidTrademark(obj.name, obj.year)) {
                System.out.println("VALID");
            } else {
                System.out.println("INVALID");
            }

        }
    }
}
