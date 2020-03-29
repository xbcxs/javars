package com.xbcxs.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AutoDemoFrame extends JFrame {

    private static volatile boolean cycle = true;

    private JButton btnTest = null;

    private JButton btnDemo = null;

    public AutoDemoFrame() {
        super("AutoDemoFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(60, 60, 500, 360);
        getContentPane().setLayout(null);
        this.btnTest = new JButton("停止");
        this.btnTest.setBounds(30, 30, 120, 36);
        this.btnTest.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        btnTest.setText("线程停止 !");
                        cycle = false;
                    }
                }
        );
        this.btnDemo = new JButton("启动");
        this.btnDemo.setBounds(350, 250, 76, 23);
        this.btnDemo.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        startDemo();
                    }
                }
        );
        getContentPane().add(this.btnTest);
        getContentPane().add(this.btnDemo);
    }

    public void startDemo() {
        new Thread(
                () -> run()
        ).start();
    }

    public void run() {
        try {
            Robot robot = new Robot();
            cycle = true;
            while (cycle) {
                robot.delay(5000);
                if(cycle){
                    robot.mouseMove(0, 0);
                    robot.delay(100);
                    robot.mouseMove(400, 700);
                    robot.delay(100);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.delay(100);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AutoDemoFrame adf = new AutoDemoFrame();
        adf.setVisible(true);


    }

}