package org.example;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.example.collections.StorageBD;
import org.example.manipulationBD.BuilderBD;
import org.example.parserManipulation.ParserManipulation;
import org.example.collections.ReactorsManipulation;

import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kzsbv
 */

public class JFrameProgram extends javax.swing.JFrame {

    public static ParserManipulation pm = new ParserManipulation();
    public static ReactorsManipulation rm = new ReactorsManipulation();
    public static BuilderBD bd = new BuilderBD();
    private boolean firstLoad = true;
    public JFrameProgram() {
        initComponents();
        try {
            pm.createFiles();
        }catch (Exception e){
            JOptionPane.showMessageDialog (null, "Ошибка добавления файлов с расширениями для парсинга", "Oшибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jButtonOpenJFileChooserParser = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTableFuelConsumption = new javax.swing.JTable();
        jLabelParserUpdate = new javax.swing.JLabel();
        jButtonFileChooserBD = new javax.swing.JButton();
        jLabelCreationBD = new javax.swing.JLabel();
        jLabelUpdateBD = new javax.swing.JLabel();
        jButtonCreateBD = new javax.swing.JButton();
        jButtonDeleteBD = new javax.swing.JButton();
        jLabelDeleteBD = new javax.swing.JLabel();
        jLabelSumConsumption = new javax.swing.JLabel();
        jButtonAggragateCountry = new javax.swing.JButton();
        jButtonAggragateCompany = new javax.swing.JButton();
        jButtonAggragateRegion = new javax.swing.JButton();
        jButtonGetDataBD = new javax.swing.JButton();
        jLabelGetDataBD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonOpenJFileChooserParser.setText("Выбрать файл для парсера");
        jButtonOpenJFileChooserParser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenJFileChooserParserActionPerformed(evt);
            }
        });

