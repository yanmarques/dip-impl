package dip.common.histogram;

import javafx.scene.image.Image;

public class Factory {
    public static Data get(Image image, boolean aggregate, boolean excludeInvalidPixels) {
        Data histogramData = new Data();
        histogramData.build(image, aggregate, excludeInvalidPixels);
        return histogramData;
    }

    public static Data get(Image image) {
        // most lightweight version
        return get(image, false, false);
    }
}
