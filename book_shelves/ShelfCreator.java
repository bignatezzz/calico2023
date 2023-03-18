import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShelfCreator {
    class InputFormat {
        public int N;// num of shelves
        public int B;// thickness
        public int W;// width
        public int D;// depth
        public int H[];// heights

        public InputFormat(int numberOfShelves,
                int thickness, int width, int depth) {
            N = numberOfShelves;
            B = thickness;
            W = width;
            D = depth;
            H = new int[N];
        }

        public String toString() {
            String str = "InputFormat:";
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
     * InputFormat ( ..... )
     * ---------
     * InputFormat ( ..... )
     * ---------
     */

    public InputFormat[] getTestCases(String inputFile) {
        InputFormat arr[] = new InputFormat[0];
        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFile));
            int numTestCases = Integer.parseInt(allLines.get(0));
            arr = new InputFormat[numTestCases];
            int j = 0;
            int k = 0;
            String firstLine, secondLine;
            String[] arrOfStr;
            for (int i = 1; i < allLines.size(); i += 2) {
                firstLine = allLines.get(i);
                secondLine = allLines.get(i + 1);
                arrOfStr = firstLine.split(" ");
                arr[j] = new InputFormat(Integer.parseInt(arrOfStr[0]),
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

    public float findVolumeOfShelf(float thickness, float heights[], float width, float depth) {
        float outsideRectangleLength = width + 2 * thickness;// outer box width
        int numberOfShelves = heights.length;
        float totalHeight = 0.0f;
        float woodHeight = (numberOfShelves + 1) * thickness;// wood thickness for height
        float insideRectangleVolume = 0.0f;
        float outsideRectangleVolume = 0.0f;

        for (int i = 0; i < numberOfShelves; i++) {
            totalHeight += heights[i]; // adding heights together
        }
        // System.out.println("total height 1 = " + totalHeight);
        // outsideRectangleVolume = outsideRectangleLength * totalHeight * depth;

        totalHeight += woodHeight;
        System.out.println("total height 2 = " + totalHeight);
        System.out.println("depth = " + depth);
        System.out.println("outsideRectangleLength = " + outsideRectangleLength);
        outsideRectangleVolume = outsideRectangleLength * totalHeight * depth;
        for (int i = 0; i < numberOfShelves; i++) {
            insideRectangleVolume += heights[i] * width * depth;
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
        InputFormat[] arrOfTestCases = shelf.getTestCases("/Users/aashu/work/calico2023/book_shelves/sample-1.1.in");
        //args[1]);
        int i = 0;
        for (InputFormat obj : arrOfTestCases) {
            System.out.println(" Test Case " + i);
            System.out.println(obj);
            i++;
        }
    }
}
