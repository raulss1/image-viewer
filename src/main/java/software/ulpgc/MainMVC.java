package software.ulpgc;

import software.ulpgc.imageviewerMVC.MainFrame;
import software.ulpgc.imageviewerMVC.control.NextImageCommand;
import software.ulpgc.imageviewerMVC.control.PreviousImageCommand;
import software.ulpgc.imageviewerMVC.io.FileImageLoader;
import software.ulpgc.imageviewerMVC.model.Image;

import java.io.File;

public class MainMVC {
    public static final String root = "src/main/resources"; //Cambie aquí la ruta a su gusto para visualizar sus propias imágenes
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.add("previous", new PreviousImageCommand(frame.imageDisplay()));
        frame.add("next", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}
