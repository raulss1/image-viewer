package software.ulpgc.imageviewerMVC.view;

import software.ulpgc.imageviewerMVC.model.Image;

public interface ImageDisplay {
    void show(Image image);
    Image image();
}
