/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;
import java.awt.event.MouseEvent;


public class TreeModelDemo extends JApplet {
  
  private JButton jbtAdd = new JButton("Add Node");
  private JButton jbtRemove = new JButton("Remove Selected Node");
  private JTree jTree1 = new JTree();
  private JTextArea jtreemessage = new JTextArea();
  public TreeModelDemo() {
    // Create the first tree
    DefaultMutableTreeNode root, europe, northAmerica, us;
    europe = new DefaultMutableTreeNode("Europe");
    europe.add(new DefaultMutableTreeNode("UK"));
    europe.add(new DefaultMutableTreeNode("Germany"));
    europe.add(new DefaultMutableTreeNode("France"));
    europe.add(new DefaultMutableTreeNode("Norway"));

    northAmerica = new DefaultMutableTreeNode("North America");
    us = new DefaultMutableTreeNode("US");
    us.add(new DefaultMutableTreeNode("California"));
    us.add(new DefaultMutableTreeNode("Texas"));
    us.add(new DefaultMutableTreeNode("New York"));
    us.add(new DefaultMutableTreeNode("Florida"));
    us.add(new DefaultMutableTreeNode("Illinois"));
    northAmerica.add(us);
    northAmerica.add(new DefaultMutableTreeNode("Canada"));

    root = new DefaultMutableTreeNode("World");
    root.add(europe);
    root.add(northAmerica);

     JPanel p1 = new JPanel();
    p1.add(new JLabel("selectionMode"));
  
    p1.add(jbtAdd);
    p1.add(jbtRemove);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1,2));
    panel.add(new JScrollPane(jTree1 = new JTree(new DefaultTreeModel(root))));
    jtreemessage.setWrapStyleWord(true);
    jtreemessage.setLineWrap(true);
    
    getContentPane().add(p1, BorderLayout.NORTH);
    getContentPane().add(new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel, new JScrollPane(jtreemessage)), BorderLayout.CENTER);

    // Get tree information
    jTree1.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent me)
        {
            TreePath tp = jTree1.getPathForLocation(me.getX(), me.getY());
           
             Object node = jTree1.getLastSelectedPathComponent();
             if(node != null)
             {
                     jtreemessage.append("Node "+ node.toString() +" is selected \n");
             }
             
        }
});

    getContentPane().add(new JSplitPane(JSplitPane.VERTICAL_SPLIT,
    panel, new JScrollPane(jtreemessage)), BorderLayout.CENTER);
    
    
      jbtAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)
          jTree1.getLastSelectedPathComponent();

        if (parent == null) {
          JOptionPane.showMessageDialog(null,
            "No node in the first tree is selected");
          return;
        }

        // Enter a new node
        String nodeName = JOptionPane.showInputDialog(
          null, "Enter a name for this new node", "Add a Child",
          JOptionPane.QUESTION_MESSAGE);

        // Insert the new node as a child of treeNode
        parent.add(new DefaultMutableTreeNode(nodeName));

        // Reload the model since a new tree node is added
        ((DefaultTreeModel)(jTree1.getModel())).reload();
      }
    });

    jbtRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Get all selected paths
        TreePath[] paths = jTree1.getSelectionPaths();

        if (paths == null) {
          JOptionPane.showMessageDialog(null,
            "No node in the left tree is selected");
          return;
        }

        // Remove all selected nodes
        for (int i = 0; i < paths.length; i++) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode)
              (paths[i].getLastPathComponent());

          if (node.isRoot()) {
            JOptionPane.showMessageDialog(null,
              "Cannot remove the root");
          }
          else
            node.removeFromParent();
        }

        // Reload the model since a new tree node is added
        ((DefaultTreeModel)(jTree1.getModel())).reload();
      }
    });

  }

  public static void main(String[] args) {
    TreeModelDemo applet = new TreeModelDemo();
    JFrame frame = new JFrame();

    frame.setDefaultCloseOperation(3);
    frame.setTitle("TreeModelDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
                      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}


