package frames;

import Alerts.Alert_error;
import Alerts.Alert_succes;
import dataBase.ConnectionMySql;
import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;

public class EnviarDinero extends JFrame {

    public static void main ( String[] args ) {
        EnviarDinero enviarDinero = new EnviarDinero ("w");
        enviarDinero.setVisible ( true );
    }


    //declare
    private final ConnectionMySql sql = new ConnectionMySql();
    private final Connection conn = sql.connectionMySql();
    private JTextField textField_cantidad,textField_id_cuenta_destinatario,
            textField_id_cuenta_remitente, textField_description;
    private int x,y;
    private final String user;

    EnviarDinero(String user){
        this.user = user;
        config();
        startLayout();
    }

    void config(){
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color (0,0,0,0));
        setFrameIcon();

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

    void startLayout() {

        JPanelConFondo panel = new JPanelConFondo ( "src/images/background_2.png" );
        panel.setLayout ( null );
        panel.setBounds ( 0 , 0 , this.getWidth ( ) , this.getHeight ( ) );
        this.add ( panel );

        panel.addMouseListener ( new MouseAdapter ( ) {
            @Override
            public void mousePressed ( MouseEvent e ) {
                label_1MousePressed ( e );
            }
        } );
        panel.addMouseMotionListener ( new MouseAdapter ( ) {
            @Override
            public void mouseDragged ( MouseEvent e ) {
                label_1MouseDragged ( e );
            }
        } );

        //////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////////////////////

        textField_id_cuenta_destinatario = new JTextField("DESTINATARIO CUENTA ID");
        textField_id_cuenta_destinatario.setBounds(30,14 * 2 + 10,panel.getWidth()-60,30);
        textField_id_cuenta_destinatario.setBackground( StyleManager.darkGray);
        textField_id_cuenta_destinatario.setFont(StyleManager.font.deriveFont(Font.BOLD,15));
        textField_id_cuenta_destinatario.setForeground(StyleManager.spaceGray);
        textField_id_cuenta_destinatario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_id_cuenta_destinatario.setText("");
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////

        textField_id_cuenta_remitente = new JTextField("REMITENTE CUENTA ID");
        textField_id_cuenta_remitente.setBounds(30,textField_id_cuenta_destinatario.getY () * 2 + 10,panel.getWidth()-60,30);
        textField_id_cuenta_remitente.setBackground( StyleManager.darkGray);
        textField_id_cuenta_remitente.setFont(StyleManager.font.deriveFont(Font.BOLD,15));
        textField_id_cuenta_remitente.setForeground(StyleManager.spaceGray);
        textField_id_cuenta_remitente.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_id_cuenta_remitente.setText("");
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////

        textField_cantidad = new JTextField("CANTIDAD");
        textField_cantidad.setBounds(30,textField_id_cuenta_destinatario.getY () * 3 + 20,panel.getWidth()-60,30);
        textField_cantidad.setBackground( StyleManager.darkGray);
        textField_cantidad.setFont(StyleManager.font.deriveFont(Font.BOLD,15));
        textField_cantidad.setForeground(StyleManager.spaceGray);
        textField_cantidad.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_cantidad.setText("");
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////

        textField_description = new JTextField("DESCRIPCION");
        textField_description.setBounds(30,textField_id_cuenta_destinatario.getY () * 4 + 30,panel.getWidth()-60,30);
        textField_description.setBackground( StyleManager.darkGray);
        textField_description.setFont(StyleManager.font.deriveFont(Font.BOLD,15));
        textField_description.setForeground(StyleManager.spaceGray);
        textField_description.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_description.setText("");
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////

        JButton button_ok = new JButton();
        button_ok.setText("Accept");
        button_ok.setForeground(Color.WHITE);
        button_ok.setBackground(Color.BLACK);
        button_ok.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_ok.setFocusable(false);
        button_ok.setBorder(null);
        button_ok.setBounds(panel.getWidth()/2 + 15,panel.getHeight()-50,70,30);
        button_ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed( ActionEvent e) {

                try{
                    if(sendMoney(textField_id_cuenta_destinatario.getText(),textField_id_cuenta_remitente.getText(),textField_cantidad.getText())){
                        close();
                        Alert_succes alert_succes = new Alert_succes();
                        alert_succes.setVisible(true);

                    }else {
                        Alert_error alert_error = new Alert_error();
                        alert_error.setVisible(true);
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        button_ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_ok.setBackground(StyleManager.getButtons());
                button_ok.setBounds(panel.getWidth()/2 + 15,panel.getHeight()-50,73,33);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_ok.setBackground(Color.BLACK);
                button_ok.setBounds(panel.getWidth()/2 + 15,panel.getHeight()-50,70,30);


            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////

        JButton button_cancel = new JButton();
        button_cancel.setBorder(null);
        button_cancel.setBackground(Color.BLACK);
        button_cancel.setText("Cancel");
        button_cancel.setFocusable(false);
        button_cancel.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_cancel.setForeground(Color.white);
        button_cancel.setBounds(panel.getWidth()/4 - 15, panel.getHeight() -50,70,30);
        button_cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed( ActionEvent e) {
                close();

            }
        });
        button_cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_cancel.setBackground(StyleManager.getButtons());
                button_cancel.setBounds(panel.getWidth()/4 - 15, panel.getHeight() -52,73,33);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_cancel.setBounds(panel.getWidth()/4 - 15, panel.getHeight() -50,70,30);
                button_cancel.setBackground(Color.BLACK);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////

        panel.add ( textField_id_cuenta_destinatario );
        panel.add ( textField_id_cuenta_remitente );
        panel.add( textField_description );
        panel.add ( textField_cantidad );
        panel.add(button_cancel);
        panel.add(button_ok);
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

    void close(){
        this.dispose();
    }

    boolean sendMoney(String id_cuenta_destinatario,String id_cuenta_remitente, String cantidad) throws SQLException {

        String info = "select * from usuarios where nombre = '"+user+"'";
        PreparedStatement statement1 = conn.prepareStatement(info);
        ResultSet rss = statement1.executeQuery();
        rss.next();

        String id = rss.getString("id");

        boolean close = false;
        
        if(!id_cuenta_destinatario.equals("DESTINATARIO CUENTA ID") || !id_cuenta_remitente.equals("REMITENTE CUENTA ID") || !cantidad.equals("CANTIDAD")){
            String getcantremitente = "select cantidad from cuentas where id = '"+id_cuenta_remitente+"'";
            PreparedStatement statement = conn.prepareStatement(getcantremitente);
            ResultSet rs = statement.executeQuery();

            String getcantdestinatario = "select cantidad from cuentas where id = '"+id_cuenta_destinatario+"'";
            PreparedStatement statement2 = conn.prepareStatement(getcantdestinatario);
            ResultSet rs2 = statement2.executeQuery();

            if(rs.next() && rs2.next()) {
                close = true;
            }

            double cantidad_rem, cantidad_des;

            cantidad_rem = rs.getDouble("cantidad");
            cantidad_des = rs2.getDouble("cantidad");

            cantidad_rem -= Double.parseDouble(cantidad);
            cantidad_des += Double.parseDouble(cantidad);

            PreparedStatement statement3 = conn.prepareStatement("call sp_update_cuenta(?,?)");
            statement3.setDouble(1, cantidad_rem);
            statement3.setString(2, id_cuenta_remitente);
            PreparedStatement statement4 = conn.prepareStatement("call sp_update_cuenta(?,?)");
            statement4.setDouble(1, cantidad_des);
            statement4.setString(2, id_cuenta_destinatario);

            statement3.execute();
            statement4.execute();

            LocalDate date = LocalDate.now();

            PreparedStatement statement5 = conn.prepareStatement("call sp_insertar_historial('"+id_cuenta_remitente+
                    "','"+id_cuenta_destinatario+"',"+cantidad+",'"+date+"','"+textField_description.getText().toUpperCase()+"','"+id+"');");
            statement5.execute();
        }

        return close;
    }
}
