package pl.marchuck.android.material.design.icon.generator;

import com.sun.istack.internal.Nullable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by lukasz on 09.01.17.
 */
public class AppPresenter {

    final AppCallbacks appCallbacks;

    File currentImage;
    String currentFileName;

    @Nullable
    IconModel model;

    public AppPresenter(AppCallbacks appCallbacks) {
        this.appCallbacks = appCallbacks;
    }


    public void stop() {

    }

    public void onFileSelected(File image) {
        System.out.println("onFileSelected");
        if (image != null) {
            currentImage = image;
            currentFileName = image.getAbsolutePath();
//            model = new IconModel("icon.png","#000000","#000000",);
            tryLoadPreview();
        }
    }

    private void tryLoadPreview() {
        System.out.println("tryLoadPreview " + currentFileName);
        System.out.println("tryLoadPreview path: " + currentImage.getPath());

        try {

            InputStream is = new BufferedInputStream(new FileInputStream(currentFileName));

            BufferedImage img = generateColoredIcon(ImageIO.read(is), "#ff0000");

            WritableImage image = SwingFXUtils.toFXImage(img, null);

            appCallbacks.loadIcon(image);
        } catch (Exception e) {
            // Do nothing
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private BufferedImage generateColoredIcon(BufferedImage image, String colorString) {
        Color color = Color.RED;

       /// / color = decodeColor(colorString);



        if (color == null) return image;

        int width = image.getWidth();
        int height = image.getHeight();
        boolean hasAlpha = image.getColorModel().hasAlpha();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = newImage.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int originalPixel = image.getRGB(xx, yy);
                int originalAlpha;
                if (hasAlpha) {
                    originalAlpha = new Color(originalPixel, true).getAlpha();
                } else {
                    // Due to ImageIO's issue, `hasAlpha` is assigned `false` although PNG file has alpha channel.
                    // Regarding PNG files of Material Icon, in this case, the file is 1bit depth binary(BLACK or WHITE).
                    // Therefore BLACK is `alpha = 0` and WHITE is `alpha = 255`
                    originalAlpha = originalPixel == 0xFF000000 ? 0 : 255;
                }

                int[] pixels = new int[4];
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                pixels[3] = combineAlpha(originalAlpha, color.getAlpha());
                raster.setPixel(xx, yy, pixels);
            }
        }
        return newImage;
    }

    /**
     * {@link Color#decode} only supports opaque colors. This replicates that code but adds support
     * for alpha stored as the highest byte.
     */
    private Color decodeColor(String argbColor) throws NumberFormatException {
        long colorBytes = Long.decode(argbColor);
        if (argbColor.length() < 8) {
            colorBytes |= 0xFF << 24;
        }
        // Must cast to int otherwise java chooses the float constructor instead of the int constructor
        return new Color((int) (colorBytes >> 16) & 0xFF, (int) (colorBytes >> 8) & 0xFF, (int) colorBytes & 0xFF, (int) (colorBytes >> 24) & 0xFF);
    }

    private int combineAlpha(int first, int second) {
        return (first * second) / 255;
    }

}
