package dip.filters.noise;

import dipfx.common.PixelContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SquareNoise extends BaseNoiseFilter {
    @Override
    public ArrayList<Color> getNeighbours(PixelContext pixelContext) {
        ArrayList<Color> neighbours = new CrossFilter().getNeighbours(pixelContext);
        neighbours.addAll(new InXFilter().getNeighbours(pixelContext));
        return neighbours;
    }
}
