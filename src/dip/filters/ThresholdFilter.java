package dip.filters;

import dipfx.common.PixelContext;
import dipfx.common.PixelContextFilter;
import dipfx.common.Unit;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ThresholdFilter extends PixelContextFilter {
    private Unit thresholdUnit;
    private double currentValue;

    public ThresholdFilter(Unit thresholdUnit) {
        this.thresholdUnit = thresholdUnit;
    }

    @Override
    public Image run(Image source) {
        this.currentValue = (double) this.thresholdUnit.getValue() / 255;
        return super.run(source);
    }

    @Override
    public Color filter(PixelContext pixelContext) {
        Color oldColor = pixelContext.getCurrentColor();

        if (oldColor.getRed() >= this.currentValue) {
            return new Color(1, 1, 1, oldColor.getOpacity());
        }

        return new Color(0, 0, 0, oldColor.getOpacity());
    }
}
