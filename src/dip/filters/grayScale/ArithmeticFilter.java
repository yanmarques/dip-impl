package dip.filters.grayScale;

import dipfx.common.PixelContext;
import dipfx.common.PixelContextFilter;
import javafx.scene.paint.Color;

public class ArithmeticFilter extends PixelContextFilter {
    public Color filter(PixelContext context) {
        Color oldColor = context.getCurrentColor();
        double average = this.calcScale(oldColor);
        return new Color(average, average, average, oldColor.getOpacity());
    }

    protected double calcScale(Color oldColor) {
        return (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
    }
}
