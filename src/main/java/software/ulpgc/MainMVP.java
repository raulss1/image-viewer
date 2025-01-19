package software.ulpgc;

import software.ulpgc.imageviewerMVP.MainFrame;
import software.ulpgc.imageviewerMVP.io.FileImageLoader;
import software.ulpgc.imageviewerMVP.model.ImageModel;
import software.ulpgc.imageviewerMVP.presenter.ImagePresenter;

import java.io.IOException;

public class MainMVP {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        ImageModel initialImage = new FileImageLoader().load();
        ImagePresenter presenter = new ImagePresenter(mainFrame.getImageDisplay());
        presenter.show(initialImage);
        mainFrame.setVisible(true);
    }
}
