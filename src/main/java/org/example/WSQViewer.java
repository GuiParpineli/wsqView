package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WSQViewer extends JFrame {

    private final WSQtoPNGConverter converter;
    private final JFileChooser fileChooser;
    private final ImagePanel imagePanel;

    private BufferedImage currentImage;

    public WSQViewer() {

        this.converter = new WSQtoPNGConverter();

        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        this.imagePanel = new ImagePanel();

        this.setLayout(new BorderLayout());

        JButton openButton = new JButton("Nova Imagem");
        openButton.addActionListener(e -> openImage());

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> saveImage());

        JPanel buttonPanel =  new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        this.setTitle("WSQ Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    private void openImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                converter.convert(file.getPath(), "temp.png");
                currentImage = ImageIO.read(new File("temp.png"));
                imagePanel.setImage(currentImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImage() {
        if (currentImage != null) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(currentImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WSQViewer().setVisible(true));
    }
}