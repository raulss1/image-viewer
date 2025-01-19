package software.ulpgc.imageviewerMVP.io;

import software.ulpgc.imageviewerMVP.model.ImageModel;

import java.io.File;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader() {
        this.files = (new File("src/main/resources")).listFiles();
    }

    @Override
    public ImageModel load() {
        return imageAt(0);
    }

    private ImageModel imageAt(int i) {
        return new ImageModel() {
            @Override
            public String id() {
                return files != null ? files[i].getAbsolutePath() : null;
            }

            @Override
            public ImageModel next() {
                return imageAt((i+1) % files.length);
            }

            @Override
            public ImageModel prev() {
                return imageAt(i > 0 ? i -1 : files.length-1);
            }
        };
    }
}
