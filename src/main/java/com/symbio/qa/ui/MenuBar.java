package com.symbio.qa.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem, exitMenuItem, browserMI, launchMI, searchMI, urlSetting;
    private JRadioButtonMenuItem rbMenuItem;
    private JCheckBoxMenuItem cbMenuItem;
    private JMenu submenu;
    public JFrame jFrame;

    public MenuBar(JFrame jFrame) {
        this.jFrame = jFrame;
        initComponent();
    }

    private void initComponent() {
        // Create the menu bar.
        menuBar = new JMenuBar();

        // Build the first menu.
        menu = new JMenu("Project");
        menu.setMnemonic(KeyEvent.VK_P);
        menu.getAccessibleContext().setAccessibleDescription("The menu of configuration");
        menuBar.add(menu);

        // a group of JMenuItems
        browserMI = new JMenuItem("Browsers", KeyEvent.VK_B);
        browserMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        browserMI.getAccessibleContext().setAccessibleDescription("The menu configure browsers properties");
        menu.add(browserMI);

        urlSetting = new JMenuItem("URL Setting", KeyEvent.VK_U);
        menu.add(urlSetting);

        menuItem = new JMenuItem("Both text and icon1", new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);

        // a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        // a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        // a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
        });
        menu.add(exitMenuItem);
        
        // Build second menu in the menu bar.
        menu = new JMenu("Launch Browsers");
        menu.setMnemonic(KeyEvent.VK_L);
        menu.getAccessibleContext().setAccessibleDescription("Lanuch Browsers");
        launchMI = new JMenuItem("Launch Browsers...");
        /**/
        searchMI = new JMenuItem("Search XXX in Browsers...");
        menu.add(searchMI);
        menu.add(launchMI);
        menuBar.add(menu);
    }

    public JMenuItem getSearch(){
        return searchMI;
    }
    public JMenuBar getMenuBar() {
        return menuBar;
    }
    
    public JMenuItem getExistMI(){
        return exitMenuItem;
    }
    
    public JMenuItem getBrowserMI(){
        return browserMI;
    }
    
    public JMenuItem getLaunchMI(){
        return launchMI;
    }
    
    public JMenuItem getURL(){
        return urlSetting;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
