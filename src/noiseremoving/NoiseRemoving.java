/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author pj
 */
public class NoiseRemoving
{
    private static JButton cleanButton;
    private static JButton saveButton;
    private static JButton loadButton;
    private static JFileChooser imageFileChooser;
    private static JLabel imageLabel;
    private static ImageProcess ip;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Create the main frame of the GUI.
        JFrame frame = new JFrame("Noise Removing GUI Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(974, 648);
        frame.setLayout(new BorderLayout());
        
        // Create the image Label for displaying the chosen image.
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);
        
        // Create button panel to store all the action buttons.
        JPanel buttonPanel = new JPanel();
        
        loadButton = new JButton("Load Image"); // Load button for loading the image.
        cleanButton = new JButton("Clean/Remove Noise"); // Clean button for removing the noise.
        saveButton = new JButton("Save Image"); // Save button for saving the image.
        
        // Create the file chooser for image selection
        imageFileChooser = new JFileChooser(new File("."));
        loadButton.addActionListener((ActionEvent e) -> 
        {
            int stateImageFileChooser = imageFileChooser.showOpenDialog(null);
        
            if(stateImageFileChooser == JFileChooser.APPROVE_OPTION)
            {
                String fileName = imageFileChooser.getSelectedFile().getPath();
                ip = new ImageProcess(fileName);
                outputImage(ip.buffered_image);
            }
        });

        cleanButton.addActionListener((ActionEvent e) -> 
        {
            if(ip != null)
            {
                ip.removeNoise();
                outputImage(ip.buffered_image);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You need to load an image!");
            }
        });

        saveButton.addActionListener((ActionEvent e) -> 
        {
            if(ip != null)
            {
                ip.save("noise_removed.jpg");
                JOptionPane.showMessageDialog(null, "Image successfully saved to noise_removed.jpg!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You need to load an image!");
            }
        });
        buttonPanel.add(loadButton);
        buttonPanel.add(cleanButton);
        buttonPanel.add(saveButton);
        
        // Add the panel to the frame at the button of the border.
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Method: outputImage
     * 
     * This method is used to display the given image to the image label. It takes in a bufferImage as a parameter and
     * assigns it to a ImageIcon called icon. Then, assigns the icon to the imageLabel to display it to the GUI.
     * 
     */
    private static void outputImage(BufferedImage image)
    {
        // Resize the image to fit the screen
        int labelWidth = imageLabel.getWidth();
        int labelHeight = imageLabel.getHeight();
        Image resizedImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(resizedImage);
        imageLabel.setIcon(icon);
    }
}
