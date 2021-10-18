package frames;

import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Notify extends JFrame {

    public static void main(String[] args) {
        Notify notify = new Notify();
        notify.setVisible(true);
    }
    //declare

    private int x,y;
    public static String text = "Creador : Romel Elic Gamallo Peralta";
    private JPanelConFondo panel_frame;

    Notify(){
        config();
        startLayout();
    }

    void config(){
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300,150);
        this.setBackground(new Color(0,0,0,0));
        setFrameIcon();
        setScreenCenter();
    }

    void startLayout(){

        JPanelConFondo panel = new JPanelConFondo("src/images/background_2.png");
        panel.setLayout(null);
        panel.setBounds(0,0,this.getWidth(),this.getHeight());
        this.add(panel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label_1MousePressed(e);
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                label_1MouseDragged(e);
            }
        });


        JLabel label = new JLabel();
        label.setText(text);
        label.setFont(StyleManager.getfont().deriveFont(Font.ITALIC,15));
        label.setForeground(StyleManager.spaceGray);
        label.setBounds(25,40,250,20);
        panel.add(label);


        JButton button_ok = new JButton("");
        button_ok.setIcon(new ImageIcon("src/images/button_ok.png"));
        button_ok.setBorder(null);
        button_ok.setBackground(new Color(0,0,0,0));
        button_ok.setBounds(panel.getWidth()/2 - 30,panel.getHeight()-50,60,26);
        button_ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        button_ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_ok.setIcon(new ImageIcon("src/images/button_ok_2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_ok.setIcon(new ImageIcon("src/images/button_ok.png"));
            }
        });

        JLabel label_ok = new JLabel("");
        label_ok.setText("    OK");
        label_ok.setForeground(StyleManager.whiteGray);
        label_ok.setFont(StyleManager.getfont().deriveFont(Font.BOLD,15));
        button_ok.add(label_ok);

        panel.add(button_ok);

        JLabel close_icon = new JLabel( );
        close_icon.setIcon( new ImageIcon( "src/images/exit_icon.png" ) );
        close_icon.setBounds( this.getWidth( ) - 35 , 5 , 30 , 30 );
        close_icon.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                closeOperation( );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                close_icon.setIcon( new ImageIcon( "src/images/exit_icon.png" ) );

            }

            @Override
            public void mouseExited( MouseEvent e ) {
                close_icon.setIcon( new ImageIcon( "src/images/exit_icon.png" ) );
            }
        } );
        close_icon.setBorder( null );
        close_icon.setBackground( new Color( 0 , 0 , 0 , 0 ) );
        panel_frame.add( close_icon );

    }

    void setFrameIcon(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Image myIcon = tk.getImage("src/images/batman_icon.png");
            setIconImage(myIcon);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        repaint();
    }
    void closeOperation(){
        this.dispose();
    }

    private void setScreenCenter(){

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height/2;
        int width = pantalla.width/2;

        this.setLocation(width-(this.getWidth()/2),height-(this.getHeight()/2));
    }

    private void label_1MousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    private void label_1MouseDragged(MouseEvent e){
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        this.setLocation(x-this.x,y-this.y);
    }

    private void close(){
        dispose();
    }
}
