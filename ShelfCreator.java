public class ShelfCreator
{
    public float findVolumeOfShelf (float thickness,float heights[], float width,float depth){
        float outsideRectangleLength = width + 2 * thickness;//outer box width
        int numberOfShelves = heights.length;
        float totalHeight = 0.0f;
        float woodHeight = (numberOfShelves + 1) * thickness;//wood thickness for height
        float insideRectangleVolume = 0.0f;
        float outsideRectangleVolume = 0.0f;
            
        for (int i=0; i < numberOfShelves; i++){
            totalHeight+= heights[i]; //adding heights together
        }
        //System.out.println("total height 1 = " + totalHeight);
        //outsideRectangleVolume = outsideRectangleLength * totalHeight * depth;
       
        totalHeight+= woodHeight;
        System.out.println("total height 2 = " + totalHeight);
        System.out.println("depth = " + depth);
        System.out.println("outsideRectangleLength = " + outsideRectangleLength);
        outsideRectangleVolume = outsideRectangleLength * totalHeight * depth;
        for (int i=0; i < numberOfShelves; i++){
            insideRectangleVolume+= heights[i] * width * depth;
        }

        System.out.println("outside Rectangle Volume =" + outsideRectangleVolume);
        System.out.println("inside Rectangle Volume =" + insideRectangleVolume);
        return outsideRectangleVolume - insideRectangleVolume;   
    }
    public static void main(String[] args) {
		System.out.println("Hello");
        ShelfCreator shelf = new ShelfCreator();
        //test for 1 shelf
        float myThickness1 = 1.0f;
        float myDepth1 = 2.0f;
        float myHeights1[] = { 3.0f};
        float myWidth1 = 4.0f;
        float volume1 = shelf.findVolumeOfShelf(myThickness1, myHeights1, myWidth1, myDepth1);
        System.out.println("Volume of Shelf 1 = " + volume1);
        
        //test for 2 shelfs
        float myThickness2 = 1.0f;
        float myDepth2 = 2.0f;
        float myHeights2[] = { 3.0f, 4.0f};
        float myWidth2 = 5.0f;
        float volume2 = shelf.findVolumeOfShelf(myThickness2, myHeights2, myWidth2, myDepth2);
        System.out.println("Volume of Shelf 2 = " + volume2);
	}
}