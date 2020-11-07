import javax.swing.*;
import java.rmi.*;

import java.awt.event.*;

public class PatientClient {
    public static void main(String args[]) {
        try {
            String hospserverURL = "rmi://127.0.0.1/HOSPITAL_SERVER";
            PatIntf patObj = (PatIntf) Naming.lookup(hospserverURL);
            JFrame frame = new JFrame("Passport Renew");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTabbedPane tp = new JTabbedPane();
            tp.setBounds(0, 0, 400, 400);
            JPanel register = new JPanel();
            register.setLayout(null);

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setBounds(10, 20, 100, 25);
            register.add(nameLabel);
            JTextField nameText = new JTextField(20);
            nameText.setBounds(120, 20, 160, 25);
            register.add(nameText);

            JLabel ageLabel = new JLabel("Age");
            ageLabel.setBounds(10, 50, 100, 25);
            register.add(ageLabel);
            JTextField ageText = new JTextField(20);
            ageText.setBounds(120, 50, 160, 25);
            register.add(ageText);

            JLabel dobLabel = new JLabel("DOB");
            dobLabel.setBounds(10, 80, 100, 25);
            register.add(dobLabel);
            JTextField dobText = new JTextField(20);
            dobText.setBounds(120, 80, 160, 25);
            register.add(dobText);

            JLabel genderLabel = new JLabel("Gender");
            genderLabel.setBounds(10, 110, 100, 25);
            register.add(genderLabel);
            JRadioButton male = new JRadioButton("Male");
            male.setBounds(110, 110, 80, 30);
            JRadioButton female = new JRadioButton("Female");
            female.setBounds(210, 110, 80, 30);
            ButtonGroup bg = new ButtonGroup();
            bg.add(male);
            bg.add(female);
            register.add(male);
            register.add(female);

            JLabel htLabel = new JLabel("Year");
            htLabel.setBounds(10, 150, 100, 25);
            register.add(htLabel);
            JTextField htText = new JTextField(20);
            htText.setBounds(120, 150, 160, 25);
            register.add(htText);

            JLabel wtLabel = new JLabel("Month");
            wtLabel.setBounds(10, 180, 100, 25);
            register.add(wtLabel);
            JTextField wtText = new JTextField(20);
            wtText.setBounds(120, 180, 160, 25);
            register.add(wtText);

            JLabel bloodLabel = new JLabel("Blood Group");
            bloodLabel.setBounds(10, 210, 100, 25);
            register.add(bloodLabel);
            JTextField bloodText = new JTextField(20);
            bloodText.setBounds(120, 210, 160, 25);
            register.add(bloodText);

            JLabel area = new JLabel("Message will be displayed here!", SwingConstants.CENTER);
            area.setBounds(0, 250, 400, 25);
            register.add(area);

            JButton btn = new JButton("Register");
            btn.setBounds(140, 290, 100, 25);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String gen = "Male";
                    if (female.isSelected())
                        gen = "Female";
                    try {
                        int pid = patObj.register(nameText.getText(), ageText.getText(), dobText.getText(), gen,
                                Integer.parseInt(htText.getText()), Integer.parseInt(wtText.getText()),
                                bloodText.getText());
                        area.setText("Registered! Your Pid is " + pid + "\n");
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            });
            register.add(btn);
            tp.add("Register", register);

            JPanel search = new JPanel();
            search.setLayout(null);
            JLabel pidLabel = new JLabel("Pid");
            pidLabel.setBounds(10, 20, 50, 25);
            search.add(pidLabel);
            JTextField pidText = new JTextField(20);
            pidText.setBounds(70, 20, 150, 25);
            search.add(pidText);

            JButton searchBtn = new JButton("Search");
            searchBtn.setBounds(230, 20, 100, 25);

            JLabel record = new JLabel("Record will be displayed here!", SwingConstants.CENTER);
            record.setBounds(0, 50, 350, 300);
            search.add(record);
            searchBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {

                        Boolean ch = patObj.expiry(Integer.parseInt(pidText.getText()));
                        int fc = 0;
                        if (ch == false) {

                        } else {

                            fc = 1;
                        }

                        if (fc == 1) {
                            patObj.del(Integer.parseInt(pidText.getText()));

                        }
                        String temp = patObj.search(Integer.parseInt(pidText.getText()));
                        record.setText(temp);
                    } catch (Exception ex) {
                    }
                }
            });
            search.add(searchBtn);
            tp.add("Search", search);

            frame.add(tp);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}