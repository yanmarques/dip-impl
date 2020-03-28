package dip.filters;

import dipfx.common.BaseFilter;
import dipfx.common.PixelContext;
import javafx.scene.paint.Color;

public class NegativeScaleFilter extends BaseFilter {
    @Override
    public Color filter(PixelContext pixelContext) {
        Color oldColor = pixelContext.getCurrentColor();
        return new Color(1 - oldColor.getRed(),
                1 - oldColor.getGreen(), 1 - oldColor.getBlue(), oldColor.getOpacity());
    }
}
