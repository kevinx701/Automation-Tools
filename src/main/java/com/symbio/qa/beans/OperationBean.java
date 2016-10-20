package com.symbio.qa.beans;

import javax.swing.JTextField;

public class OperationBean {

    private OperationBean() {
        // TODO Auto-generated constructor stub
    }

    private static String name;
    private static String css;
    private static String type;
    private static String handeler;
    private static String value;
    private static JTextField element;

    public static void setElement(JTextField in){
        element = in;
    }
    
    public static JTextField getElement(){
        return element;
    }
    
    public static void setValue(String in) {
        value = in;
    }

    public static String getValue() {
        return value;
    }

    public static void setID(String id) {
        name = id;
    }

    public static String getID() {
        return name;
    }

    public static void setHandler(String in) {
        handeler = in;
    }

    public static String getHandeler() {
        return handeler;
    }

    public static void setCSS(String in) {
        css = in;
    }

    public static String getCSS() {
        return css;
    }

    public static void setType(String in) {
        type = in;
    }

    public static String getType() {
        return type;
    }

    public static void cleanBean() {
        name = null;
        css = null;
        type = null;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
