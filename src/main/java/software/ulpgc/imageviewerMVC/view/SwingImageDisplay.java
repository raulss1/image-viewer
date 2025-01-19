package software.ulpgc.imageviewerMVC.view;

import software.ulpgc.imageviewerMVC.model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;
    private BufferedImage scaledBitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.invalidate();
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (scaledBitmap != null) {
            int x = (this.getWidth() - scaledBitmap.getWidth()) / 2;
            int y = (this.getHeight() - scaledBitmap.getHeight()) / 2;
            g.drawImage(scaledBitmap, x, y, null);
        }
    }

    @Override
    public void invalidate() {
        if (bitmap == null) return;

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        if (panelWidth > 0 && panelHeight > 0) {
            Dimension resized = calculateResizedDimensions(panelWidth, panelHeight);
            if (resized.width > 0 && resized.height > 0) {
                scaledBitmap = scaleImage(bitmap, resized.width, resized.height);
            }
        }
    }

    private Dimension calculateResizedDimensions(int panelWidth, int panelHeight) {
        Dimension panelSize = new Dimension(panelWidth, panelHeight);
        Dimension bitmapSize = new Dimension(bitmap.getWidth(), bitmap.getHeight());
        return new Resizer(panelSize).resize(bitmapSize);
    }

    private BufferedImage scaleImage(BufferedImage image, int targetWidth, int targetHeight) {
        BufferedImage result = new BufferedImage(targetWidth, targetHeight, image.getType());

        for (int y = 0; y < targetHeight; y++) {
            for (int x = 0; x < targetWidth; x++) {
                int srcX = x * image.getWidth() / targetWidth;
                int srcY = y * image.getHeight() / targetHeight;
                int rgb = image.getRGB(srcX, srcY);
                result.setRGB(x, y, rgb);
            }
        }
        return result;
    }

    public static class Resizer {
        private final Dimension dimension;

        public Resizer(Dimension dimension) {
            this.dimension = dimension;
        }

        public Dimension resize(Dimension original) {
            double scaleWidth = dimension.getWidth() / original.width;
            double scaleHeight = dimension.getHeight() / original.height;

            double scale = Math.min(scaleWidth, scaleHeight);

            int newWidth = (int) (original.width * scale);
            int newHeight = (int) (original.height * scale);

            return new Dimension(newWidth, newHeight);
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
