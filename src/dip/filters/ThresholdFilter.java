package dip.filters;

import dipfx.common.BaseFilter;
import dipfx.common.PixelContext;
import dipfx.common.Unit;
import javafx.scene.paint.Color;

public class ThresholdFilter extends BaseFilter {
    private Unit thresholdUnit;

    public ThresholdFilter(Unit thresholdUnit) {
        this.thresholdUnit = thresholdUnit;
    }

    @Override
    public Color filter(PixelContext pixelContext) {
        Color oldColor = pixelContext.getCurrentColor();

        if ((oldColor.getRed() * 255) >= this.thresholdUnit.getValue()) {
            return new Color(1, 1, 1, oldColor.getOpacity());
        }

        return new Color(0, 0, 0, oldColor.getOpacity());
    }
}
