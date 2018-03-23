package practice;
 
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
 
class Window01 extends JFrame{
 
    private JPanel mainPanel = new JPanel() {
        protected void paintComponent(java.awt.Graphics g) {
            if(img != null)
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        };
    };
    private BufferedImage img;
    private JMenuBar bar = new JMenuBar();
    private JMenu function = new JMenu("기능");
    private JMenuItem magnifier = new JMenuItem("돋보기");
    private JMenuItem exit = new JMenuItem("종료");
    
    private int size = 300;
    private int zoom = 3;//배율 설정
    private int megascope = size/zoom;
    
    public Window01() {
        this.display();
        this.event();
        this.menu();
        
        this.setTitle("GUI테스트");
        this.setSize(500, 400);
        this.setLocationByPlatform(true);
//        상단부분이 나오지 않도록 설정
//        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
    }
 
    private void display() {
        this.setContentPane(mainPanel);
        
    }
    private void event() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //종료 단축키 설정
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        exit.setAccelerator(esc);
        exit.addActionListener(event->{
            System.exit(0);
        });
        //돋보기 단축키 설정
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        magnifier.setAccelerator(f5);
                
        magnifier.addActionListener(event->{
        Thread t = new Thread() {
            @Override
            public void run() {
                    while(true) {                        
                        PointerInfo info = MouseInfo.getPointerInfo();
                        Point p = info.getLocation();
                        System.out.println("x = " + p.x + " y = " + p.y);
                        try {
                            Robot r = new Robot();
                            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                            Rectangle rect = new Rectangle(p.x -(megascope/2), p.y-(megascope/2), megascope, megascope);
                            img = r.createScreenCapture(rect);
                            mainPanel.repaint();
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000/60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.setDaemon(true);
            t.start();
        });
        
    }
    private void menu() {
        this.setJMenuBar(bar);
        bar.add(function);
        function.add(magnifier);
        function.add(exit);
    }
}
 
public class Test01 {
    public static void main(String[] args) {
        Window01 window = new Window01();
    }
}
