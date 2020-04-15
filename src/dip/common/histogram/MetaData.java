package dip.common.histogram;

import javafx.scene.image.Image;

public class MetaData {
    private double pixelsCount;

    private int redValidPixelCount;
    private int greenValidPixelCount;
    private int blueValidPixelCount;

    private int firstRedTone;
    private int firstGreenTone;
    private int firstBlueTone;

    public MetaData(Data histogram, Image image, boolean excludeInvalidPixels) {
        this.pixelsCount = image.getWidth() * image.getHeight();

        if (excludeInvalidPixels) {
            this.redValidPixelCount = this.getValidPixelCountFor(histogram.getRed());
            this.greenValidPixelCount = this.getValidPixelCountFor(histogram.getGreen());
            this.blueValidPixelCount = this.getValidPixelCountFor(histogram.getBlue());

            this.firstRedTone = this.getFirstValidToneIndexFor(histogram.getRed());
            this.firstGreenTone = this.getFirstValidToneIndexFor(histogram.getGreen());
            this.firstBlueTone = this.getFirstValidToneIndexFor(histogram.getBlue());
        } else {
            this.redValidPixelCount = 255;
            this.greenValidPixelCount = 255;
            this.blueValidPixelCount = 255;

            this.firstRedTone = 0;
            this.firstGreenTone = 0;
            this.firstBlueTone = 0;
        }
    }

    public double getPixelsCount() {
        return pixelsCount;
    }

    public int getRedValidPixelCount() {
        return redValidPixelCount;
    }

    public int getGreenValidPixelCount() {
        return greenValidPixelCount;
    }

    public int getBlueValidPixelCount() {
        return blueValidPixelCount;
    }

    public int getFirstRedTone() {
        return firstRedTone;
    }

    public int getFirstGreenTone() {
        return firstGreenTone;
    }

    public int getFirstBlueTone() {
        return firstBlueTone;
    }

    protected int getValidPixelCountFor(int[] histogram) {
        int invalidCount = 0;

        for (int tone : histogram) {
            if (tone == 0) {
                invalidCount++;
            }
        }

        return 255 - invalidCount;
    }

    protected int getFirstValidToneIndexFor(int[] histogram) {
        for (int index = 0; index < histogram.length; index++) {
            if (histogram[index] > 0) {
                return index;
            }
        }

        return 0;
    }
}
