package dip.filters.grayScale;

import dipfx.common.Unit;
import javafx.scene.paint.Color;

public class WeightedFilter extends ArithmeticFilter {
    private Unit<Integer> redUnit;
    private Unit<Integer> greenUnit;
    private Unit<Integer> blueUnit;

    public WeightedFilter(Unit<Integer> redUnit, Unit<Integer> greenUnit, Unit<Integer> blueUnit) {
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
