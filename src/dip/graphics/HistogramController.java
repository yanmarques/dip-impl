package dip.graphics;

import dip.common.histogram.Data;
import dip.common.histogram.Factory;
import dipfx.graphics.BaseHistogramController;
import javafx.scene.image.Image;

public class HistogramController extends BaseHistogramController {
    public int[] getHistogramFor(Image image) {
        Data histogram = Factory.get(image);

        int[] unifiedHistogram = new int[256];
        for (int index = 0; index < unifiedHistogram.length; index++) {
            unifiedHistogram[index] = histogram.getRed()[index] + histogram.getBlue()[index] + histogram.getGreen()[index];
        }

        return unifiedHistogram;
    }
}
