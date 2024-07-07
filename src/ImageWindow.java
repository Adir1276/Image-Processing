import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private ImagePanel imagePanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    BufferedImage bufferedImage;

    public ImageWindow (File selectedFile) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        buttonsPanelSetup();

        try {
        bufferedImage = ImageIO.read(selectedFile);
        imagePanel = new ImagePanel(bufferedImage);
        imagePanel.setImage(bufferedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        add(imagePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
    }

    public void buttonsPanelSetup(){
        bottomPanel = new JPanel();
        topPanel = new JPanel();
        JButton grayscale = new JButton("Grayscale");
        grayscale.addActionListener((event) -> {
        bufferedImage = toGraycale(bufferedImage);
        imagePanel.setImage(bufferedImage);
        });
        JButton shiftRight = new JButton("Color Shift Right");
        shiftRight.addActionListener((event) -> {
            bufferedImage = colorShiftRight(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton shiftLeft = new JButton("Color Shift Left");
        shiftLeft.addActionListener((event) -> {
            bufferedImage = colorShiftLeft(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton mirrorImage = new JButton("Mirror Image");
        mirrorImage.addActionListener((event) -> {
            bufferedImage = mirrorImage(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton invertedColors = new JButton("Invert Colors");
        invertedColors.addActionListener((event) -> {
            bufferedImage = invertImage(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton blackWhite = new JButton("Black White");
        blackWhite.addActionListener((event) -> {
            bufferedImage = toBlackAndWhite(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton sepia = new JButton("Sepia");
        sepia.addActionListener((event) -> {
            bufferedImage = toSepia(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton bright = new JButton("Brighten");
        bright.addActionListener((event) -> {
            bufferedImage = brighten(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton dark = new JButton("Darken");
        dark.addActionListener((event) -> {
            bufferedImage = darken(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        JButton contrast = new JButton("Contrast");
        contrast.addActionListener((event) -> {
            bufferedImage = increaseContrast(bufferedImage);
            imagePanel.setImage(bufferedImage);
        });
        bottomPanel.add(shiftRight);
        bottomPanel.add(shiftLeft);
        bottomPanel.add(grayscale);
        bottomPanel.add(mirrorImage);
        bottomPanel.add(invertedColors);
        topPanel.add(blackWhite);
        topPanel.add(sepia);
        topPanel.add(bright);
        topPanel.add(dark);
        topPanel.add(contrast);


    }

    private BufferedImage toGraycale(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int gray = (red + green + blue) / 3;
                int grayRgb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
                grayImage.setRGB(x, y, grayRgb);
            }
        }
        return grayImage;
    }

    private BufferedImage colorShiftRight(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage shiftedImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = blue;
                int newGreen = red;
                int newBlue = green;

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                shiftedImage.setRGB(x, y, newRgb);
            }
        }
        return shiftedImage;
    }

    private BufferedImage colorShiftLeft(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage shiftedImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = green;
                int newGreen = blue;
                int newBlue = red;

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                shiftedImage.setRGB(x, y, newRgb);
            }
        }
        return shiftedImage;
    }

    private BufferedImage mirrorImage(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage mirroredImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                mirroredImage.setRGB(width - 1 - x, y, rgb);
            }
        }
        return mirroredImage;
    }

    private BufferedImage invertImage(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage invertedImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = 255 - red;
                int newGreen = 255 - green;
                int newBlue = 255 - blue;

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                invertedImage.setRGB(x, y, newRgb);
            }
        }
        return invertedImage;
    }

    private BufferedImage toBlackAndWhite(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int luminance = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                int threshold = 128;
                int bw = luminance < threshold ? 0 : 255;

                int newRgb = (0xFF << 24) | (bw << 16) | (bw << 8) | bw;
                bwImage.setRGB(x, y, newRgb);
            }
        }
        return bwImage;
    }

    private BufferedImage toSepia(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage sepiaImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                newRed = Math.min(255, newRed);
                newGreen = Math.min(255, newGreen);
                newBlue = Math.min(255, newBlue);

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                sepiaImage.setRGB(x, y, newRgb);
            }
        }
        return sepiaImage;
    }

    private BufferedImage brighten(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage brightImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = (int) (red * 1.2f);
                int newGreen = (int) (green * 1.2f);
                int newBlue = (int) (blue * 1.2f);

                newRed = Math.min(255, newRed);
                newGreen = Math.min(255, newGreen);
                newBlue = Math.min(255, newBlue);

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                brightImage.setRGB(x, y, newRgb);
            }
        }
        return brightImage;
    }

    private BufferedImage darken(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage darkImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = (int) (red * 0.8f);
                int newGreen = (int) (green * 0.8f);
                int newBlue = (int) (blue * 0.8f);

                newRed = Math.min(255, newRed);
                newGreen = Math.min(255, newGreen);
                newBlue = Math.min(255, newBlue);

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                darkImage.setRGB(x, y, newRgb);
            }
        }
        return darkImage;
    }

    private BufferedImage increaseContrast(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        float contrastFactor = 1.2f;
        BufferedImage contrastImage = new BufferedImage(width, height, original.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int newRed = (int) (128 + contrastFactor * (red - 128));
                int newGreen = (int) (128 + contrastFactor * (green - 128));
                int newBlue = (int) (128 + contrastFactor * (blue - 128));

                newRed = Math.min(255, Math.max(0, newRed));
                newGreen = Math.min(255, Math.max(0, newGreen));
                newBlue = Math.min(255, Math.max(0, newBlue));

                int newRgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                contrastImage.setRGB(x, y, newRgb);
            }
        }
        return contrastImage;
    }


}
