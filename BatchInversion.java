import edu.duke.*;
import java.io.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class BatchInversion 
{

    public ImageResource makeInversion(ImageResource inImage) 
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel: outImage.pixels()) 
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            int   average = ((255 - inPixel.getRed()) + (255 - inPixel.getBlue()) + (255 - inPixel.getGreen()))/3;

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }

        return outImage;
    }

    public void selectAndConvert() 
    {
        DirectoryResource dr = new DirectoryResource();

        for(File f: dr.selectedFiles()) 
        { 
            ImageResource originalImage = new ImageResource(f);
            String        fileName      = originalImage.getFileName();
            String        newFileName   = "inverted-" + fileName;
            ImageResource invertedImage = makeInversion(originalImage);
            
            invertedImage.setFileName(newFileName);
            invertedImage.draw();
            invertedImage.save();
        }
    }
}
