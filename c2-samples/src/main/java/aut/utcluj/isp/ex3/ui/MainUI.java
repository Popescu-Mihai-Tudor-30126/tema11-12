package aut.utcluj.isp.ex3.ui;

import aut.utcluj.isp.ex3.Equipment;
import aut.utcluj.isp.ex3.EquipmentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainUI {
    private JPanel rootPanel;
    private JPanel tablePanel;
    private JPanel mainControls;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel searchPanel;
    private JButton View;
    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTable table1;
    private JTextField ownerField;
    private JTextField nameField;
    private JTextField serialField;
    private JButton addToTableButton;
    private JTextField serialRemoveField;
    private JButton removeFromTableButton;
    private JButton viewButton;
    private JTextField searchOwnerField;
    private JButton searchOwnerButton;
    private final EquipmentController equipmentController;


    public MainUI() {

        tablePanel.setVisible(true);
        addPanel.setVisible(false);
        removePanel.setVisible(false);
        searchPanel.setVisible(false);

        equipmentController = new EquipmentController();
        createTable(equipmentController.getEquipments());
        clearFields();

        //opens view panel (needed for user in order to redisplay the table after using the Search function)
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable(equipmentController.getEquipments());
                tablePanel.setVisible(true);
                addPanel.setVisible(false);
                removePanel.setVisible(false);
                searchPanel.setVisible(false);
            }
        });

        //opens add panel
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(true);
                removePanel.setVisible(false);
                searchPanel.setVisible(false);
            }
        });

        //opens remove panel
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(false);
                removePanel.setVisible(true);
                searchPanel.setVisible(false);
            }
        });

        //opens add panel
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(false);
                removePanel.setVisible(false);
                searchPanel.setVisible(true);
            }
        });

        //adding to the table
        addToTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the info
                String serial = serialField.getText();
                String name = nameField.getText();
                String owner = ownerField.getText();

                if(!Objects.equals(serial, "")){
                    //add by serial 'n' name
                    if((!Objects.equals(name, ""))&&(Objects.equals(owner, ""))){
                        equipmentController.addEquipment(new Equipment(name,serial));
                        createTable(equipmentController.getEquipments());
                        addPanel.setVisible(false);
                        clearFields();
                        return;
                    }
                    //add by serial, name 'n' owner
                    if((!Objects.equals(name, ""))&&(!Objects.equals(owner, ""))){
                        equipmentController.addEquipment(new Equipment(name,serial,owner));
                        createTable(equipmentController.getEquipments());
                        addPanel.setVisible(false);
                        clearFields();
                        return;
                    }
                    //add by serial
                    if(Objects.equals(name, "")){
                        equipmentController.addEquipment(new Equipment(serial));
                        createTable(equipmentController.getEquipments());
                        addPanel.setVisible(false);
                        clearFields();
                    }
                }
                else {
                    //serial cannot be empty
                    JOptionPane.showMessageDialog(new JFrame(),"Serial cannot be empty");
                    clearFields();
                }
            }
        });

        //removing from table
        removeFromTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serial = serialRemoveField.getText();
                if(!Objects.equals(serial, "")) {
                    equipmentController.removeEquipmentBySerialNumber(serial);
                    createTable(equipmentController.getEquipments());
                    removePanel.setVisible(false);
                    clearFields();
                }
                else
                    JOptionPane.showMessageDialog(new JFrame(),"Serial cannot be empty");
                clearFields();
            }
        });


        searchOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String owner = searchOwnerField.getText();
                if(!owner.equals("")){
                    Map<String, List<Equipment>> map = equipmentController.getEquipmentsGroupedByOwner();
                    List<Equipment> list = map.get(owner);
                    if(list!=null) {
                        createTable(list);
                    }
                    else
                    {
                        clearFields();
                        JOptionPane.showMessageDialog(new JFrame(),"Name not found");
                    }
                }
                else
                {
                    clearFields();
                    JOptionPane.showMessageDialog(new JFrame(),"Name cannot be empty");
                }
            }
        });
    }

    //used for populating the table
    private void createTable(List<Equipment> equipments) {
        DefaultTableModel model = new DefaultTableModel(null,
                new String[]{"Serial", "Name", "Owner", "Taken"}
        );

        for(Equipment equipment:equipments)
        {
            model.addRow(new Object[]{equipment.getSerialNumber(),equipment.getName(),equipment.getOwner(),equipment.isTaken()});
        }
        table1.setModel(model);
    }

    //clear all JTextFields
    private void clearFields(){
        ownerField.setText("");
        serialField.setText("");
        nameField.setText("");
        serialRemoveField.setText("");
        searchOwnerField.setText("");
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }
}
