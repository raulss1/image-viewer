package software.ulpgc.imageviewerMVP.model;

public interface ImageModel {
    String id();
    ImageModel next();
    ImageModel prev();
}
