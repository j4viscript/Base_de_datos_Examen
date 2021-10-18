package frames;

import Alerts.Login_alert;
import Alerts.Register_alert;
import Alerts.User_Register;
import dataBase.ConnectionMySql;
import design.JPanelConFondo;
import design.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Login extends JFrame {

    public static void main( String[] args ) {
        Login login = new Login( );
        login.setVisible( true );
    }

    public Login() {

        config( );
        startLayout( );
    }


    //declare

    private int x, y;

    private JPanelConFondo panel_frame;
    private JPanel panel_login, panel_register;

    private boolean eye = false;

    public static JTextField textField_Email;
    public static JPasswordField passwordField;

    public JTextField textField_nombre_registro, textField_apellido_1_registro, textField_apellido_2_registro,    textField_email_registro;
    public JPasswordField passwordField_registro;

    private final ConnectionMySql sql = new ConnectionMySql( );
    private final Connection conn = sql.connectionMySql( );


    //

    void config( ) {

        this.setUndecorated( true );
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        this.setLayout( null );
        this.setSize( 400 , 460 );
        this.setVisible( true );
        this.setBackground( new Color( 0 , 0 , 0 , 0 ) );
        setFrameIcon( );
        setScreenCenter( );
    }

    void startLayout( ) {

        ///
        JLabel img_bat = new JLabel();
        img_bat.setIcon(new ImageIcon("src/images/batsi_icon.png"));
        img_bat.setBounds(145,130,120,120);
        add(img_bat);

        panel_frame = new JPanelConFondo( "src/images/wallpaper_home.png" );
        panel_frame.setLayout( null );
        panel_frame.setBounds( 0 , 0 , this.getWidth( ) , this.getHeight( ) );
        this.add( panel_frame );

        panel_frame.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                label_1MousePressed( e );
            }
        } );
        panel_frame.addMouseMotionListener( new MouseAdapter( ) {
            @Override
            public void mouseDragged( MouseEvent e ) {
                label_1MouseDragged( e );
            }
        } );

        ///
        ///

        JLabel icon = new JLabel( );
        icon.setIcon( new ImageIcon( "src/images/batman_icon.png" ) );
        icon.setBounds( 5 , 5 , 50, 30 );
        panel_frame.add( icon );


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


        ///jpanel

        panel_login = new JPanel( null );
        panel_login.setBounds( 60 , 240 , 300 , 230 );
        panel_login.setBackground( new Color( 0 , 0 , 0 , 0 ) );

        panel_frame.add( panel_login );


        ImageIcon image_1 = new ImageIcon( "src/images/textfield_1.png" );
        ImageIcon image_2 = new ImageIcon( "src/images/textfield_1.png" );

        JLabel label_textfield_1 = new JLabel( );
        label_textfield_1.setIcon( image_1 );
        label_textfield_1.setBounds( 23 , 20 , image_1.getIconWidth( ) , image_1.getIconHeight( ) );
        label_textfield_1.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_1.setIcon( new ImageIcon( "src/images/textfield_2.png" ) );
                textField_Email.setBackground( new Color( 200 , 200 , 200 ) );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_textfield_1.setIcon( new ImageIcon( "src/images/textfield_1.png" ) );
                textField_Email.setBackground( new Color( 220 , 220 , 220 ) );
            }
        } );

        JLabel label_textfield_2 = new JLabel( );
        label_textfield_2.setIcon( image_2 );
        label_textfield_2.setBounds( 23 , 80 , image_1.getIconWidth( ) , image_1.getIconHeight( ) );
        label_textfield_2.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_2.setIcon( new ImageIcon( "src/images/textfield_2.png" ) );
                passwordField.setBackground( new Color( 200 , 200 , 200 ) );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_textfield_2.setIcon( new ImageIcon( "src/images/textfield_1.png" ) );
                passwordField.setBackground( new Color( 220 , 220 , 220 ) );
            }
        } );
        panel_login.add( label_textfield_1 );
        panel_login.add( label_textfield_2 );
        ///////////////

        JButton button_login = new JButton();
        button_login.setBorder( null );
        button_login.setText("Log in");
        button_login.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_login.setForeground(Color.white);
        button_login.setBackground(Color.BLACK);
        button_login.setBounds( 23 , 149 ,125,40);
        button_login.setBorder(null);
        button_login.setFocusable(false);
        button_login.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                button_login.setBackground(StyleManager.getButtons());
                button_login.setBounds( 23 , 149 ,130,45);
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                button_login.setBounds( 23 , 149 ,125,40);
                button_login.setBackground(Color.BLACK);
                repaint( );
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                try {
                    login( textField_Email.getText( ) , String.valueOf( passwordField.getPassword( ) ) );
                } catch( SQLException ex ) {
                    ex.printStackTrace( );
                }
            }
        } );

        panel_login.add( button_login , FlowLayout.CENTER );

        ///

        JButton button_register = new JButton();
        button_register.setBorder( null );
        button_register.setText("Register");
        button_register.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_register.setForeground(Color.white);
        button_register.setBackground(Color.BLACK);
        button_register.setBounds( 170 , 149 ,125,40);
        button_register.setBorder(null);
        button_register.setFocusable(false);
        button_register.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                button_register.setBackground(StyleManager.getButtons());
                button_register.setBounds( 170 , 149 , 130,45 );
                repaint();
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                button_register.setBackground(Color.BLACK);
                button_register.setBounds( 170 , 149 ,125,40);
                repaint();
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                panel_login.setVisible( false );
                try {
                    startRegister( panel_frame );
                } catch( Exception ex ) {
                    ex.printStackTrace( );
                }
                repaint( );

                //imagen batman movimiento.
                img_bat.setVisible(false);

            }
        } );

        ///


        ///

        JLabel email_icon = new JLabel( );
        email_icon.setIcon( new ImageIcon( "src/images/user_icon.png" ) );
        email_icon.setBounds( 1 , 3 , 30 , 30 );

        JLabel password_icon = new JLabel( );
        password_icon.setIcon( new ImageIcon( "src/images/password_icon.png" ) );
        password_icon.setBounds( 1 , 3 , 30 , 30 );

        JLabel eye_icon = new JLabel( );
        eye_icon.setIcon( new ImageIcon( "src/images/eye_2.png" ) );
        eye_icon.setBounds( label_textfield_1.getWidth( ) - 35 , 3 , 30 , 30 );
        eye_icon.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                activeEye( eye_icon , new ImageIcon( "src/images/eye_1.png" ) , new ImageIcon( "src/images/eye_2.png" ) , passwordField );
            }
        } );


        label_textfield_1.add( email_icon );
        label_textfield_2.add( password_icon );
        label_textfield_2.add( eye_icon );

        ///


        ///

        JLabel label_or = new JLabel( );
        label_or.setText( "OR" );
        label_or.setForeground( new Color( 60 , 60 , 60 ) );
        label_or.setBounds( 143 , 153 , 30 , 30 );
        label_or.setFont( StyleManager.getfont( ) );
        panel_login.add( label_or );
        panel_login.add( button_register );

        ///


        ///

        textField_Email = new JTextField( "Name" );
        textField_Email.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        textField_Email.setBounds( 35 , 2 , label_textfield_1.getWidth( ) - 45 , 30 );
        textField_Email.setBorder( null );
        textField_Email.setForeground( new Color( 40 , 40 , 40 ) );
        textField_Email.setBackground( StyleManager.whiteGray );
        textField_Email.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                textField_Email.setBackground( StyleManager.darkGray );
                label_textfield_1.setIcon( new ImageIcon( "src/images/textfield_2.png" ) );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_Email.setBackground( StyleManager.whiteGray );
                label_textfield_1.setIcon( new ImageIcon( "src/images/textfield_1.png" ) );
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                textField_Email.setText( "" );
            }
        } );


        passwordField = new JPasswordField( "password" );
        passwordField.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        passwordField.setBounds( 35 , 2 , label_textfield_1.getWidth( ) - 75 , 30 );
        passwordField.setBorder( null );
        passwordField.setForeground( new Color( 40 , 40 , 40 ) );
        passwordField.setBackground( new Color( 220 , 220 , 220 ) );
        passwordField.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                passwordField.setBackground( new Color( 200 , 200 , 200 ) );
                label_textfield_2.setIcon( new ImageIcon( "src/images/textfield_2.png" ) );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                passwordField.setBackground( new Color( 220 , 220 , 220 ) );
                label_textfield_2.setIcon( new ImageIcon( "src/images/textfield_1.png" ) );
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                passwordField.setText( "" );
            }
        } );


        label_textfield_2.add( passwordField );
        label_textfield_1.add( textField_Email );

        ///
        panel_login.setVisible( true );
        repaint( );
        validate( );
    }

    void startRegister( JPanel panel_frame ) {

        panel_register = new JPanel( null );
        panel_register.setBounds( 100 , 100 , 300 , 370 );
        panel_register.setBackground( new Color( 255,255,255) );

        panel_frame.add( panel_register );


        //textfields

        //Images
        ImageIcon image_1 = new ImageIcon( "src/images/textfield_1.png" );
        ImageIcon image_2 = new ImageIcon( "src/images/textfield_2.png" );
        ImageIcon image_3 = new ImageIcon( "src/images/textfield_3.png" );
        ImageIcon image_4 = new ImageIcon( "src/images/textfield_4.png" );

        //NOMBRE USUARIO

        JLabel label_nombre = new JLabel( );
        label_nombre.setIcon( image_1 );
        label_nombre.setBounds( 23 , 25 , image_1.getIconWidth( ) , image_1.getIconHeight( ) );
        label_nombre.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_nombre.setIcon( image_2 );
                textField_nombre_registro.setBackground( StyleManager.darkGray );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_nombre.setIcon( image_1 );
                textField_nombre_registro.setBackground( StyleManager.whiteGray );
            }
        } );

        //usuario_icono
        JLabel usuario_icon = new JLabel( );
        usuario_icon.setIcon( new ImageIcon( "src/images/user_icon.png" ) );
        usuario_icon.setBounds( 5 , 5 , 30 , 30 );

        //textfield_nombre
        textField_nombre_registro = new JTextField( "name" );
        textField_nombre_registro.setBounds( 40 , 3 , 200 , 30 );
        textField_nombre_registro.setBackground( StyleManager.whiteGray );
        textField_nombre_registro.setBorder( null );
        textField_nombre_registro.setForeground( StyleManager.spaceGray );
        textField_nombre_registro.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        textField_nombre_registro.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                textField_nombre_registro.setText( "" );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                textField_nombre_registro.setBackground( StyleManager.darkGray );
                label_nombre.setIcon( image_2 );
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_nombre_registro.setBackground( StyleManager.whiteGray );
                label_nombre.setIcon( image_1 );
                repaint( );
            }
        } );

        label_nombre.add( textField_nombre_registro );
        label_nombre.add( usuario_icon );

        ///


        /// APELLIDO 1
        JLabel label_textfield_2 = new JLabel( );
        label_textfield_2.setIcon( image_3 );
        label_textfield_2.setBounds( 23 , 80 , image_1.getIconWidth( ) / 2 - 5 , image_1.getIconHeight( ) );
        label_textfield_2.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_2.setIcon( image_4 );
                textField_apellido_1_registro.setBackground( StyleManager.darkGray );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_textfield_2.setIcon( image_3 );
                textField_apellido_1_registro.setBackground( StyleManager.whiteGray );
            }
        } );

        //textfield_apellido 1
        textField_apellido_1_registro = new JTextField( "Last Name" );
        textField_apellido_1_registro.setBounds( 5 , 3 , 100 , 30 );
        textField_apellido_1_registro.setBackground( StyleManager.whiteGray );
        textField_apellido_1_registro.setBorder( null );
        textField_apellido_1_registro.setForeground( StyleManager.spaceGray );
        textField_apellido_1_registro.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        textField_apellido_1_registro.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                textField_apellido_1_registro.setText( "" );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                textField_apellido_1_registro.setBackground( StyleManager.darkGray );
                label_textfield_2.setIcon( image_4 );
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_apellido_1_registro.setBackground( StyleManager.whiteGray );
                label_textfield_2.setIcon( image_3 );
                repaint( );
            }
        } );

        label_textfield_2.add( textField_apellido_1_registro );


        /// APELLIDO 2
        JLabel label_textfield_3 = new JLabel( );
        label_textfield_3.setIcon( image_3 );
        label_textfield_3.setBounds( 155 , 80 , image_1.getIconWidth( ) / 2 - 5 , image_1.getIconHeight( ) );
        label_textfield_3.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_3.setIcon( image_4 );
                textField_apellido_2_registro.setBackground( StyleManager.darkGray );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_apellido_2_registro.setBackground( StyleManager.whiteGray );
                label_textfield_3.setIcon( image_3 );
            }
        } );
        textField_apellido_2_registro = new JTextField( "Last Name 2" );
        textField_apellido_2_registro.setBounds( 2 , 3 , 115 , 30 );
        textField_apellido_2_registro.setBackground( StyleManager.whiteGray );
        textField_apellido_2_registro.setBorder( null );
        textField_apellido_2_registro.setForeground( StyleManager.spaceGray );
        textField_apellido_2_registro.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        textField_apellido_2_registro.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                textField_apellido_2_registro.setText( "" );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                textField_apellido_2_registro.setBackground( StyleManager.darkGray );
                label_textfield_3.setIcon( image_4 );
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_apellido_2_registro.setBackground( StyleManager.whiteGray );
                label_textfield_3.setIcon( image_3 );
                repaint( );
            }
        } );

        label_textfield_3.add( textField_apellido_2_registro );


        ///


        /// CORREO

        JLabel label_textfield_4 = new JLabel( );
        label_textfield_4.setIcon( image_1 );
        label_textfield_4.setBounds( 23 , 140 , image_1.getIconWidth( ) , image_1.getIconHeight( ) );
        label_textfield_4.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_4.setIcon( image_2 );
                textField_email_registro.setBackground( new Color( 200 , 200 , 200 ) );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_textfield_4.setIcon( image_1 );
                textField_email_registro.setBackground( new Color( 220 , 220 , 220 ) );
            }
        } );

        JLabel email_icon = new JLabel( );
        email_icon.setIcon( new ImageIcon( "src/images/email_icon.png" ) );
        email_icon.setBounds( 5 , 3 , 30 , 30 );

        //textfield_EMAIL
        textField_email_registro = new JTextField( "email" );
        textField_email_registro.setBounds( 40 , 3 , 201 , 30 );
        textField_email_registro.setBackground( StyleManager.whiteGray );
        textField_email_registro.setBorder( null );
        textField_email_registro.setForeground( StyleManager.spaceGray );
        textField_email_registro.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        textField_email_registro.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                textField_email_registro.setText( "" );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                textField_email_registro.setBackground( StyleManager.darkGray );
                label_textfield_4.setIcon( image_2 );
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                textField_email_registro.setBackground( StyleManager.whiteGray );
                label_textfield_4.setIcon( image_1 );
                repaint( );
            }
        } );

        label_textfield_4.add( textField_email_registro );
        label_textfield_4.add( email_icon );


        /// Textfield CONTRASEÑAs


        JLabel label_textfield_5 = new JLabel( );
        label_textfield_5.setIcon( image_1 );
        label_textfield_5.setBounds( 23 , 200 , image_1.getIconWidth( ) , image_1.getIconHeight( ) );
        label_textfield_5.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                label_textfield_5.setIcon( image_2 );
                passwordField_registro.setBackground( StyleManager.darkGray );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                label_textfield_5.setIcon( image_1 );
                passwordField_registro.setBackground( StyleManager.whiteGray );
            }
        } );

        //usuario_icono
        JLabel password_icon = new JLabel( );
        password_icon.setIcon( new ImageIcon( "src/images/password_icon.png" ) );
        password_icon.setBounds( 1 , 3 , 30 , 30 );


        //textfield_nombre
        passwordField_registro = new JPasswordField( "password" );
        passwordField_registro.setBounds( 40 , 3 , 170 , 30 );
        passwordField_registro.setBackground( StyleManager.whiteGray );
        passwordField_registro.setBorder( null );
        passwordField_registro.setForeground( StyleManager.spaceGray );
        passwordField_registro.setFont( StyleManager.font.deriveFont( Font.PLAIN , 20 ) );
        passwordField_registro.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                passwordField_registro.setText( "" );
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
                passwordField_registro.setBackground( StyleManager.darkGray );
                label_textfield_5.setIcon( image_2 );
                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                passwordField_registro.setBackground( StyleManager.whiteGray );
                label_textfield_5.setIcon( image_1 );
                repaint( );
            }
        } );

        JLabel eye_icon = new JLabel( );
        eye_icon.setIcon( new ImageIcon( "src/images/eye_2.png" ) );
        eye_icon.setBounds( label_textfield_5.getWidth( ) - 35 , 3 , 30 , 30 );
        eye_icon.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mousePressed( MouseEvent e ) {
                activeEye( eye_icon , new ImageIcon( "src/images/eye_1.png" ) , new ImageIcon( "src/images/eye_2.png" ) , passwordField_registro );
            }
        } );

        label_textfield_5.add( passwordField_registro );
        label_textfield_5.add( password_icon );
        label_textfield_5.add( eye_icon );

        ///button - Login - Register


        JButton button_login = new JButton( );
        button_login.setBounds( 23 , 280,120,30 );
        button_login.setText("Log In");
        button_login.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_login.setForeground(Color.white);
        button_login.setBackground(Color.BLACK);
        button_login.setBorder(null);
        button_login.setFocusable(false);
        button_login.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                button_login.setBackground(StyleManager.getButtons());
                button_login.setBounds( 23 , 280,124,34);

            }

            @Override
            public void mouseExited( MouseEvent e ) {
                button_login.setBounds( 23 , 280,120,30 );
                button_login.setBackground(Color.BLACK);
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                panel_login.setVisible( true );
                panel_register.setVisible( false );
                validate( );
                repaint( );
            }
        } );

        JButton button_register = new JButton();
        button_register.setBounds( 160 , 280 ,120,30);
        button_register.setText("Register");
        button_register.setFont(StyleManager.getfont().deriveFont(Font.BOLD,20));
        button_register.setForeground(Color.white);
        button_register.setBackground(Color.BLACK);
        button_register.setBorder(null);
        button_register.setFocusable(false);
        button_register.addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseEntered( MouseEvent e ) {
                button_register.setBackground(StyleManager.getButtons());
                button_register.setBounds( 160 , 280 ,124,34);

                repaint( );
            }

            @Override
            public void mouseExited( MouseEvent e ) {
                button_register.setBackground(Color.BLACK);
                button_register.setBounds( 160 , 280 ,120,30);
                repaint( );
            }

            @Override
            public void mousePressed( MouseEvent e ) {
                try {
                    registrar( textField_nombre_registro.getText( ) , textField_apellido_1_registro.getText( ) ,
                            textField_apellido_2_registro.getText( ) , textField_email_registro.getText( ) , passwordField_registro.getText( ) );
                } catch( SQLException ex ) {
                    ex.printStackTrace( );
                }
            }
        } );

        panel_register.add( button_login );
        panel_register.add( button_register );

        ///

        panel_register.add( label_nombre );
        panel_register.add( label_textfield_2 );
        panel_register.add( label_textfield_3 );
        panel_register.add( label_textfield_4 );
        panel_register.add( label_textfield_5 );
    }

    void closeOperation( ) {
        System.exit( 0 );
    }

    private void label_1MousePressed( MouseEvent e ) {
        x = e.getX( );
        y = e.getY( );
    }

    private void label_1MouseDragged( MouseEvent e ) {
        int x = e.getXOnScreen( );
        int y = e.getYOnScreen( );

        this.setLocation( x - this.x , y - this.y );
    }

    private void setScreenCenter( ) {

        Dimension pantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int height = pantalla.height / 2;
        int width = pantalla.width / 2;

        this.setLocation( width - (this.getWidth( ) / 2) , height - (this.getHeight( ) / 2) );
    }

    void setFrameIcon( ) {
        Toolkit tk = Toolkit.getDefaultToolkit( );
        try {
            Image myIcon = tk.getImage( "src/images/batman_icon.png" );
            setIconImage( myIcon );
        } catch( Exception e ) {
            e.printStackTrace( );
            System.out.println( e.getMessage( ) );
        }
        repaint( );
    }

    void activeEye( JLabel label , ImageIcon icon_1 , ImageIcon icon_2 , JPasswordField passwordField ) {
        if( ! eye ) {
            label.setIcon( icon_1 );
            passwordField.setEchoChar( (char) 0 );
            eye = true;
        } else {
            label.setIcon( icon_2 );
            passwordField.setEchoChar( '•' );
            eye = false;
        }
    }

    void registrar( String name , String lastName_1 , String lastName_2 , String mail , String password ) throws SQLException {

        if( name.equals( "name" ) | lastName_1.equals( "Last name" ) | lastName_2.equals( "Last name 2" ) | mail.equals( "email" ) |
                password.equals( "password" ) | name.equals( "" ) | lastName_1.equals( "" ) | lastName_2.equals( "" ) | mail.equals( "" ) |
                password.equals( "" )) {

            Register_alert register_alert = new Register_alert();
            register_alert.setVisible(true);

        } else {
            try {

                String registrar = "call sp_insertar_usuario(?,?,?,?,?)";

                PreparedStatement insert_user = conn.prepareStatement( registrar );

                insert_user.setString( 1 , name );
                insert_user.setString( 2 , lastName_1 );
                insert_user.setString( 3 , lastName_2 );
                insert_user.setString( 4 , mail );
                insert_user.setString( 5 , password );
                insert_user.execute( );

                panel_register.setVisible( false );
                panel_login.setVisible( true );

                User_Register user_register = new User_Register();
                user_register.setVisible(true);

            } catch( SQLException e ) {
                e.printStackTrace( );

                Notify.text = "    ERROR";
                Notify notify = new Notify();
                notify.setVisible(true);
            }
        }
    }

    void login( String user , String password ) throws SQLException {

        if( user.equals( "user" ) | password.equals( "password" ) | user.equals( "" ) | password.equals( "" ) ) {

            Login_alert alert = new Login_alert();
            alert.setVisible(true);


        } else {

            String getUser = "select keyword from usuarios where nombre = '" + user + "' and keyword = '" + password + "'";

            PreparedStatement statement = conn.prepareStatement( getUser );

            ResultSet rs = statement.executeQuery( );

            if( rs.next( ) ) {
                Home home = new Home( user , password , this.getLocation( ) );
                home.setVisible( true );
                this.dispose( );
            } else {
                Notify.text = "ERROR";
                Notify notify = new Notify( );
                notify.setVisible( true );
            }
        }

    }
}
