package frames;

import Alerts.Alert_newacc;
import Alerts.Alert_succes;
import dataBase.ConnectionMySql;
import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NuevaCuenta extends JFrame {

    //declare
    private final ConnectionMySql sql = new ConnectionMySql();
    private final Connection conn = sql.connectionMySql();

    private JComboBox<String> comboBox;

    private JTextField textField_cantidad;

    private String user;
    private String password;
    private int x,y;

    NuevaCuenta(String user, String password){

        this.user = user;
        this.password = password;

        config();
        startLayout();

    }

    void config(){
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300,250);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
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

        //////////////////////////////////////////////////////////////////////////////////////////


        comboBox = new JComboBox<String>();
        comboBox.setFont(StyleManager.font.deriveFont(Font.BOLD,20));
        comboBox.setBounds(30,50,panel.getWidth()-60,30);
        comboBox.setForeground(StyleManager.spaceGray);
        comboBox.setBackground(StyleManager.darkGray);
        comboBox.addItem("Ahorro");
        comboBox.addItem("Credito");

        textField_cantidad = new JTextField();
        textField_cantidad.setBounds(30,comboBox.getY()+comboBox.getHeight()*2,panel.getWidth()-60,30);
        textField_cantidad.setBackground(StyleManager.darkGray);
        textField_cantidad.setFont(StyleManager.font.deriveFont(Font.BOLD,20));
        textField_cantidad.setForeground(StyleManager.spaceGray);
        textField_cantidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_cantidad.setText("");
            }
        });

        panel.add(comboBox);
        panel.add(textField_cantidad);

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
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAccount();
                } catch (SQLException ex) {
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

        panel.add(button_ok);
        validate();
        ////////////////////////////////////////////////////////////////////////////////////////
        JButton button_cancel = new JButton();
        button_cancel.setBorder(null);
        button_cancel.setBackground(Color.BLACK);
        button_cancel.setText("Cancel");
        button_cancel.setFocusable(false);
        button_cancel.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_cancel.setForeground(Color.white);
        button_cancel.setBounds(panel.getWidth()/4 - 15,panel.getHeight()-50,70,30);
        button_cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        button_cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_cancel.setBackground(StyleManager.getButtons());
                button_cancel.setBounds(panel.getWidth()/4 - 15,panel.getHeight()-50,73,33);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_cancel.setBackground(Color.BLACK);
                button_cancel.setBounds(panel.getWidth()/4 - 15,panel.getHeight()-50,70,30);
            }
        });
        panel.add(button_cancel);


        //////////////////////////////////////////////////////////////////////////////////////

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

        this.setLocation(x-this.x,y-this.y);
    }

    private void close(){
        dispose();
    }


    private void   updateAccount() throws SQLException {

        if (user == null || password == null){
            user = "w";
            password = "w";
        }

        String cantidad = textField_cantidad.getText();
        String tipo = (String) comboBox.getSelectedItem();

        if (textField_cantidad.getText().equals("")){
            Alert_newacc alert_newacc = new Alert_newacc();
            alert_newacc.setVisible(true);
        }else {
            String getid = "select id from usuarios where nombre = '"+user+"'";
            PreparedStatement statement = conn.prepareStatement(getid);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");


                System.out.println(user);
                System.out.println(id);
                String insertcuenta = "call sp_insertar_cuenta(?,?,?)";


                PreparedStatement insert_cuenta = conn.prepareStatement(insertcuenta);
                insert_cuenta.setDouble(1, Double.parseDouble(cantidad));
                insert_cuenta.setString(2, id);
                if(comboBox.getSelectedItem().equals("Ahorro")){
                    insert_cuenta.setString(3, "Ahorro");
                }else {
                    insert_cuenta.setString(3, "Credito");
                }
                insert_cuenta.executeQuery();

                Alert_newacc alert_newacc = new Alert_newacc();
                alert_newacc.setVisible(true);
                this.dispose();
            }
        }
    }
}