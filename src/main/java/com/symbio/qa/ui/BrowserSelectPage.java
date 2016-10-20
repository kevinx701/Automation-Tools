package com.symbio.qa.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.symbio.qa.util.MyProperties;

public class BrowserSelectPage {

    public BrowserSelectPage() {
        initComponent();
    }
    
    private void initComponent() {

        MyProperties prop = null;
        try {
            prop = new MyProperties("src/main/resources/properties/project.properties");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        final MyProperties myProperties = prop;
        Boolean ieState = Boolean.valueOf(prop.getPropertiy("IE"));
        Boolean firfoxState = Boolean.valueOf(prop.getPropertiy("FireFox"));
        Boolean ChromeState = Boolean.valueOf(prop.getPropertiy("Chrome"));
        final JFrame jFrame = new JFrame();
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocation(200,200);
        jFrame.setSize(400,300);
        jFrame.setTitle("Browsers selector...");
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        JLabel titles = new JLabel("Please select Browsers used in testing.");
        jPanel.add(titles);
        final JCheckBox ieCheckBox = new JCheckBox("IE Browser", null, ieState);
        final JCheckBox fireFoxCheckBox = new JCheckBox("Firefox Browser", null, firfoxState);
        final JCheckBox chromeCheckBox = new JCheckBox("Chrome Browser", null, ChromeState);
        jPanel.add(ieCheckBox);
        jPanel.add(fireFoxCheckBox);
        jPanel.add(chromeCheckBox);
        JButton saveButton = new JButton("Save");
        saveButton.setMnemonic(KeyEvent.VK_S);
        JButton cancelButton = new JButton("Cancle");
        cancelButton.setMnemonic(KeyEvent.VK_C);
        jPanel.add(saveButton);
        jPanel.add(cancelButton);

        GroupLayout layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(
                        layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titles)
                                .addComponent(ieCheckBox)
                                .addComponent(fireFoxCheckBox)
                                .addComponent(chromeCheckBox))
                        .addContainerGap(69, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, 
                        layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)
                        .addGap(41, 41, 41)));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(titles)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(ieCheckBox)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(fireFoxCheckBox)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chromeCheckBox, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                                
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton)
                                .addComponent(cancelButton))
                        .addContainerGap()));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               
                myProperties.setPropertiy("IE", String.valueOf(ieCheckBox.isSelected()));
                myProperties.setPropertiy("FireFox", String.valueOf(fireFoxCheckBox.isSelected()));
                myProperties.setPropertiy("Chrome", String.valueOf(chromeCheckBox.isSelected()));
                try {
                    myProperties.saveProperties();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jFrame.dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                jFrame.dispose();
            }
        });

        jFrame.setVisible(true);
        
    }
}
