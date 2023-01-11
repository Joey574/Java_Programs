import javax.swing.plaf.ColorChooserUI;
import java.awt.Color;

public class GS13_01
{

    /** Method to test chromakey */
    public static void testChromakey()
    {
        Picture mark = new Picture("blue-mark.jpg");
        Picture moon = new Picture("moon-surface.jpg");
        mark.chromakey(moon);
        mark.explore();
    }

    public static void main(String[] args)
    {

    }
    
    
    
}
