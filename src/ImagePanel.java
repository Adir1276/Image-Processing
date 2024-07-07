import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class ImagePanel extends JPanel {
    private BufferedImage image;
    private BufferedImage original;
    private int divideX = 0;

    public ImagePanel(BufferedImage original){
        this.original = original;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                divideX = e.getX();
                repaint();
            }
        });
    }

    private BufferedImage finalImage(int divider) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage finalImg = new BufferedImage(width, height, original.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb;
                if (x<=divider)
                    rgb = original.getRGB(x, y);
                else
                    rgb = image.getRGB(x, y);
                finalImg.setRGB(x, y, rgb);
            }
        }
        return finalImg;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();
            double imgAspect = (double) imgHeight / imgWidth;

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            double panelAspect = (double) panelHeight / panelWidth;

            int drawWidth = panelWidth;
            int drawHeight = panelHeight;

            if (imgAspect < panelAspect) {
                drawHeight = (int) (panelWidth * imgAspect);
            } else {
                drawWidth = (int) (panelHeight / imgAspect);
            }

            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;
            float divider = (float)(divideX-x)/(float)drawWidth;
            divider *= (float)original.getWidth();
            g.drawImage(finalImage((int) divider), x, y, drawWidth, drawHeight, this);
            g.setColor(Color.RED);
            g.drawLine(divideX, 0, divideX, drawHeight);
        }
    }


}