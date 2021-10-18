package frames;

import dataBase.ConnectionMySql;
import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Historial extends JFrame{

    public static void main(String[] args){
        Historial h = new Historial("w");
        h.setVisible(true);
    }

    //declare
    private final ConnectionMySql sql = new ConnectionMySql();
    private final Connection conn = sql.connectionMySql();

    private int x, y;
    private final String user;
    private JPanel panel_historial;

    Historial(String user){
        this.user = user;
        config();
        startLayout();
    }

    void config(){
        this.setUndecorated(true);
        this.setLayout(null);
        this.setSize(600, 582);
        this.setVisible(true);
        this.setBackground(StyleManager.noColor);
        this.setLocationRelativeTo(null);
        setFrameIcon();
    }

    void startLayout(){

        JLabel icon = new JLabel( );
        icon.setIcon( new ImageIcon( "src/images/batman_icon.png" ) );
        icon.setBounds( 5 , 5 , 30 , 30 );

        JPanelConFondo panel_frame = new JPanelConFondo("src/images/wallpaper_home.png");
        panel_frame.setLayout(null);
        panel_frame.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(panel_frame);

        panel_frame.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                label_1MousePressed(e);
            }
        });
        panel_frame.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                label_1MouseDragged(e);
            }
        });

        JLabel close_icon = new JLabel();
        close_icon.setIcon(new ImageIcon("src/images/exit_icon.png"));
        close_icon.setBounds(this.getWidth()-35, 5, 30, 30);
        close_icon.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                closeOperation();
            }

            @Override
            public void mouseEntered(MouseEvent e){
                close_icon.setIcon(new ImageIcon("src/images/exit_icon_2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e){
                close_icon.setIcon(new ImageIcon("src/images/exit_icon.png"));
            }
        });
        close_icon.setBorder(null);
        close_icon.setBackground(new Color(0, 0, 0, 0));
        panel_frame.add(close_icon);
        panel_frame.add(icon);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel label_Usuario = new JLabel();
        label_Usuario.setForeground(Color.white);
        label_Usuario.setText("HISTORIAL");
        label_Usuario.setBounds(40, 15, this.getWidth()-40, 100);
        label_Usuario.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 60));

        panel_frame.add(label_Usuario);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        panel_historial = new JPanel(new GridLayout(3, 3, 5, 5));
        panel_historial.setBounds(40,120,this.getWidth()-80,this.getHeight()-150);
        panel_historial.setBackground(StyleManager.noColor);

        panel_frame.add(panel_historial);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try{
            mostartHistorial();
        }catch(SQLException e){
            e.printStackTrace();
        }


        repaint();
        validate();
    }

    private void label_1MousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    private void label_1MouseDragged(MouseEvent e){
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        this.setLocation(x-this.x, y-this.y);
    }


    void setFrameIcon(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        try{
            Image myIcon = tk.getImage("src/images/batman_icon.png");
            setIconImage(myIcon);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        repaint();
    }

    private void closeOperation(){
        this.dispose();
    }

    private void mostartHistorial() throws SQLException{

        String info = "select * from usuarios where nombre = '"+user+"'";
        PreparedStatement statement = conn.prepareStatement(info);
        ResultSet rs = statement.executeQuery();
        rs.next();

        String id = rs.getString("id");

        PreparedStatement statement1 = conn.prepareStatement("select count(id) from cuentas where cliente = '"+id+"'");
        ResultSet rs_2 = statement1.executeQuery();
        rs_2.next();

        PreparedStatement statement2 = conn.prepareStatement("select * from movimientos where cliente = '"+id+"'");
        ResultSet rs_3 = statement2.executeQuery();

        for(int i = 0; i < rs_2.getInt("count(id)"); ++i){

            if(rs_3.next()) {

                JPanelConFondo panel = new JPanelConFondo("src/images/background.png");
                panel.setBackground(Color.green);
                panel.setLayout(new GridLayout(3, 3, 5, 5));
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                panel.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        panel.setImagen("src/images/background_2.png");
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e){
                        panel.setImagen("src/images/background.png");
                        repaint();
                    }
                });

                JLabel id_destinatario = new JLabel();
                id_destinatario.setText("id Des = "+rs_3.getString("id_recibe"));
                id_destinatario.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                id_destinatario.setForeground(StyleManager.spaceGray);
                panel.add(id_destinatario);

                JLabel id_remitente = new JLabel();
                id_remitente.setText("id Rem = "+rs_3.getString("id_envio"));
                id_remitente.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                id_remitente.setBounds(10, 5, panel.getWidth()-10, 15);
                id_remitente.setForeground(StyleManager.spaceGray);
                panel.add(id_remitente);

                JLabel fecha = new JLabel();
                fecha.setText(""+rs_3.getDate("fecha"));
                fecha.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                fecha.setForeground(StyleManager.spaceGray);
                panel.add(fecha);

                JLabel cantidad = new JLabel();
                cantidad.setText("$ "+rs_3.getString("cantidad"));
                cantidad.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                cantidad.setForeground(StyleManager.spaceGray);
                panel.add(cantidad);

                JLabel descrition = new JLabel();
                descrition.setText("'"+rs_3.getString("descripcion")+"'");
                descrition.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                descrition.setForeground(StyleManager.spaceGray);
                panel.add(descrition);

                JLabel cliente = new JLabel();
                cliente.setText("cliente = '"+rs_3.getString("cliente")+"'");
                cliente.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 15));
                cliente.setForeground(StyleManager.spaceGray);
                panel.add(cliente);

                repaint();
                validate();
                this.panel_historial.add(panel);
            }
        }
    }
}
