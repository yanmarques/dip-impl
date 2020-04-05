package dip.filters.noise;

import dipfx.common.BaseFilter;
import dipfx.common.PixelContext;
import dipfx.common.PixelToolkit;
import javafx.scene.paint.Color;

import java.util.ArrayList;

abstract public class BaseNoiseFilter extends BaseFilter {
    public BaseNoiseFilter() {
        this.setPixelToolkit(new PixelToolkit(this::filter, 1, -1, -1));
    }

    abstract public ArrayList<Color> getNeighbours(PixelContext pixelContext);

    @Override
    public Color filter(PixelContext pixelContext) {
        ArrayList<Color> neighbours = this.getNeighbours(pixelContext);
        neighbours.add(pixelContext.getCurrentColor());

        int median = neighbours.size() / 2;

        neighbours.sort(this::compareRed);
        double red = neighbours.get(median).getRed();

        neighbours.sort(this::compareGreen);
        double green = neighbours.get(median).getGreen();

        neighbours.sort(this::compareBlue);
        double blue = neighbours.get(median).getBlue();

        return new Color(red, green, blue, pixelContext.getCurrentColor().getOpacity());
    }

    protected int compareRed(Color color, Color t1) {
        return Double.compare(color.getRed(), t1.getRed());
    }

    protected int compareGreen(Color color, Color t1) {
        return Double.compare(color.getGreen(), t1.getGreen());
    }

    protected int compareBlue(Color color, Color t1) {
        return Double.compare(color.getBlue(), t1.getBlue());
    }
}
