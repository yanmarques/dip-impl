package dip.filters.multiImg;

import dipfx.common.multiImg.MixedContextFilter;
import dipfx.common.multiImg.MixedPixelContext;
import javafx.scene.paint.Color;

public class SubFilter extends MixedContextFilter {
    @Override
    public Color filter(MixedPixelContext pixelContext) {
        Color srcImgColor = pixelContext.getCurrentColor();
        Color dstImgColor = pixelContext.getTargetContext().getCurrentColor();

        double r = srcImgColor.getRed() - dstImgColor.getRed();
        double g = srcImgColor.getGreen() - dstImgColor.getGreen();
        double b = srcImgColor.getBlue() - dstImgColor.getBlue();

        return new Color(
            this.zeroOrItself(r),
            this.zeroOrItself(g),
            this.zeroOrItself(b),
            srcImgColor.getOpacity()
        );
    }

    protected double zeroOrItself(double value) {
        return value < 0 ? 0 : value;
    }
}
