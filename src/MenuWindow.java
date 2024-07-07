import javax.swing.*;
import java.io.File;

public class MenuWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public String imgPath;

    public MenuWindow () {
        JButton imgButton = new JButton("Select Image");
        imgButton.setBounds(325, 300, 150, 40);
        imgButton.addActionListener((event) -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "bmp"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imgPath = selectedFile.getAbsolutePath();
                    ImageWindow imageWindow = new ImageWindow(selectedFile);
                    this.setVisible(false);
                }
        });
        add(imgButton);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }
}
