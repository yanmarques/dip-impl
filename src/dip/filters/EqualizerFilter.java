package dip.filters;

import dip.common.histogram.Data;
import dip.common.histogram.Factory;
import dip.common.histogram.MetaData;
import dipfx.common.PixelContext;
import dipfx.common.PixelContextFilter;
import dipfx.common.Unit;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EqualizerFilter extends PixelContextFilter {
    private Unit<Boolean> ckOnlyValidPixels;
    private Data histogram;

    private double redUpperCount;
    private double greenUpperCount;
    private double blueUpperCount;

    public EqualizerFilter(Unit<Boolean> ckOnlyValidPixels) {
        this.ckOnlyValidPixels = ckOnlyValidPixels;
    }

    @Override
    public Image run(Image source) {
        this.preProcess(source);
        return super.run(source);
    }

    @Override
    public Color filter(PixelContext pixelContext) {
        Color currentColor = pixelContext.getCurrentColor();

        int redAgg = this.histogram.getRedAgg()[Data.redToTone(currentColor)];
        int greenAgg = this.histogram.getGreenAgg()[Data.greenToTone(currentColor)];
        int blueAgg = this.histogram.getBlueAgg()[Data.blueToTone(currentColor)];

        MetaData metaData = this.histogram.getMetaData();

        double redValue = this.redUpperCount * redAgg;
        double greenValue = this.greenUpperCount * greenAgg;
        double blueValue = this.blueUpperCount * blueAgg;

        return new Color(
                (metaData.getFirstRedTone() + redValue) / 255,
                (metaData.getFirstGreenTone() + greenValue) / 255,
                (metaData.getFirstBlueTone() + blueValue) / 255,
                currentColor.getOpacity());
    }

    protected void preProcess(Image source) {
        this.histogram = Factory.get(source, true, this.ckOnlyValidPixels.getValue());

        MetaData metaData = this.histogram.getMetaData();
        this.redUpperCount = (metaData.getRedValidPixelCount() - 1) / metaData.getPixelsCount();
        this.greenUpperCount = (metaData.getGreenValidPixelCount() - 1) / metaData.getPixelsCount();
        this.blueUpperCount = (metaData.getBlueValidPixelCount() - 1) / metaData.getPixelsCount();
    }
}
