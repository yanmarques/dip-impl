package dip.filters;

import dipfx.common.MouseInput;
import dipfx.common.PixelContext;
import dipfx.common.PixelContextFilter;
import javafx.scene.paint.Color;

public class PixelMarker extends PixelContextFilter {
    private MouseInput source;
    private MouseInput destination;
    private int[] axisXRange = new int[2];
    private int[] axisYRange = new int[2];

    public PixelMarker(MouseInput source, MouseInput destination) {
        this.source = source;
        this.destination = destination;
        this.preProcessPixelRanges();
    }

    public Color filter(PixelContext pixelContext) {
        if (this.isBorder(pixelContext)) {
            return new Color(1, 1, 1, pixelContext.getCurrentColor().getOpacity());
        }

        return pixelContext.getCurrentColor();
    }

    protected boolean isBorder(PixelContext pixelContext) {
        return this.isAxisXTheBorder(pixelContext) || this.isAxisYTheBorder(pixelContext);
    }

    protected boolean isAxisYTheBorder(PixelContext pixelContext) {
        int axisY = pixelContext.getAxisY();
        return (axisY == source.getAxisY() || axisY == destination.getAxisY()) &&
                this.isInRange(pixelContext.getAxisX(), axisXRange);
    }

    protected boolean isAxisXTheBorder(PixelContext pixelContext) {
        int axisX = pixelContext.getAxisX();
        return (axisX == source.getAxisX() || axisX == destination.getAxisX()) &&
                    this.isInRange(pixelContext.getAxisY(), axisYRange);
    }

    protected boolean isInRange(int value, int[] range) {
        return value >= range[0] && value <= range[1];
    }

    protected void preProcessPixelRanges() {
        if (source.getAxisX() > destination.getAxisX()) {
            axisXRange[0] = destination.getAxisX();
            axisXRange[1] = source.getAxisX();
        } else {
            axisXRange[0] = source.getAxisX();
            axisXRange[1] = destination.getAxisX();
        }

        if (source.getAxisY() > destination.getAxisY()) {
            axisYRange[0] = destination.getAxisY();
            axisYRange[1] = source.getAxisY();
        } else {
            axisYRange[0] = source.getAxisY();
            axisYRange[1] = destination.getAxisY();
        }
    }
}
