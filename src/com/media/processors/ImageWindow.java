package com.media.processors;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImageWindow {

// This inner class is our canvas.
// We draw the image on it.
class ImagePanel extends JComponent {

        BufferedImage theImage = null;

        ImagePanel( BufferedImage image )           {
                super();
                theImage = image;
        }

        public BufferedImage getImage( ) {
                return theImage;
        }

        public void setImage( BufferedImage image ) {
                theImage = image;
                this.updatePanel();
        }

        public void updatePanel() {

                invalidate();
                getParent().doLayout();
                repaint();
        }

        public void paintComponent( Graphics g ) {

                int w = theImage.getWidth( );
                int h = theImage.getHeight( );

                g.drawImage( theImage, 0,0, w,h, this );
        }
}  // end ImagePanel inner class

// Constructor
public ImageWindow( String file) {

        // open image
        BufferedImage image = openImageFile( file );

        // create a panel for it
        ImagePanel theImagePanel  = new ImagePanel( image );

        // display the panel in a JFrame
        createWindowForPanel( theImagePanel, file );

        // filter the image
        filterImage( theImagePanel );
}

public void filterImage( ImagePanel panel ) {

        SmartBlurFilter filter = new SmartBlurFilter( );

        BufferedImage newImage = filter.filter( panel.getImage( ) );

        panel.setImage( newImage );
}

public void createWindowForPanel( ImagePanel theImagePanel, String name ) {

        BufferedImage image = theImagePanel.getImage();
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle( name );
        mainFrame.setBounds(50,80,image.getWidth( )+10, image.getHeight( )+10);
        mainFrame.setDefaultCloseOperation(3);
        mainFrame.getContentPane().add( theImagePanel );
        mainFrame.setVisible(true);
}

BufferedImage openImageFile( String fname ) {

        BufferedImage img = null;

        try {
                File f = new File( fname );
                if ( f.exists( ) )
                img = ImageIO.read(f);
        }
        catch (Exception e) {
                e.printStackTrace();
        }

        return img;
}

public static void main( String[] args ) {

	String file="C:\\Workspaces\\Java_Code\\RESTfulJerseyHelloWorld\\orange.jpg";
        new ImageWindow(file);
}
}