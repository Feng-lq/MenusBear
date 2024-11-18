package application;

import javax.swing.*;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TomatoTimer{
    JFrame window1;
    JPanel forImage;
    ImageIcon icon;
    JLabel backgroundLabel;
    JPanel forContent;
    JPanel forText;
    JLabel label;
    JButton startButton;
    JButton stopButton;
    JButton resetButton;
    JButton setButton;

    JPanel forUI;
    JButton mini;
    JButton quit;

    int mouseAtX;
    int mouseAtY;

    JButton aa;
    JButton b;
    JButton c;
    JButton d;
    JButton ee;
    JPanel newButton;
    JPanel imPanel;
    Boolean isCN = true;

    static String fileName = "TomatoConfig.txt";
    
    int recMinutes;
    int minutes; // ����һ�����ͱ��������ڴ洢������
    int seconds; // ����һ�����ͱ��������ڴ洢����
    Timer timer;// ����һ��Timer�������ڼ�ʱ

    public TomatoTimer(boolean tempIsCN, int timeSet){
        //��������
        String title = "����ʱ��";
        isCN = tempIsCN;
        recMinutes = timeSet;
        if(!tempIsCN)   title = "Tomato Timer";
        window1 = new JFrame(title);

        // window1.getStylesheets().add(getClass());

        //��ʼ��
        minutes = timeSet; // ��ʼ��������Ϊ25
        seconds = 0; // ��ʼ������Ϊ0
        // timer = new Timer(1000, new TimerListener()); // ����һ�����Ϊ1000ms��Timer���󣬲�Ϊ�����ActionListener

        //һ�������ű���ͼƬ��Panel
        forImage = new JPanel();

        forUI = new JPanel();
        if(tempIsCN){
            mini = new JButton("��С��");
            quit = new JButton("�ر�");
        }else{
            mini = new JButton("Minimize");
            quit = new JButton("Quit");
        }
        
        forUI.setLayout(null);

        forUI.setOpaque(false);
        forUI.add(mini);
        forUI.add(quit);

        mini.setBackground(new Color(122,80,23));
        mini.setBorderPainted(false);
        mini.setFocusPainted(false);
        mini.setBounds(0,0,100,40);

        quit.setBackground(new Color(122,80,23));
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setBounds(500,0,100,40);

        mini.addActionListener(new ap());
        quit.addActionListener(new ap());

        mini.setForeground(Color.white);
        mini.setFont(new Font("SimHei", Font.BOLD, 20));
        quit.setForeground(Color.white);
        quit.setFont(new Font("SimHei", Font.BOLD, 20));


        //����ͼƬ
        icon = new ImageIcon("testBackground.png");

        //����������
        backgroundLabel = new JLabel(icon);
        backgroundLabel.setBounds(0,0,600,400);

        window1.getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
        window1.setResizable(false);

        //һ�������Ű�ť��Panel
        forContent = new JPanel(new GridLayout(1, 4));
        forContent.setPreferredSize(new Dimension(600, 80));
        //һ�����������ֵ�Panel
        forText = new JPanel();
        forText.setOpaque(false);

        //����
        label = new JLabel(minutes+":00", SwingConstants.CENTER); // ����һ����ʼֵΪ��25:00����JLabel����
        label.setFont(new Font("Times New Roman", Font.BOLD, 200));
        label.setForeground(new Color(41,33,16));
        // label.setForeground(Color.white);

        newButton = new JPanel();
        aa = new JButton("<html>05<br>00</html>");
        aa.setFont(new Font("SimHei", Font.BOLD, 15));
        newButton.add(aa);
        newButton.setOpaque(false);
        aa.setBorderPainted(false);

        b = new JButton("<html>10<br>00</html>");
        b.setFont(new Font("SimHei", Font.BOLD, 15));
        newButton.add(b);
        b.setBorderPainted(false);

        c = new JButton("<html>15<br>00</html>");
        c.setFont(new Font("SimHei", Font.BOLD, 15));
        newButton.add(c);
        c.setBorderPainted(false);

        d = new JButton("<html>20<br>00</html>");
        d.setFont(new Font("SimHei", Font.BOLD, 15));
        newButton.add(d);
        d.setBorderPainted(false);

        ee = new JButton("<html>45<br>00</html>");
        ee.setFont(new Font("SimHei", Font.BOLD, 15));
        newButton.add(ee);
        ee.setBorderPainted(false);

        Color saveForA = new Color(248,169,0);
        Color saveForD = new Color(224,157,65);
        aa.setBackground(saveForA);
        aa.setFocusPainted(false);
        b.setBackground(saveForA);
        b.setFocusPainted(false);
        c.setBackground(saveForA);
        c.setFocusPainted(false);
        d.setBackground(saveForA);
        d.setFocusPainted(false);
        ee.setBackground(saveForA);
        ee.setFocusPainted(false);

        aa.setPreferredSize(new Dimension(50,50));
        b.setPreferredSize(new Dimension(50,50));
        c.setPreferredSize(new Dimension(50,50));
        d.setPreferredSize(new Dimension(50,50));
        ee.setPreferredSize(new Dimension(50,50));

        aa.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                aa.setBackground(saveForA);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                aa.setBackground(saveForA);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                aa.setBackground(saveForA.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                aa.setBackground(saveForA.darker());
            }
        });
        
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                b.setBackground(saveForA);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                b.setBackground(saveForA);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                b.setBackground(saveForA.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                b.setBackground(saveForA.darker());
            }
        });
        
        c.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                c.setBackground(saveForA);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                c.setBackground(saveForA);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                c.setBackground(saveForA.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                c.setBackground(saveForA.darker());
            }
        });

        d.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                d.setBackground(saveForA);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                d.setBackground(saveForA);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                d.setBackground(saveForA.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                d.setBackground(saveForA.darker());
            }
        });

        ee.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ee.setBackground(saveForA);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                ee.setBackground(saveForA);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                ee.setBackground(saveForA.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                ee.setBackground(saveForA.darker());
            }
        });

        //��ť��
        String a = "��ʼ";
        if(!tempIsCN)   a = "Begin";
        startButton = new JButton(a);
        startButton.setFont(new Font("SimHei", Font.BOLD, 40));
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                startButton.setBackground(saveForD);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                // Not working :(
                    startButton.setBackground(Color.pink);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(saveForD);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(saveForD.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                startButton.setBackground(saveForD.darker());
            }
        });

        a = "��ͣ";
        if(!tempIsCN)   a = "Pause";
        stopButton = new JButton(a);
        stopButton.setFont(new Font("SimHei", Font.BOLD, 40));
        stopButton.setFocusPainted(false);
        stopButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                stopButton.setBackground(saveForD);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                // Not working :(
                    stopButton.setBackground(Color.pink);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                stopButton.setBackground(saveForD);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                stopButton.setBackground(saveForD.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                stopButton.setBackground(saveForD.darker());
            }
        });

        a = "����";
        if(!tempIsCN)   a = "Reset";
        resetButton = new JButton(a);
        resetButton.setFont(new Font("SimHei", Font.BOLD, 40));
        resetButton.setFocusPainted(false);
        resetButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                resetButton.setBackground(saveForD);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                // Not working :(
                    resetButton.setBackground(Color.pink);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setBackground(saveForD);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setBackground(saveForD.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                resetButton.setBackground(saveForD.darker());
            }
        });

        a = "����";
        if(!tempIsCN)   a = "Options";
        setButton = new JButton(a);
        setButton.setFont(new Font("SimHei", Font.BOLD, 40));
        setButton.setFocusPainted(false);
        setButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                setButton.setBackground(saveForD);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                // Not working :(
                    setButton.setBackground(Color.pink);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                setButton.setBackground(saveForD);
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                setButton.setBackground(saveForD.darker());
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                setButton.setBackground(saveForD.darker());
            }
        });

        startButton.setBorderPainted(false); //��Ϊ�߽�͸��
        stopButton.setBorderPainted(false); //��Ϊ�߽�͸��
        resetButton.setBorderPainted(false); //��Ϊ�߽�͸��
        setButton.setBorderPainted(false); //��Ϊ�߽�͸��

        // startButton.setBorder(BorderFactory.createRaisedBevelBorder());

        startButton.setBackground(saveForD);
        stopButton.setBackground(saveForD);
        resetButton.setBackground(saveForD);
        setButton.setBackground(saveForD);

        aa.addActionListener(new aListener());
        b.addActionListener(new bListener());
        c.addActionListener(new cListener());
        d.addActionListener(new dListener());
        ee.addActionListener(new eListener());

        startButton.addActionListener(new StartButtonListener()); // ΪstartButton���ActionListener
        stopButton.addActionListener(new StopButtonListener()); // ΪstopButton���ActionListener
        resetButton.addActionListener(new ResetButtonListener()); // ΪresetButton���ActionListener
        setButton.addActionListener(new SetTimeButtonListener()); // ΪsetTimeButton���ActionListener

        timer = new Timer(1000, new TimerListener()); // ����һ�����Ϊ1000ms��Timer���󣬲�Ϊ�����ActionListener
 
        //��Panel��������ֺͰ�ť
        forText.add(label, BorderLayout.CENTER);
        forContent.add(startButton);
        forContent.add(stopButton);
        forContent.add(resetButton);
        forContent.add(setButton);

        //�޸ķǱ���Panel��͸���ȣ�����û�ã�
        imPanel = (JPanel)window1.getContentPane();
        imPanel.setOpaque(false);
        forContent.setOpaque(false);
        forText.setOpaque(false);

        //��Panel��ӵ�Frame��
        // window1.setLayout(new BorderLayout(0, 15));
        window1.getContentPane().setLayout(null);

        window1.add(forImage);

        forContent.setBounds(0,320,600,80);
        window1.add(forContent);


        forUI.setBounds(0,0,600,80);
        // forUI
        window1.add(forUI);

        newButton.setBounds(0,250,600,80);
        window1.add(newButton, BorderLayout.NORTH);

        forText.setBounds(0,25,600,250);
        window1.add(forText);
        
     

        //������Ļ���100�����أ��ϱ�100�����أ����ڵĿ���300������300
		window1.setBounds(100,100,600,400);    //���ô���1����Ļ�ϵ�λ�ü���С

        window1.setUndecorated(true);

        window1.addMouseListener(new MouseAdapter() 
        {
            public void mousePressed(MouseEvent e) 
            {
                /*
                                                    * ��ȡ������ʱ������
                 */
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
         });      
        
        window1.addMouseMotionListener(new MouseMotionAdapter()
          {
              public void mouseDragged(MouseEvent e) 
              {
                  window1.setLocation((e.getXOnScreen()-mouseAtX),(e.getYOnScreen()-mouseAtY));//������ק�󣬴��ڵ�λ��
              }
          });


		window1.setVisible(true); //���ô��ڿɼ�
        // window1.getContentPane().setBackground(Color.red);
		window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�ͷŵ�ǰ����
    }

    public void start() { // �������ѹ�������ʱ��
        window1.setVisible(true); // ����������Ϊ�ɼ�
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        try (Scanner sc = new Scanner(new FileReader(fileName))){
            Boolean tempIsCN = false;
            if(sc.nextLine().equals("CN")){
                tempIsCN = true;
            }
            int timeSet = Integer.parseInt(sc.nextLine());
            TomatoTimer tomatoTimerHong = new TomatoTimer(tempIsCN, timeSet);
		    tomatoTimerHong.start();
        }
	}

    private class StartButtonListener implements ActionListener { // ��ʼ��ť��ActionListener
        public void actionPerformed(ActionEvent e) {
            timer.start(); // ������ʱ��
        }
    }

    private class aListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minutes = 5; // ���·�����
            recMinutes = 5;
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
            timer.stop();
        }
    }

    private class bListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minutes = 10; // ���·�����
            recMinutes = 10;
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
            timer.stop();
        }
    }

    private class cListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minutes = 15; // ���·�����
            recMinutes = 15;
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
            timer.stop();
        }
    }

    private class dListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minutes = 20; // ���·�����
            recMinutes = 20;
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
            timer.stop();
        }
    }

    private class eListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minutes = 45; // ���·�����
            recMinutes = 45;
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
            timer.stop();
        }
    }

    private class StopButtonListener implements ActionListener { // ֹͣ��ť��ActionListener
        public void actionPerformed(ActionEvent e) {
            timer.stop(); // ֹͣ��ʱ��
        }
    }

    private class ResetButtonListener implements ActionListener { // ���ð�ť��ActionListener
        public void actionPerformed(ActionEvent e) {
            timer.stop(); // ֹͣ��ʱ��
            minutes = recMinutes; // ������������Ϊ25
            seconds = 0; // ����������Ϊ0
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
        }
    }

    private class SetTimeButtonListener implements ActionListener { // ����ʱ�䰴ť��ActionListener
        public void actionPerformed(ActionEvent e) {
            String str = "Enter time in minutes (max 60):";
            if(isCN)    str = "������ʱ�䣨������60���ӣ�";
            String input = JOptionPane.showInputDialog(window1, str); // ����һ���Ի�����ʾ�û����������
            try {
                int newMinutes = Integer.parseInt(input); // ���û�������ַ���ת��Ϊ����
                if (newMinutes > 0 && newMinutes <= 60) { // �������ķ�������1��60֮��
                    minutes = newMinutes; // ���·�����
                    recMinutes = newMinutes;
                    seconds = 0; // ����������Ϊ0
                    label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
                } else { // �������ķ���������1��60֮��
                    str = "Invalid input. Please enter an integer between 1 and 60.";
                    if(isCN)    str = "��Ч���룬������1-60֮�������";
                    JOptionPane.showMessageDialog(window1, str); // ����һ���Ի�����ʾ�û�����1��60֮�������
                }
            } catch (NumberFormatException ex) { // ����û�����Ĳ�������
                str = "Invalid input. Please enter an integer between 1 and 60.";
                    if(isCN)    str = "��Ч���룬������1-60֮�������";
                JOptionPane.showMessageDialog(window1, str); // ����һ���Ի�����ʾ�û�����1��60֮�������
            }
        }
    }

    private class TimerListener implements ActionListener { // ��ʱ����ActionListener
        public void actionPerformed(ActionEvent e) {
            if (minutes == 0 && seconds == 0) { // ���ʱ�䵽��
                timer.stop(); // ֹͣ��ʱ��
                String str = "Times Up!";
                if(isCN)    str = "ʱ�䵽��";
                JOptionPane.showMessageDialog(window1, str); // ����һ���Ի�����ʾ�û�ʱ�䵽��
            } else if (seconds == 0) { // �������Ϊ0
                minutes--; // ��������1
                seconds = 59; // ��������Ϊ59
            } else { // ���������Ϊ0
                seconds--; // ������1
            }
            label.setText(String.format("%02d:%02d", minutes, seconds)); // ����label���ı�
        }
    }

    private class ap implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand() == "��С��"){
                window1.setExtendedState(window1.ICONIFIED);
            }else if(e.getActionCommand() == "�ر�"){
            	window1.dispose(); 
            }
        }
    }

    
 } 
 