        jTableFuelConsumption.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {},
                        {},
                },
                new String [] {

                }
        ));
        jTableFuelConsumption.setEnabled(false);
        jTableFuelConsumption.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(jTableFuelConsumption);

        jLabelParserUpdate.setText("Файл не загружен");
        jLabelParserUpdate.setEnabled(false);

        jButtonFileChooserBD.setText("Выбрать файл для загрузки в БД");
        jButtonFileChooserBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileChooserBDActionPerformed(evt);
            }
        });

        jLabelCreationBD.setText(bd.areTablesExist());
        jLabelCreationBD.setEnabled(false);

        jLabelUpdateBD.setText(bd.areDataExist());
        jLabelUpdateBD.setEnabled(false);

        jButtonCreateBD.setText("Создать БД");
        jButtonCreateBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateBDActionPerformed(evt);
            }
        });

        jButtonDeleteBD.setText("Удалить БД");
        jLabelDeleteBD.setEnabled(false);
        jButtonDeleteBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteBDActionPerformed(evt);
            }
        });

        jLabelSumConsumption.setText("Расчитать суммарное потребление топлива по:");
        jLabelSumConsumption.setEnabled(false);

        jButtonAggragateCountry.setText("Странам");
        jButtonAggragateCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggragateCountryActionPerformed(evt);
            }
        });

        jButtonAggragateCompany.setText("Компаниям");
        jButtonAggragateCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggragateCompanyActionPerformed(evt);
            }
        });

        jButtonAggragateRegion.setText("Регионам");
        jButtonAggragateRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggragateRegionActionPerformed(evt);
            }
        });

        jButtonGetDataBD.setText("Получить данные из БД");
        jButtonGetDataBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetDataBDActionPerformed(evt);
            }
        });

        jLabelGetDataBD.setText("Данные не получены");
        jLabelGetDataBD.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelSumConsumption)
                                        .addComponent(jButtonAggragateCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButtonAggragateCompany, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(jButtonAggragateRegion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jButtonOpenJFileChooserParser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButtonFileChooserBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButtonCreateBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButtonDeleteBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jButtonGetDataBD, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(53, 53, 53)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelGetDataBD)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabelParserUpdate)
                                                                .addComponent(jLabelCreationBD)
                                                                .addComponent(jLabelUpdateBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabelDeleteBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonOpenJFileChooserParser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelParserUpdate))
                                                .addGap(41, 41, 41)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabelCreationBD)
                                                        .addComponent(jButtonCreateBD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButtonFileChooserBD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelUpdateBD))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(123, 123, 123)
                                                                .addComponent(jLabelDeleteBD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(74, 74, 74))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jButtonGetDataBD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabelGetDataBD))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButtonDeleteBD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(59, 59, 59)))
                                                .addComponent(jLabelSumConsumption)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonAggragateCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jButtonAggragateCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonAggragateRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void jButtonOpenJFileChooserParserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenJFileChooserParserActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите файл");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                rm.setReactorCollection(pm.importData(String.valueOf(file)));
                if(rm.getReactorCollection().getReactors().isEmpty()) throw new Exception();
                jLabelParserUpdate.setText(rm.areDataReactorGet());
            }catch (Exception r){
                JOptionPane.showMessageDialog (null, "Ошибка чтения файла для парсинга", "Oшибка", JOptionPane.ERROR_MESSAGE);
                jLabelParserUpdate.setText("Файл с реакторами  не загружен");
            }
      }
    }//GEN-LAST:event_jButtonOpenJFileChooserParserActionPerformed

    private void jButtonFileChooserBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileChooserBDActionPerformed
        if(jLabelCreationBD.getText().equals("БД не создана")) JOptionPane.showMessageDialog(null, "БД не создана!", "Oшибка", JOptionPane.ERROR_MESSAGE);
        else {
            if(jLabelUpdateBD.getText().equals("Данные загружены в БД")) JOptionPane.showMessageDialog(null, "Данные уже загружены в БД!", "Oшибка", JOptionPane.ERROR_MESSAGE);
            else {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Выберите файл для БД");
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = chooser.getSelectedFile();
                        bd.fillBD(String.valueOf(file));
                        jLabelUpdateBD.setText(bd.areDataExist());

                    } catch (Exception r) {
                        JOptionPane.showMessageDialog(null, "Ошибка чтения файла для заполнения БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
                        jLabelUpdateBD.setText("Данные не загружены в БД");
                    }
            }
        }

        }
    }//GEN-LAST:event_jButtonFileChooserBDActionPerformed

    private void jButtonCreateBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateBDActionPerformed
       if(jLabelCreationBD.getText().equals("БД создана")) JOptionPane.showMessageDialog(null, "БД уже создана!", "Oшибка", JOptionPane.ERROR_MESSAGE);
       else {
           try {
               bd.createBD();
               jLabelCreationBD.setText(bd.areTablesExist());
               jLabelDeleteBD.setText("");
           } catch (Exception r) {
               JOptionPane.showMessageDialog(null, "Ошибка создания БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
               jLabelCreationBD.setText("БД не создана");
           }
       }

    }//GEN-LAST:event_jButtonCreateBDActionPerformed

    private void jButtonDeleteBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteBDActionPerformed
        if(jLabelCreationBD.getText().equals("БД не создана"))JOptionPane.showMessageDialog(null, "Ошибка: БД не создана", "Oшибка", JOptionPane.ERROR_MESSAGE);
        else {
            try {
                bd.deleteBD();
                rm.setStorageBDInitial(new StorageBD());
                jLabelDeleteBD.setText("БД удалена");
                jLabelCreationBD.setText(bd.areTablesExist());
                jLabelUpdateBD.setText(bd.areDataExist());
                jLabelGetDataBD.setText("Данные не получены");
            } catch (Exception r) {
                JOptionPane.showMessageDialog(null, "Ошибка удаления БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
                jLabelDeleteBD.setText("БД не удалена");
            }
        }
    }//GEN-LAST:event_jButtonDeleteBDActionPerformed
    private void jButtonGetDataBDActionPerformed(java.awt.event.ActionEvent evt) {
        if(jLabelUpdateBD.getText().equals("Данные не загружены в БД")) JOptionPane.showMessageDialog(null, "Ошибка: данные не загружены в БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
        else {
            try {
                rm.setStorageBDInitial(bd.getDataFromBD());
                jLabelGetDataBD.setText(rm.areDataGet());
                this.firstLoad = true;
            } catch (Exception r) {
                JOptionPane.showMessageDialog(null, "Ошибка получения данных из БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
                jLabelGetDataBD.setText("Данные не получены");
            }
        }
    }

    public void prepareInfo(){
        if(firstLoad) {
            rm.filterUnitsInOperation();
            rm.addInfo2Units();
            rm.addFuelConsumption();
            this.firstLoad = false;
        }
    }

    private void jButtonAggragateCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggragateCountryActionPerformed
        if(jLabelGetDataBD.getText().equals("Данные получены")&&jLabelParserUpdate.getText().equals("Файл с реакторами загружен")){
            prepareInfo();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Страна");
            model.addColumn("Объем ежегодного потребления, т.");

            rm.aggregateCountry().forEach((key, value) -> {
                model.addRow(new Object[]{key, value});
            });
            jTableFuelConsumption.setModel(model);
        }else JOptionPane.showMessageDialog(null, "Не все данные загружены", "Oшибка", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_jButtonAggragateCountryActionPerformed

    private void jButtonAggragateCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggragateCompanyActionPerformed
        if(jLabelGetDataBD.getText().equals("Данные получены")&&jLabelParserUpdate.getText().equals("Файл с реакторами загружен")){
            prepareInfo();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Компания");
            model.addColumn("Объем ежегодного потребления, т.");

            rm.aggregateCompany().forEach((key, value) -> {
                model.addRow(new Object[]{key, value});
            });
            jTableFuelConsumption.setModel(model);
        }else JOptionPane.showMessageDialog(null, "Не все данные загружены", "Oшибка", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_jButtonAggragateCompanyActionPerformed

    private void jButtonAggragateRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggragateRegionActionPerformed
        if(jLabelGetDataBD.getText().equals("Данные получены")&&jLabelParserUpdate.getText().equals("Файл с реакторами загружен")){
            prepareInfo();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Регион");
            model.addColumn("Объем ежегодного потребления, т.");

            rm.aggregateRegion().forEach((key, value) -> {
                model.addRow(new Object[]{key, value});
            });
            jTableFuelConsumption.setModel(model);
        }else JOptionPane.showMessageDialog(null, "Не все данные загружены", "Oшибка", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_jButtonAggragateRegionActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify
    private javax.swing.JButton jButtonAggragateCompany;
    private javax.swing.JButton jButtonAggragateCountry;
    private javax.swing.JButton jButtonAggragateRegion;
    private javax.swing.JButton jButtonCreateBD;
    private javax.swing.JButton jButtonDeleteBD;
    private javax.swing.JButton jButtonFileChooserBD;
    private javax.swing.JButton jButtonGetDataBD;
    private javax.swing.JButton jButtonOpenJFileChooserParser;
    private javax.swing.JLabel jLabelCreationBD;
    private javax.swing.JLabel jLabelDeleteBD;
    private javax.swing.JLabel jLabelGetDataBD;
    private javax.swing.JLabel jLabelParserUpdate;
    private javax.swing.JLabel jLabelSumConsumption;
    private javax.swing.JLabel jLabelUpdateBD;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableFuelConsumption;
    // End of variables declaration
}
