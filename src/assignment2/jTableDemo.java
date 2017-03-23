/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

        
/**
 *
 * @author shraddha
 * 15992
 */
public class jTableDemo extends JApplet{

    private String[] columnname = {"Country","Capital","Population in Millions","Democracy"};
    private Object[][] rowData = {
    {"USA", "Washington DC", 280, true},
    {"Canada", "Ottawa", 32, true},
    {"United Kingdom", "London", 60, true},
    {"Germany", "Berlin", 83, true},
    {"France", "Paris", 60, true},
    {"Norway", "Oslo", 4.5, true},
    {"India", "New Delhi", 1046, true}
  };
    
    private JTable jTable1 = new JTable(rowData, columnname);
    private JSpinner jspiRowHeight = new JSpinner(new SpinnerNumberModel(16, 1, 50, 1));
    private JCheckBox jchkShowGrid = new JCheckBox("Show Grid", true);
    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());

    public jTableDemo() throws HeadlessException {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("RowHeight"));
        panel1.add(jspiRowHeight);
        panel1.add(jchkShowGrid);
        add(panel1,BorderLayout.SOUTH);
        add(new JScrollPane(jTable1), BorderLayout.CENTER);
        //tableproperties.
        jTable1.setRowSorter(sorter);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.setBackground(Color.white);
        jTable1.setGridColor(Color.MAGENTA);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setForeground(Color.blue);
        
        jspiRowHeight.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                jTable1.setRowHeight(((Integer)(jspiRowHeight.getValue())).intValue());
            }
        });
        jchkShowGrid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                jTable1.setShowGrid(jchkShowGrid.isSelected());
            }
        });
    }
    
    
    public static void main(String[] args) {
        jTableDemo applet = new jTableDemo();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setTitle("J Table Demo Of properties and filter");
        frame.getContentPane().add(applet,BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
