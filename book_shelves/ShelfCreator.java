import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShelfCreator {
    class Dimensions {
        public int N;// num of shelves
        public int B;// thickness
        public int W;// width
        public int D;// depth
        public int H[];// heights

        public Dimensions(int numberOfShelves,
                int thickness, int width, int depth) {
            N = numberOfShelves;
            B = thickness;
            W = width;
            D = depth;
            H = new int[N];
        }

        public String toString() {
            String str = "Dimensions:";
            str += " Num Shelves - " + N + "\n";
            str += " Thickness - " + B + "\n";
            str += " width - " + W + "\n";
            str += " depth - " + D + "\n";
            str += "heights - ";
            for (int h : H) {
                str += h + ", ";
            }
            return str;
        }

    }
    /*
     * 
     * --------
     * Dimensions ( ..... )
     * ---------
     * Dimensions ( ..... )
     * ---------
     */

    public Dimensions[] getTestCases(String inputFile) {
        Dimensions arr[] = new Dimensions[0];
        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFile));
            int numTestCases = Integer.parseInt(allLines.get(0));
            arr = new Dimensions[numTestCases];
            int j = 0;
            int k = 0;
            String firstLine, secondLine;
            String[] arrOfStr;
            for (int i = 1; i < allLines.size(); i += 2) {
                firstLine = allLines.get(i);
                secondLine = allLines.get(i + 1);
                arrOfStr = firstLine.split(" ");
                arr[j] = new Dimensions(Integer.parseInt(arrOfStr[0]),
                        Integer.parseInt(arrOfStr[1]),
                        Integer.parseInt(arrOfStr[2]),
                        Integer.parseInt(arrOfStr[3]));
                arrOfStr = secondLine.split(" ");
                k = 0;
                for (String str : arrOfStr) {
                    arr[j].H[k] = Integer.parseInt(str);
                    k++;
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println(inputFile + " not found");
            e.printStackTrace();
        }
        return arr;
    }

    public int findVolumeOfShelf(Dimensions dim) {
        // int thickness, int heights[], int width, int depth

        int outsideRectangleLength = dim.W + 2 * dim.B;// outer box width
        int numberOfShelves = dim.H.length;
        int totalHeight = 0;
        int woodHeight = (numberOfShelves + 1) * dim.B;// wood thickness for height
        int insideRectangleVolume = 0;
        int outsideRectangleVolume = 0;

        for (int i = 0; i < numberOfShelves; i++) {
            totalHeight += dim.H[i]; // adding heights together
        }
        // System.out.println("total height 1 = " + totalHeight);
        // outsideRectangleVolume = outsideRectangleLength * totalHeight * depth;

        totalHeight += woodHeight;
        System.out.println("total height 2 = " + totalHeight);
        System.out.println("depth = " + dim.D);
        System.out.println("outsideRectangleLength = " + outsideRectangleLength);
        outsideRectangleVolume = outsideRectangleLength * totalHeight * dim.D;
        for (int i = 0; i < numberOfShelves; i++) {
            insideRectangleVolume += dim.H[i] * dim.W * dim.D;
        }

        System.out.println("outside Rectangle Volume =" + outsideRectangleVolume);
        System.out.println("inside Rectangle Volume =" + insideRectangleVolume);
        return outsideRectangleVolume - insideRectangleVolume;
    }

    public static void main(String[] args) {
        ShelfCreator shelf = new ShelfCreator();
        /*
         * //test for 1 shelf
         * float myThickness1 = 1.0f;
         * float myDepth1 = 2.0f;
         * float myHeights1[] = { 3.0f};
         * 
         * 
         * 
         * 
         * //test for 2 shelfs
         * float myThickness2 = 1.0f;
         * float myDepth2 = 2.0f;
         * float myHeights2[] = { 3.0f, 4.0f};
         * at myWidth2 = 5.0f;
         * float volume2 = shelf.findVolumeOfShelf(myThickness2,
         * System.out.println("Volume of Shelf 2 = " + volume2);
         */
        Dimensions[] arrOfTestCases = shelf.getTestCases("/Users/aashu/work/calico2023/book_shelves/sample-1.1.in");
        // args[1]);
        int i = 0;
        System.out.println("*******************");
        for (Dimensions obj : arrOfTestCases) {
            System.out.println(" Test Case " + i);
            System.out.println(obj);
            int volume = shelf.findVolumeOfShelf(obj);
            System.out.println(" VOLUME OF SHELF = " + volume);
            i++;
        }
    }
}
