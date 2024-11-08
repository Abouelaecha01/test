package com.example.cv.config;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ImageProcessor {

    // Method to make the image circular
    public static BufferedImage makeCircularImage(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int diameter = Math.min(width, height);

        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circularImage.createGraphics();

        // Apply anti-aliasing for smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a circular shape and clip the image
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(inputImage, 0, 0, diameter, diameter, null);
        g2.dispose();

        return circularImage;
    }
}
