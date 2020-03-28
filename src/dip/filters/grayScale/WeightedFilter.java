package dip.filters.grayScale;

import dipfx.common.Unit;
import javafx.scene.paint.Color;

public class WeightedFilter extends ArithmeticFilter {
    private Unit redUnit;
    private Unit greenUnit;
    private Unit blueUnit;

    public WeightedFilter(Unit redUnit, Unit greenUnit, Unit blueUnit) {
        this.redUnit = redUnit;
        this.greenUnit = greenUnit;
        this.blueUnit = blueUnit;
    }

    @Override
    protected double calcScale(Color oldColor) {
        int redWeight = this.redUnit.getValue();
        int greenWeight = this.greenUnit.getValue();
        int blueWeight = this.blueUnit.getValue();

        return (oldColor.getRed() * redWeight + oldColor.getGreen() * greenWeight + oldColor.getBlue() * blueWeight) / 100;
    }
}
