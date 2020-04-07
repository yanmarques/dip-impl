package dip.filters.multiImg;

import dipfx.common.Unit;
import dipfx.common.multiImg.MixedContextFilter;
import dipfx.common.multiImg.MixedPixelContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AddFilter extends MixedContextFilter {
    private Unit srcUnit;
    private Unit dstUnit;
    private double currentSrcValue;
    private double currentDstValue;

    public AddFilter(Unit srcUnit, Unit dstUnit) {
        this.srcUnit = srcUnit;
        this.dstUnit = dstUnit;
    }

    @Override
    public Image run(Image source, Image target) {
        this.currentDstValue = (double) dstUnit.getValue() / 100;
        this.currentSrcValue = (double) srcUnit.getValue() / 100;
        return super.run(source, target);
    }

    @Override
    public Color filter(MixedPixelContext pixelContext) {
        Color srcImgColor = pixelContext.getCurrentColor();
        Color dstImageColor = pixelContext.getTargetContext().getCurrentColor();

        double r = (srcImgColor.getRed() * currentSrcValue) + (dstImageColor.getRed() * currentDstValue);
        double g = (srcImgColor.getGreen() * currentSrcValue) + (dstImageColor.getGreen() * currentDstValue);
        double b = (srcImgColor.getBlue() * currentSrcValue) + (dstImageColor.getBlue() * currentDstValue);

        return new Color(
                this.oneOrItself(r),
                this.oneOrItself(g),
                this.oneOrItself(b),
                1
        );
    }

    protected double oneOrItself(double value) {
        return value > 1 ? 1 : value;
    }
}
