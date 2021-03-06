package dip.graphics;

import dip.common.histogram.Data;
import dip.filters.EqualizerFilter;
import dip.filters.NegativeScaleFilter;
import dip.filters.PixelMarker;
import dip.filters.ThresholdFilter;
import dip.filters.grayScale.ArithmeticFilter;
import dip.filters.grayScale.WeightedFilter;
import dip.filters.multiImg.AddFilter;
import dip.filters.multiImg.SubFilter;
import dip.filters.noise.CrossFilter;
import dip.filters.noise.InXFilter;
import dip.filters.noise.SquareNoise;
import dipfx.common.MouseInput;
import dipfx.graphics.BaseHistogramController;
import dipfx.graphics.MainController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Controller extends MainController {
    public void initialize() {
        super.initialize();
        this.filters.put("arithmetic-gray-scale", new ArithmeticFilter());
        this.filters.put("negative-scale", new NegativeScaleFilter());
        this.filters.put("in-x-noise", new InXFilter());
        this.filters.put("cross-noise", new CrossFilter());
        this.filters.put("square-noise", new SquareNoise());

        this.filters.put("weighted-gray-scale", new WeightedFilter(this.redSliderUnit,
                        this.greenSliderUnit, this.blueSliderUnit));
        this.filters.put("threshold", new ThresholdFilter(this.thresholdUnit));

        this.multiImgFilters.put("add-multi-image", new AddFilter(this.srcMultiImgUnit, this.dstMultiImgUnit));
        this.multiImgFilters.put("sub-multi-image", new SubFilter());

        this.filters.put("equalizer", new EqualizerFilter(this.ckOnlyValidPixelsUnit));
    }

    @Override
    public void setPixelColor(Image image, Color color) {
        setRed(color.getRed());
        setBlue(color.getGreen());
        setGreen(color.getBlue());
    }

    @Override
    public Color getImagePixelColor(Image image, int mouseX, int mouseY) {
        return image.getPixelReader().getColor(mouseX, mouseY);
    }

    @Override
    public int colorToDecimal(double color) {
        return Data.toTone(color);
    }

    @Override
    public Image onImageMark(MouseInput srcInput, MouseInput dstInput, Image image) {
        return new PixelMarker(srcInput, dstInput).run(image);
    }

    @Override
    public BaseHistogramController getHistogramController() {
        return new HistogramController();
    }
}
