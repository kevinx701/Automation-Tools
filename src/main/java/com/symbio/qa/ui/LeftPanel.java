package com.symbio.qa.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.codehaus.jettison.json.JSONException;

public class LeftPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel leftP = this;
    @SuppressWarnings("rawtypes")
    private RightPanel rightP;
    private DefaultMutableTreeNode root, child, chosen;
    private JTree tree;
    private DefaultTreeModel model;

    @SuppressWarnings("rawtypes")
    public LeftPanel(RightPanel rightP) {
        this.rightP = rightP;
        File[] treeNodeName = getTreeData();

        for (File file : treeNodeName) {
            System.out.println(file.toString());

        }
        initComponents(treeNodeName);

    }

    private File[] getTreeData() {
        return new File("src/main/resources/pageObject").listFiles();
    }

    private void initComponents(File[] treeNodeName) {
        leftP.setLayout(new BorderLayout());
        leftP.setPreferredSize(new Dimension(300, 0));
        leftP.setMinimumSize(getPreferredSize());
        root = new DefaultMutableTreeNode("root");
        tree = new JTree(root);
        leftP.add(new JScrollPane(tree));
        model = (DefaultTreeModel) tree.getModel();
        for (File file : treeNodeName) {
            child = new DefaultMutableTreeNode(file.toString());
            chosen = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (chosen == null)
                chosen = root;
            model.insertNodeInto(child, chosen, 0);
        }

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @SuppressWarnings("unchecked")
            public void valueChanged(TreeSelectionEvent arg0) {
                TreePath path = tree.getSelectionPath();
                if (path.getPathCount() > 1) {
                    System.out.println("select" + tree.getSelectionPath().getPathComponent(1));
                    rightP.fileName = tree.getSelectionPath().getPathComponent(1).toString();
                    try {
                        rightP.addComponent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    rightP.revalidate();
                    rightP.repaint();
                }
            }

        });
    }

    public static void main(String[] args) {
        // new LeftPanel(rightP);
    }

}
