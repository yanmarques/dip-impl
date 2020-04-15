package dip.common.histogram;

import dipfx.common.PixelContext;
import dipfx.common.SimplePixelToolkit;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Data {
    final private static int LENGTH = 256;

    private int[] red;
    private int[] green;
    private int[] blue;
    private int[] redAgg;
    private int[] greenAgg;
    private int[] blueAgg;
    private MetaData metaData;

    public Data() {
        this.red = new int[LENGTH];
        this.green = new int[LENGTH];
        this.blue = new int[LENGTH];
    }

    public static int toTone(double color) {
        return (int) (color * 255);
    }

    public static int redToTone(Color color) {
        return toTone(color.getRed());
    }

    public static int greenToTone(Color color) {
        return toTone(color.getGreen());
    }

    public static int blueToTone(Color color) {
        return toTone(color.getBlue());
    }

    public void build(Image image, boolean aggregate, boolean excludeInvalidPixels) {
        new SimplePixelToolkit<>(this::factory).apply(new PixelContext(image));

        if (aggregate) {
            this.redAgg = this.buildAggregateFor(this.red);
            this.greenAgg = this.buildAggregateFor(this.green);
            this.blueAgg = this.buildAggregateFor(this.blue);
        }

        this.metaData = new MetaData(this, image, excludeInvalidPixels);
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public int[] getRed() {
        return red;
    }

    public int[] getGreen() {
        return green;
    }

    public int[] getBlue() {
        return blue;
    }

    public int[] getRedAgg() {
        return redAgg;
    }

    public int[] getGreenAgg() {
        return greenAgg;
    }

    public int[] getBlueAgg() {
        return blueAgg;
    }

    protected Color factory(PixelContext pixelContext) {
        Color currentColor = pixelContext.getCurrentColor();

        this.red[redToTone(currentColor)]++;
        this.green[greenToTone(currentColor)]++;
        this.blue[blueToTone(currentColor)]++;

        return currentColor;
    }

    protected int[] buildAggregateFor(int[] histogram) {
        int[] aggData = new int[LENGTH];
        int lastAggValue = histogram[0];
        aggData[0] = lastAggValue;

        for (int index = 1; index < histogram.length; index++) {
            lastAggValue += histogram[index];
            aggData[index] = lastAggValue;
        }

        return aggData;
    }
}
