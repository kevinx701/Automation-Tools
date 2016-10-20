package com.symbio.qa.ui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.codehaus.jettison.json.JSONException;

import com.symbio.qa.beans.OperationBean;
import com.symbio.qa.util.Json;

public class RightPanel<T> extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel rightP = this;
    public JPanel components;
    public String fileName;
    public ButtonGroup bg = new ButtonGroup();

    private final HashMap<JRadioButton, T> map = new HashMap<JRadioButton, T>();

    public RightPanel() {
        initComponents();
    }

    private void initComponents() {
        JTextArea jtext = new JTextArea("right");
        // rightP.setLayout(new BorderLayout());
        rightP.add(jtext);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K> void addComponent() throws IOException, JSONException {
        if (components != null)
            rightP.remove(components);
        components = new JPanel();
        components.setAutoscrolls(true);
        FlowLayout layOut = new FlowLayout();
        layOut.setAlignment(FlowLayout.LEFT);
        components.setLayout(layOut);
        components.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        components.setPreferredSize(rightP.getSize());
        final HashMap<K,K> configure = readConfigure(fileName);
        ArrayList confList = (ArrayList) configure.get("elements");
        final ArrayList buttonList = new ArrayList();
        for (int i = 0; i < confList.size(); i++) {
            final int count = i;
            HashMap conf = (HashMap) confList.get(i);
            buttonList.add(i, new JRadioButton());
            final JRadioButton jrb = (JRadioButton) buttonList.get(i);
            jrb.setText(conf.get("name").toString() + "123");
            components.add(jrb);
            ((HashMap)((ArrayList) configure.get("elements")).get(i)).put("ID", jrb);
            /*
             * editor, dropdown, container(table), trees, list 
             * button
             */
            switch (conf.get("Type").toString().toLowerCase()) {
            case "editor":
                JTextField jTextField = new JTextField("please input text here", 20);
                components.add(jTextField);
                map.put(jrb, (T) jTextField);
                ((HashMap)((ArrayList) configure.get("elements")).get(i)).put("editor", jTextField);
            case "dropdwon":
                break;
            case "button":
                break;
            default:
                break;
            }
            bg.add(jrb);
            jrb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JTextField jTextField = (JTextField) map.get(jrb);
                    
                    System.out.println(configure.get("elements"));
                    Iterator iter = ((ArrayList) configure.get("elements")).iterator();
                    for (HashMap mp ;iter.hasNext();){
                        mp=(HashMap) iter.next();
                        if(jrb == mp.get("ID")){
                            OperationBean.cleanBean();
                            OperationBean.setID(mp.get("name").toString());
                            OperationBean.setType(mp.get("Type").toString());
                            OperationBean.setHandler("css");
                            if(mp.get("Type").toString().equals("editor"))
                            OperationBean.setElement((JTextField)mp.get("editor"));
                            OperationBean.setCSS(((HashMap) mp.get("handler")).get("css").toString());
                        }
                    }
                    System.out.println(jrb == ((HashMap)((ArrayList) configure.get("elements")).get(count)).get("ID"));
                }

            });

            components.add(Box.createHorizontalStrut(rightP.getWidth()));
        }
        components.setVisible(true);
        rightP.add(components);
        rightP.setVisible(true);
    }

    @SuppressWarnings("rawtypes")
    private <K> HashMap readConfigure(String fileName) throws IOException, JSONException {
        return new Json().j2m(fileName);
    }

    public static void main(String[] args) {
        RightPanel<Object> rightP = new RightPanel<Object>();
        rightP.fileName = "src/main/resources/pageObject/Bing.json";
        try {
            rightP.addComponent();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        rightP.setVisible(true);
        JFrame bbb = new JFrame();
        bbb.add(rightP);
        bbb.setVisible(true);
        bbb.pack();
    }

}
