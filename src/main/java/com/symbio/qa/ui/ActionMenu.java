package com.symbio.qa.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.symbio.qa.beans.DriverBeans;
import com.symbio.qa.beans.OperationBean;
import com.symbio.qa.pageObjects.PageObject;

public class ActionMenu extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel actionPanel = this;

    public ActionMenu() {
        initComponent();
    }

    private void initComponent() {
        JButton hover = new JButton("Hover");
        JButton click = new JButton("Click");
        JButton select = new JButton("Select");
        JButton input = new JButton("Input");
        JButton scrrenShot = new JButton("Screenshot");
        actionPanel.add(hover);
        actionPanel.add(click);
        actionPanel.add(select);
        actionPanel.add(input);
        actionPanel.add(scrrenShot);
        GroupLayout layout = new GroupLayout(actionPanel);
        actionPanel.setLayout(layout);
        int inveral = 5;
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup().addGap(inveral, inveral, inveral)
                                        .addGroup(layout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hover))
                                .addGap(inveral, inveral, inveral)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(click))
                                .addGap(inveral, inveral, inveral)
                                .addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(select))
                .addGap(inveral, inveral, inveral)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(input))
                .addGap(inveral, inveral, inveral)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrrenShot))
                .addContainerGap(70, Short.MAX_VALUE)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup().addGap(inveral, inveral, inveral)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

        .addGap(8, 8, 8).addComponent(hover).addComponent(click).addComponent(select).addComponent(input)
                                .addComponent(scrrenShot))
                        .addContainerGap()));
        click.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (DriverBeans.getIEDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getIEDriver();
                    pageObject.click(OperationBean.getCSS());
                }
                if (DriverBeans.getChromeDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getChromeDriver();
                    pageObject.click(OperationBean.getCSS());
                }
                if (DriverBeans.getFirefoxDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getFirefoxDriver();
                    pageObject.click(OperationBean.getCSS());
                }

            }

        });

        input.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (DriverBeans.getIEDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getIEDriver();
                    pageObject.editor(pageObject.getElement(OperationBean.getCSS()), OperationBean.getElement().getText());
                }
                if (DriverBeans.getChromeDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getChromeDriver();
                    pageObject.editor(pageObject.getElement(OperationBean.getCSS()), OperationBean.getElement().getText());
                }
                if (DriverBeans.getFirefoxDriver() != null) {
                    PageObject pageObject = new PageObject();
                    pageObject.driver = DriverBeans.getFirefoxDriver();
                    pageObject.editor(pageObject.getElement(OperationBean.getCSS()), OperationBean.getElement().getText());
                }
            }

        });
    }

    public static void main(String[] args) {

    }

}
