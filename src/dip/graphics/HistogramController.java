package dip.graphics;

import dipfx.common.PixelContext;
import dipfx.common.SimplePixelToolkit;
import dipfx.graphics.BaseHistogramController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HistogramController extends BaseHistogramController {
    public int[] getHistogramFor(Image image) {
        int[] histogram = new int[256];

        new SimplePixelToolkit<>(pixelContext -> {
            Color currentColor = pixelContext.getCurrentColor();

            histogram[(int) (currentColor.getRed() * 255)]++;
            histogram[(int) (currentColor.getGreen() * 255)]++;
            histogram[(int) (currentColor.getBlue() * 255)]++;

            return currentColor;
        }).apply(new PixelContext(image));

        return histogram;
    }
}
