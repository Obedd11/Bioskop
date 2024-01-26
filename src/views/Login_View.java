package views;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import Controllers.Login_Controller;
import Entities.User_Entity;

import java.io.File;

public class Login_View extends JFrame {
    private Login_Controller _loginController=new Login_Controller();
    final private Font mainFont = new Font("Segoe UI",Font.BOLD,20);
    Container c=getContentPane();

    public Login_View()
    {
        setTitle("Login");
        setSize(840,490);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelkanan();
        panekKiri();
        setVisible(true);

    }

    public void panelkanan()
    {
        JLabel lblUsername,lblPassword,lblLogin;
        JButton btnSignIn,btnRegister;
        JTextField jfEmail;
        JPasswordField pfPassword;
        JPanel pnlKanan;
        JCheckBox cbPassword;

        //panel
        pnlKanan=new JPanel();
        pnlKanan.setBounds(370,0,470,490);
        pnlKanan.setBackground(Color.gray);
        pnlKanan.setLayout(null);

        //label
        lblLogin=new JLabel("LOGIN");
        lblLogin.setFont(new Font("Segoe UI",Font.BOLD,32));
        lblLogin.setForeground(Color.darkGray);
        lblLogin.setBounds(195,50,210,50);


        lblUsername=new JLabel("Email");
        lblUsername.setFont(mainFont);
        lblUsername.setForeground(Color.darkGray);
        lblUsername.setBounds(26,220,97,25);

        lblPassword=new JLabel("Password");
        lblPassword.setFont(mainFont);
        lblPassword.setForeground(Color.darkGray);
        lblPassword.setBounds(26,220,97,150);

        //txtField
        jfEmail=new JTextField();
        jfEmail.setFont(mainFont);
        jfEmail.setBounds(26,250,400,25);

        pfPassword=new JPasswordField();
        pfPassword.setFont(mainFont);
        pfPassword.setBounds(26,320,380,25);

        //button
        btnSignIn=new JButton("SIGN IN");
        btnSignIn.setFont(new Font("Segoe UI",Font.BOLD,9));
        btnSignIn.setBounds(26, 350, 75, 20);
        btnSignIn.setBackground(Color.lightGray);
        btnSignIn.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                String email=jfEmail.getText();
                String password=pfPassword.getText();

                User_Entity user=_loginController.login(email,password);

                if (jfEmail.getText().equals("")&&pfPassword.getText().equals("")){

                    JOptionPane.showMessageDialog(null,"Silahkan Input !","Warning !" ,JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (user!=null)
                    {
                        JOptionPane.showMessageDialog(null,"login sukses !","Information !" ,JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"login gagal !","Warning !" ,JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSignIn.setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSignIn.setBackground(Color.lightGray);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

        });

        // // btnRegister=new JButton("SIGN UP");
        // // btnRegister.setFont(new Font( "Segoe UI",Font.BOLD,9));
        // // btnRegister.setBounds(120, 350, 75, 20);
        // // btnRegister.setBackground(Color.lightGray);
        // // btnRegister.addMouseListener(new MouseInputListener() {

        // //     @Override
        // //     public void mouseClicked(MouseEvent e) {
        // //         //    new view_controller().toRegister();
        // //         setVisible(false);
        // //     }

        // //     @Override
        // //     public void mousePressed(MouseEvent e) {
        // //         // TODO Auto-generated method stub

        // //     }

        // //     @Override
        // //     public void mouseReleased(MouseEvent e) {
        // //         // TODO Auto-generated method stub

        // //     }

        // //     @Override
        // //     public void mouseEntered(MouseEvent e) {
        // //         btnRegister.setBackground(Color.gray);

        // //     }

        // //     @Override
        // //     public void mouseExited(MouseEvent e) {
        // //         btnRegister.setBackground(Color.lightGray);

        // //     }

        // //     @Override
        // //     public void mouseDragged(MouseEvent e) {
        // //         // TODO Auto-generated method stub

        // //     }

        // //     @Override
        // //     public void mouseMoved(MouseEvent e) {
        // //         // TODO Auto-generated method stub

        // //     }

        // });

        //checkbox
        cbPassword=new JCheckBox();
        cbPassword.setBounds(405,320,20,25);
        cbPassword.setBackground(Color.lightGray);
        cbPassword.addActionListener((e)->{
            if (cbPassword.isSelected()){
                pfPassword.setEchoChar((char)0);
            }else{
                pfPassword.setEchoChar('*');
            }
        });
        pnlKanan.add(cbPassword);
        pnlKanan.add(jfEmail);
        pnlKanan.add(lblUsername);
        pnlKanan.add(lblPassword);
        pnlKanan.add(lblLogin);
        pnlKanan.add(pfPassword);
        pnlKanan.add(btnSignIn);
        //pnlKanan.add(btnRegister);
        c.add(pnlKanan);
    }

    public void panekKiri()
    {
        JLabel lblWelcome;
        ImageIcon imgIcon = loadImage("src/assets/download.jpeg");
        JLabel imgLabel = new JLabel(imgIcon);
        imgLabel.setBounds(9, 100, 350, 350);

        // panel
        JPanel pnlKiri = new JPanel();
        pnlKiri.setBounds(0, 0, 370, 490);
        pnlKiri.setBackground(Color.darkGray);
        pnlKiri.setLayout(null);

        // label
        lblWelcome = new JLabel("BIOSKOP");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblWelcome.setBounds(100, 50, 300, 50);
        lblWelcome.setForeground(Color.gray);

        // image
        pnlKiri.add(imgLabel);
        pnlKiri.add(lblWelcome);
        c.add(pnlKiri);

    }

    private ImageIcon loadImage(String imagePath) {
        try {
            BufferedImage bImage = ImageIO.read(new File(imagePath));
            Image imgResize = bImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            return new ImageIcon(imgResize);
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }
}
