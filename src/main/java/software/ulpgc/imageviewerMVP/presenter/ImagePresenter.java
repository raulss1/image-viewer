package software.ulpgc.imageviewerMVP.presenter;

import software.ulpgc.imageviewerMVP.model.ImageModel;
import software.ulpgc.imageviewerMVP.view.ImageDisplay;
import software.ulpgc.imageviewerMVP.view.ImageDisplay.Released;
import software.ulpgc.imageviewerMVP.view.ImageDisplay.Shift;

import java.io.IOException;

public class ImagePresenter {
    private final ImageDisplay display;
    private ImageModel image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
    }

    private void shift(int slideOffset) throws IOException {
        display.clear();
        display.paint(image.id(), slideOffset);
        if (slideOffset > 0)
            display.paint(image.prev().id(), slideOffset - display.getWidth());
        else
            display.paint(image.next().id(), display.getWidth() + slideOffset);
    }

    private void released(int slideOffset) throws IOException {
        if (Math.abs(slideOffset) >= display.getWidth() / 2)
            this.image = slideOffset > 0 ? image.prev() : image.next();
        repaint();
    }

    public void show(ImageModel image) throws IOException {
        this.image = image;
        repaint();
    }

    private void repaint() throws IOException {
        this.display.clear();
        this.display.paint(this.image.id(), 0);
    }
}
