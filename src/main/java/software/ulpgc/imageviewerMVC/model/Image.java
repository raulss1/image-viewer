package software.ulpgc.imageviewerMVC.model;

public interface Image {
    String name();
    Image next();
    Image prev();
}
