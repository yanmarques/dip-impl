package dip.filters;

import dipfx.common.BaseFilter;
import dipfx.common.PixelContext;
import javafx.scene.paint.Color;

public class GrayScaleFilter extends BaseFilter {
    public Color filter(PixelContext context) {
        Color oldColor = context.getCurrentColor();
        double average = this.calcScale(oldColor);
        return new Color(average, average, average, oldColor.getOpacity());
    }

    protected double calcScale(Color oldColor) {
        return (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
    }
}
