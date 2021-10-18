package design;

import javax.swing.*;
import java.awt.*;

public class JPanelConFondo extends JPanel {

    ImageIcon imagen;
    String nombreImagen;


    public JPanelConFondo(String nombreImagen){
        this.nombreImagen = nombreImagen;
    }

    public void setImagen(String nombreImagen){
        this.nombreImagen = nombreImagen;
    }

    public void paint(Graphics g){
        Dimension size = getSize();
        imagen = new ImageIcon(nombreImagen);
        g.drawImage(imagen.getImage(),0,0,size.width,size.height,null);
        setOpaque(false);
        super.paint(g);
    }

}
