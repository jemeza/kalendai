package app.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * RecoloredImage.java - a kalendai class to recolor images to suit app schemes
 */
public class RecoloredImage {
    // Resultant image
    private Image image;

    /**
     * Creates a new image that is recolored based on parameters
     * @param inputImage - an Image that is the base image
     * @param oldColor - a Color that will be replaced on the base image
     * @param newColor - a Color that will replace the old color on the base image
     */
    public RecoloredImage(Image inputImage, Color oldColor, Color newColor) {
        // Store base image dimensions
        int width = (int)inputImage.getWidth();
        int height = (int)inputImage.getHeight();

        // Create objects used to read and write pixels of images
        WritableImage outputImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        // Gets components of old and new color RGB values
        int ob = (int)(oldColor.getBlue() * 255.0D);
        int or = (int)(oldColor.getRed() * 255.0D);
        int og = (int)(oldColor.getGreen() * 255.0D);
        int nb = (int)(newColor.getBlue() * 255.0D);
        int nr = (int)(newColor.getRed() * 255.0D);
        int ng = (int)(newColor.getGreen() * 255.0D);

        // Iterate through image
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                // Read image pixel
                int argb = reader.getArgb(x, y);
                int a = argb >> 24 & 255;
                int r = argb >> 16 & 255;
                int g = argb >> 8 & 255;
                int b = argb & 255;

                // Replace image pixel
                if (g == og && r == or && b == ob) {
                    r = nr;
                    g = ng;
                    b = nb;
                }
                argb = a << 24 | r << 16 | g << 8 | b;
                writer.setArgb(x, y, argb);
            }
        }

        // Stores resultant image
        this.image = outputImage;
    }

    /**
     * Gets the resultant recolored image
     * @return Image - the resultant image
     */
    public Image getImage() {
        return this.image;
    }
}
