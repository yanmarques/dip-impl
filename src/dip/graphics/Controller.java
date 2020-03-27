package dip.graphics;

import dip.filters.GrayScaleFilter;
import dipfx.graphics.MainController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Controller extends MainController {
    public void initialize() {
        super.initialize();
        this.filters.put("gray-scale", new GrayScaleFilter());
    }

    @Override
    public void setPixelColor(Image image, Color color) {
        setRed(color.getRed());
        setBlue(color.getGreen());
        setGreen(color.getBlue());
    }

    @Override
    public Color getImagePixelColor(Image image, int mouseX, int mouseY) {
        return image.getPixelReader().getColor(mouseX, mouseY);
    }

    @Override
    public int colorToDecimal(double color) {
        return (int) (color * 255);
    }
}
