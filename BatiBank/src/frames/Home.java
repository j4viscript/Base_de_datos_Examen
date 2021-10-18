package frames;

import dataBase.ConnectionMySql;
import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class Home extends JFrame{

    //declare

    private int x, y;
    private JPanelConFondo panel_frame;

    private final ConnectionMySql sql = new ConnectionMySql();
    private final Connection conn = sql.connectionMySql();
    private final String _user;
    private final String _password;
    private int cantCuentas = 0;

    Home(String user, String password, Point p){
        this._user = user;
        this._password = password;
        config();
        startLayout();
        this.setLocation(p);
    }

    void config(){
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 582);
        this.setVisible(true);
        this.setBackground(StyleManager.noColor);
        setFrameIcon();
    }

    void startLayout(){

        JLabel icon = new JLabel( );
        icon.setIcon( new ImageIcon( "src/images/batman_icon.png" ) );
        icon.setBounds( 5 , 5 , 25 , 25 );

        panel_frame = new JPanelConFondo("src/images/wallpaper_home.png");
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

        JLabel info_icon = new JLabel();
        info_icon.setIcon(new ImageIcon("src/images/info_icon_1.png"));
        info_icon.setBounds(this.getWidth()-30, this.getHeight()-30, 30, 30);
        info_icon.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                info_icon.setIcon(new ImageIcon("src/images/info_icon_2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e){
                info_icon.setIcon(new ImageIcon("src/images/info_icon_1.png"));
            }

            @Override
            public void mousePressed(MouseEvent e){
            }
        });

        panel_frame.add(close_icon);
        panel_frame.add(info_icon);
        panel_frame.add(icon);

        //////////////////////////////////////////////////////////////////////////////////

        JLabel label_bienvenido = new JLabel();
        label_bienvenido.setText("Bienvenido");
        label_bienvenido.setForeground(Color.white);
        label_bienvenido.setBounds(45, 10, 150, 20);
        label_bienvenido.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));

        JLabel label_Usuario = new JLabel();
        label_Usuario.setForeground(Color.white);
        label_Usuario.setText(_user.toUpperCase(Locale.ROOT));
        label_Usuario.setBounds(40, 15, 700, 100);
        label_Usuario.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 80));

        panel_frame.add(label_Usuario);
        panel_frame.add(label_bienvenido);

        //////////////////////////////////////////////////////////////////////////////////

        try{
            mostrarCuentas();
        }catch(SQLException e){
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////////////////

        JPanel buttons = new JPanel(new GridLayout(1, 1, 70, 50));
        buttons.setBackground(StyleManager.noColor);
        buttons.setBounds(45, 115, this.getWidth()-80, 50);

        //////////////////////////////////////////////////////////////////////////////////

        JButton button_regresar = new JButton();
        button_regresar.setLayout(new FlowLayout());
        button_regresar.setBackground(Color.BLACK);
        button_regresar.setBorder(null);
        button_regresar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                closeOperation();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button_regresar.setBackground(StyleManager.getButtons());

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_regresar.setBackground(Color.BLACK);

            }
        });

        JButton button_nueva = new JButton();
        button_nueva.setLayout(new FlowLayout());
        button_nueva.setBackground(Color.BLACK);
        button_nueva.setBorder(null);
        button_nueva.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                NuevaCuenta nuevaCuenta = new NuevaCuenta(_user, _password);
                nuevaCuenta.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button_nueva.setBackground(StyleManager.getButtons());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_nueva.setBackground(Color.BLACK);

            }
        });

        JButton button_enviar = new JButton();
        button_enviar.setLayout(new FlowLayout());
        button_enviar.setBackground(Color.BLACK);
        button_enviar.setBorder(null);
        button_enviar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                EnviarDinero enviarDinero = new EnviarDinero(_user);
                enviarDinero.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button_enviar.setBackground(StyleManager.getButtons());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_enviar.setBackground(Color.BLACK);

            }
        });

        JButton button_historial = new JButton();
        button_historial.setLayout(new FlowLayout());
        button_historial.setBorder(null);
        button_historial.setBackground(Color.BLACK);
        button_historial.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                Historial historial = new Historial(_user);
                historial.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button_historial.setBackground(StyleManager.getButtons());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_historial.setBackground(Color.BLACK);

            }
        });

        JButton button_actualizar = new JButton();
        button_actualizar.setLayout(new FlowLayout());
        button_actualizar.setBackground(Color.BLACK);
        button_actualizar.setBorder(null);
        button_actualizar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                update();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button_actualizar.setBackground(StyleManager.getButtons());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_actualizar.setBackground(Color.BLACK);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////

        JLabel back_icon = new JLabel();
        back_icon.setIcon(new ImageIcon("src/images/back_icon.png"));
        back_icon.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        button_regresar.add(back_icon);

        JLabel add_icon = new JLabel();
        add_icon.setIcon(new ImageIcon("src/images/add_icon.png"));
        add_icon.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        button_nueva.add(add_icon);

        JLabel send_icon = new JLabel();
        send_icon.setIcon(new ImageIcon("src/images/money_icon.png"));
        send_icon.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        button_enviar.add(send_icon);

        JLabel moves = new JLabel();
        moves.setIcon(new ImageIcon("src/images/last_move_icon.png"));
        moves.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        button_historial.add(moves);

        JLabel update = new JLabel();
        update.setIcon(new ImageIcon("src/images/update_icon.png"));
        update.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        button_actualizar.add(update);


        //////////////////////////////////////////////////////////////////////////////////

        buttons.add(button_regresar);
        buttons.add(button_nueva);
        buttons.add(button_enviar);
        buttons.add(button_historial);
        buttons.add(button_actualizar);

        panel_frame.add(buttons);

        //////////////////////////////////////////////////////////////////////////////////
        repaint();
        validate();
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

    private void label_1MousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    private void label_1MouseDragged(MouseEvent e){
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        this.setLocation(x-this.x, y-this.y);
    }

    private void update(){
        Home h = new Home(_user, _password, this.getLocation());
        h.setVisible(true);
        this.dispose();
    }

    private void closeOperation(){
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }

    private void changeImage(JPanelConFondo panelConFondo){

        JButton button_Image_1 = new JButton();
        button_Image_1.setBackground(Color.BLACK);
        JButton button_Image_2 = new JButton();
        button_Image_2.setBackground(StyleManager.getButtons());
        panelConFondo.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelConFondo.add(button_Image_2);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e){
                panelConFondo.add(button_Image_1);
                repaint();
            }
        });
    }

    private void getcantcuentas() throws SQLException{
        String getid = "select id from usuarios where nombre = '"+_user+"'";
        PreparedStatement statement = conn.prepareStatement(getid);
        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            String id = rs.getString("id");
            String getcantCuentas = "select count(id) from cuentas where cliente = '"+id+"'";
            PreparedStatement obtener_cantCuentas = conn.prepareStatement(getcantCuentas);
            ResultSet rst = obtener_cantCuentas.executeQuery();

            if(rst.next()) {
                cantCuentas = rst.getInt("count(id)");
            }
        }
    }

    private void mostrarCuentas() throws SQLException{
        try{
            getcantcuentas();
        }catch(SQLException e){
            e.printStackTrace();
        }

        String getid = "select id from usuarios where nombre = '"+_user+"'";
        PreparedStatement statement = conn.prepareStatement(getid);
        ResultSet rs = statement.executeQuery();

        JPanel panel_cuentas = new JPanel(new GridLayout(2, 2, 15, 15));
        panel_cuentas.setBackground(StyleManager.noColor);
        panel_cuentas.setBounds(45, 180, panel_frame.getWidth()-90, 380);

        rs.next();

        String id = rs.getString("id");
        String getids = "select * from cuentas where cliente = '"+id+"' order by id";

        PreparedStatement obtener_ids_1 = conn.prepareStatement(getids);

        ResultSet rst = obtener_ids_1.executeQuery();
        rst.next();

        for (int i = 0; i < cantCuentas; ++i){
            JPanelConFondo panel = new JPanelConFondo("src/images/background.png");
            panel.setBackground(Color.green);
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
            JLabel tipo = new JLabel();

            if(rst.getString("tipo").equals("Ahorro")) {
                tipo.setText("AHO");
            }else {
                tipo.setText("CRE");
            }
            tipo.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));
            tipo.setForeground(StyleManager.spaceGray);

            JLabel id_cuenta = new JLabel();
            id_cuenta.setText("id = "+rst.getString("id"));
            id_cuenta.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));
            id_cuenta.setBorder(new EmptyBorder(100, 0, 0, 0));
            id_cuenta.setForeground(StyleManager.spaceGray);

            JLabel cant = new JLabel();
            cant.setText("$ "+rst.getString("cantidad"));
            cant.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));
            cant.setBorder(new EmptyBorder(100, 0, 0, 0));
            cant.setForeground(StyleManager.spaceGray);
            rst.next();


            panel.add(tipo, BorderLayout.NORTH);
            panel.add(id_cuenta,BorderLayout.WEST);
            panel.add(cant, BorderLayout.EAST);

            panel_cuentas.add(panel);
        }

        panel_frame.add(panel_cuentas);
    }
}
