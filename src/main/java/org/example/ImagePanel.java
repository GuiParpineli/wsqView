package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        // Armazenar a imagem para desenhar na próxima pintura do painel
        this.image = image;

        // Redimensionar o painel para se ajustar à nova imagem
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

        // Faça a GUI realizar o layout novamente desde que este painel mudou de tamanho
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Deixe a classe JPanel limpar o fundo
        super.paintComponent(g);

        if (image != null) {
            // Desenhar imagem no painel
            g.drawImage(image, 0, 0, this);
        }
    }
}