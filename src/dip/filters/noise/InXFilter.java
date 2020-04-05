package dip.filters.noise;

import dipfx.common.PixelContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InXFilter extends BaseNoiseFilter {
    @Override
    public ArrayList<Color> getNeighbours(PixelContext pixelContext) {
        ArrayList<Color> neighbours = new ArrayList<>();
        int x = pixelContext.getAxisX();
        int y = pixelContext.getAxisY();

        neighbours.add(pixelContext.getPixelReader().getColor(x - 1, y + 1));
        neighbours.add(pixelContext.getPixelReader().getColor(x  + 1, y - 1));
        neighbours.add(pixelContext.getPixelReader().getColor(x + 1, y + 1));
        neighbours.add(pixelContext.getPixelReader().getColor(x - 1, y - 1));

        return neighbours;
    }
}
