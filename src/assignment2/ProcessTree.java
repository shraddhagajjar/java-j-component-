/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 *
 * @author shraddha
 */
public class ProcessTree extends JApplet {

  // Create a combo box for choosing selection modes

  // Create two buttons
  private JButton jbtAdd = new JButton("Add Node");
  private JButton jbtRemove = new JButton("Remove Selected Node");
  private JTextArea jtreemessage = new JTextArea();
  // Declare two trees
  private JTree jTree1;

  public ProcessTree() {
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

    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(1, 2));
    p2.add(new JScrollPane(jTree1 = new JTree(root)));
   
    JSplitPane jSplitPane1 = new JSplitPane(
      JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.add(new JScrollPane(jTree1), JSplitPane.LEFT);
    jSplitPane1.add(new JScrollPane(jtreemessage), JSplitPane.RIGHT);
    add(jSplitPane1);
    
    
    
    getContentPane().add(p1, BorderLayout.NORTH);
    getContentPane().add(p2, BorderLayout.CENTER);

    // Register listeners

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
    ProcessTree applet = new ProcessTree();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ProcessTree");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(650, 320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
                      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}

