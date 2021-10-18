package Alerts;

import design.StyleManager;
import frames.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register_alert extends JFrame {

    //wrong email or password,
    public Register_alert() {
        start();
    }

    public Register_alert(Point p) {
        start();
        this.setLocation(p);
    }

    void start() {
        config_win();
        startWinLayout();
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
    }

    void config_win() {
        setSize(400,250);
        setTitle("BatBank");
        setLayout(null);
        setResizable(false);
        setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFrameIcon();
        getContentPane().setBackground(Color.gray);

    }
    void startWinLayout(){

        JLabel error = new JLabel();
        error.setText("Information incorrect, Please Check");
        error.setForeground(new Color(54, 53, 50));
        error.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));
        error.setBorder(null);
        error.setBounds(this.getWidth()/8 -40 , 50, 400,50);
        this.add(error);

        JButton back = new JButton();
        back.setText("OK");
        back.setFont(StyleManager.getfont().deriveFont(Font.BOLD, 20));
        back.setBounds( 140,150,140,30);
        back.setBackground(Color.BLACK);
        back.setFocusable(false);
        back.setBorder(null);
        back.addActionListener(e-> backregister());
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(StyleManager.getButtons());
                back.setBounds(140,150,143,33);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setBackground(Color.BLACK);
                back.setBounds(140,150,140,30);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Register_alert.this.dispose();
            }
        });
        this.add(back);


        repaint();
        validate();
    }
    void backregister (){
        Login rg = new Login();
        this.getLocation();
    }

}

