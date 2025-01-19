package software.ulpgc.imageviewerMVP.io;

import java.awt.image.BufferedImage;

public interface Reader {
    BufferedImage read(String name);
}
