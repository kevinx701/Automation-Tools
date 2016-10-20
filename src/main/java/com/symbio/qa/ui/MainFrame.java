package com.symbio.qa.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.apache.log4j.BasicConfigurator;

import com.symbio.qa.beans.DriverBeans;

public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JFrame jFrame = this;

    public MainFrame() {
        initComponent();
    }

    private void initComponent() {

        jFrame.setTitle("Multiple Browser test");
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create the menu bar.
        MenuBar menuBar = new MenuBar(jFrame);
        new MenuAction(menuBar);
        jFrame.setJMenuBar(menuBar.getMenuBar());

        ActionMenu actionMenu = new ActionMenu();
        jFrame.add(actionMenu);

        RightPanel rightP = new RightPanel();
        LeftPanel leftP = new LeftPanel(rightP);
        // jFrame.add(leftP);

        // jFrame.add(rightP);

        GroupLayout layout = new GroupLayout(jFrame.getContentPane());
        jFrame.setLayout(layout);

        /*
         * layout.setHorizontalGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
         * .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
         * .addComponent(actionMenu)) .addContainerGap(69, Short.MAX_VALUE)) .addGroup(GroupLayout.Alignment.LEADING,
         * layout.createSequentialGroup().addGap(5,5,5) .addComponent(leftP).addGap(18, 18,
         * 18).addComponent(rightP).addGap(41, 41, 41)));
         * 
         * layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
         * .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
         * .addGroup(layout.createSequentialGroup().addComponent(actionMenu)))
         * 
         * .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(leftP).addComponent(rightP)
         * ) .addContainerGap()));
         */
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftP, rightP);
        jFrame.add(splitPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(actionMenu))
                                .addContainerGap(69, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.LEADING,
                        layout.createSequentialGroup().addGap(5, 5, 5).addComponent(splitPane).addGap(5, 5, 5)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup().addComponent(actionMenu)))

        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(splitPane))
                        .addContainerGap()));

        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {

                if (DriverBeans.getIEDriver() != null)
                    DriverBeans.getIEDriver().close();
                if (DriverBeans.getFirefoxDriver() != null)
                    DriverBeans.getFirefoxDriver().close();
                if (DriverBeans.getChromeDriver() != null)
                    DriverBeans.getChromeDriver().close();
                System.exit(0);
            }
        });
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        BasicConfigurator.configure();
        MainFrame mainframe = new MainFrame();

    }

}
