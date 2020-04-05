package dip.graphics;

import dip.filters.NegativeScaleFilter;
import dip.filters.ThresholdFilter;
import dip.filters.grayScale.ArithmeticFilter;
import dip.filters.grayScale.WeightedFilter;
import dip.filters.noise.CrossFilter;
import dip.filters.noise.InXFilter;
import dip.filters.noise.SquareNoise;
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
        return (int) (color * 255);
    }
}
