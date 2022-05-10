package aut.utcluj.isp.ex5.ui;

import aut.utcluj.isp.ex3.Equipment;
import aut.utcluj.isp.ex3.EquipmentController;
import aut.utcluj.isp.ex4.EquipmentHistoryDetails;
import aut.utcluj.isp.ex4.Operation;
import aut.utcluj.isp.ex5.EquipmentHistory;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.*;

public class MainUI {
    private final EquipmentController equipmentController;
    private final EquipmentHistory equipmentHistory;
    private JPanel rootPanel;
    private JPanel controlPanel;
    private JPanel tablePanel;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel returnPanel;
    private JPanel providePanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton returnButton;
    private JButton provideButton;
    private JButton addToTableButton;
    private JTextField addSerialField;
    private JTextField addNameField;
    private JButton removeEquipmentButton;
    private JTextField removeSerialField;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton returnEquipmentButton;
    private JButton provideButton1;
    private JTextField provideOwnerField;
    private JButton historyButton;
    private JButton equipmentsButton;
    private JTable table;
    private JButton viewButton;

    public MainUI() {

        equipmentController = new EquipmentController();
        equipmentHistory = new EquipmentHistory();
        createTableEquipments(equipmentController.getEquipments());
        tablePanel.setVisible(true);
        addPanel.setVisible(false);
        removePanel.setVisible(false);
        returnPanel.setVisible(false);
        providePanel.setVisible(false);



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(true);
                removePanel.setVisible(false);
                returnPanel.setVisible(false);
                providePanel.setVisible(false);
                createTableEquipments(equipmentController.getEquipments());
            }
        });



        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(false);
                removePanel.setVisible(true);
                returnPanel.setVisible(false);
                providePanel.setVisible(false);
                createTableEquipments(equipmentController.getEquipments());
            }
        });



        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(false);
                removePanel.setVisible(false);
                returnPanel.setVisible(true);
                providePanel.setVisible(false);
                populateComboBox(equipmentController.getEquipments());
                createTableEquipments(equipmentController.getEquipments());
            }
        });



        provideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(false);
                removePanel.setVisible(false);
                returnPanel.setVisible(false);
                providePanel.setVisible(true);
                populateComboBox(equipmentController.getEquipments());
                createTableEquipments(equipmentController.getEquipments());
            }
        });



        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(true);
                addPanel.setVisible(false);
                removePanel.setVisible(false);
                returnPanel.setVisible(false);
                providePanel.setVisible(false);
            }
        });



        addToTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the info
                String serial = addSerialField.getText();
                String name = addNameField.getText();

                if ((!Objects.equals(serial, "")) && (!Objects.equals(name, ""))) {
                    //add by serial 'n' name
                    for (Equipment each : equipmentController.getEquipments()) {
                        if (each.getSerialNumber().equals(serial)) {
                            clearFields();
                            JOptionPane.showMessageDialog(new JFrame(), "Serial must be unique");
                            return;
                        }
                    }
                    equipmentController.addEquipment(new Equipment(name, serial));
                    addPanel.setVisible(false);
                    createTableEquipments(equipmentController.getEquipments());
                    clearFields();
                } else {
                    //fields cannot be empty
                    JOptionPane.showMessageDialog(new JFrame(), "Fields cannot be empty");
                }
            }
        });



        removeEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serial = removeSerialField.getText();
                String owner;
                if (!Objects.equals(serial, "")) {
                    for (Equipment each : equipmentController.getEquipments()) {
                        if (each.getSerialNumber().equals(serial)) {
                            owner = each.getOwner();
                            if (!Objects.equals(owner, null))
                                equipmentHistory.addEquipmentHistory(owner, Operation.RETURN, LocalDateTime.now());
                            break;
                        }
                    }
                    equipmentController.removeEquipmentBySerialNumber(serial);
                    createTableEquipments(equipmentController.getEquipments());
                    removePanel.setVisible(false);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Serial cannot be empty");
                }
            }
        });



        returnEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Equipment equipment : equipmentController.getEquipments()) {
                    if (!Objects.equals(equipment.getOwner(), null))
                        comboBox1.addItem(equipment.getSerialNumber());
                }
                String serial = comboBox1.getSelectedItem().toString();
                if (comboBox1.getItemCount() != 0) {
                    for (int i = 0; i < equipmentController.getEquipments().size(); i++) {
                        if (equipmentController.getEquipments().get(i).getSerialNumber().equals(serial)) {
                            String owner = equipmentController.getEquipments().get(i).getOwner();
                            equipmentController.getEquipments().get(i).returnEquipmentToOffice();
                            equipmentHistory.addEquipmentHistory(owner, Operation.RETURN, LocalDateTime.now());
                            createTableHistory(equipmentHistory.getHistoryDetailsList());
                            returnPanel.setVisible(false);
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "tt");
                }
            }
        });



        provideButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serial = comboBox2.getSelectedItem().toString();
                if (comboBox2.getItemCount() != 0) {
                    String owner = provideOwnerField.getText();
                    if (!owner.equals("")) {
                        equipmentHistory.addEquipmentHistory(owner, Operation.PROVIDE, LocalDateTime.now());
                        for (int i = 0; i < equipmentController.getEquipments().size(); i++)
                            if (serial.equals(equipmentController.getEquipments().get(i).getSerialNumber())) {
                                equipmentController.getEquipments().get(i).provideEquipmentToUser(owner);
                                break;
                            }
                        providePanel.setVisible(false);
                        createTableHistory(equipmentHistory.getHistoryDetailsList());
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Field cannot be empty");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "tt");
                }
            }
        });



        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTableHistory(equipmentHistory.getHistoryDetailsList());
            }
        });



        equipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTableEquipments(equipmentController.getEquipments());
            }
        });
    }



    private void populateComboBox(List<Equipment> list) {
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        for (Equipment equipment : list) {
            if (!Objects.equals(equipment.getOwner(), null))
                comboBox1.addItem(equipment.getSerialNumber());
            else
                comboBox2.addItem(equipment.getSerialNumber());
        }
    }



    private void createTableEquipments(List<Equipment> equipments) {
        DefaultTableModel model = new DefaultTableModel(null,
                new String[]{"Serial", "Name", "Owner", "Taken"}
        );
        for (Equipment equipment : equipments) {
            model.addRow(new Object[]{equipment.getSerialNumber(), equipment.getName(), equipment.getOwner(), equipment.isTaken()});
        }
        table.setModel(model);
    }



    private void createTableHistory(List<EquipmentHistoryDetails> equipmentHistory) {
        DefaultTableModel model = new DefaultTableModel(null,
                new String[]{"Owner", "Operation", "Time"}
        );
        for (EquipmentHistoryDetails equipment : equipmentHistory) {
            model.addRow(new Object[]{equipment.getOwner(), equipment.getOperation(), equipment.getDate()});
        }
        table.setModel(model);
    }



    private void clearFields() {
        addSerialField.setText("");
        addNameField.setText("");
        removeSerialField.setText("");
        provideOwnerField.setText("");
    }



    public JPanel getRootPanel() {
        return rootPanel;
    }
}
