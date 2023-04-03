package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JWindow {
        private JFrame defaultFrame;
        private JFrame insertFrame;
        private JFrame successFrame;
        public JWindow() {
                initialize();
                show();
        }

        public void initialize() {
                //frame creation
                defaultFrame = new JFrame();

                defaultFrame.setTitle("Zoo Database");
                defaultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                defaultFrame.setSize(800, 500);
                defaultFrame.setLocationRelativeTo(null);

                //Create ability to add tabs and buttons to those tabs
                JTabbedPane tabs = new JTabbedPane();


                //Initialize buttons and action listeners
                JButton storageInsert = new JButton("INSERT");
                JButton preppedFoodInsert = new JButton("INSERT");
                JButton rawFoodOrdersInsert = new JButton("INSERT");
                JButton computersInsert = new JButton("INSERT");

                //Animal's insert button will actually do something :D
                JButton animalsInsert = new JButton("INSERT");
                JButton habitatsInsert = new JButton("INSERT");
                JButton workerInsert = new JButton("INSERT");
                JButton vendorsInsert = new JButton("INSERT");
                JButton zookeepersInsert = new JButton("INSERT");
                JButton vetsInsert = new JButton("INSERT");
                vetsInsert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertNewVet();
                    }
                });

                JButton shopsInsert = new JButton("INSERT");
                JButton itemsInsert = new JButton("INSERT");


                //Places Tab creation
                JPanel places = new JPanel();
                places.setLayout(new FlowLayout());
                places.setBackground(Color.DARK_GRAY);

                //Storage_Units Tab
                JPanel storageUnits = new JPanel();
                storageUnits.add(storageInsert);
                storageUnits.setLayout((new FlowLayout()));
                storageUnits.setBackground(Color.DARK_GRAY);

                //Prepped_Food Tab
                JPanel preppedFood = new JPanel();
                preppedFood.add(preppedFoodInsert);
                preppedFood.setLayout(new FlowLayout());
                preppedFood.setBackground(Color.DARK_GRAY);

                //Raw_Food_Orders Tab
                JPanel rawFoodOrders = new JPanel();
                rawFoodOrders.add(rawFoodOrdersInsert);
                rawFoodOrders.setLayout(new FlowLayout());
                rawFoodOrders.setBackground(Color.DARK_GRAY);

                //Computers Tab
                JPanel computers = new JPanel();
                computers.add(computersInsert);
                computers.setLayout(new FlowLayout());
                computers.setBackground(Color.DARK_GRAY);

                //Animals Tab
                JPanel animals = new JPanel();
                animals.add(animalsInsert);
                animals.setLayout(new FlowLayout());
                animals.setBackground(Color.DARK_GRAY);

                //Habitats Tab
                JPanel habitats = new JPanel();
                habitats.add(habitatsInsert);
                habitats.setLayout(new FlowLayout());
                habitats.setBackground(Color.DARK_GRAY);

                //Workers Tab
                JPanel workers = new JPanel();
                workers.add(workerInsert);
                workers.setLayout(new FlowLayout());
                workers.setBackground(Color.DARK_GRAY);

                //Vendors Tab
                JPanel vendors = new JPanel();
                vendors.add(vendorsInsert);
                vendors.setLayout(new FlowLayout());
                vendors.setBackground(Color.DARK_GRAY);

                //Zookeepers Tab
                JPanel zookeepers = new JPanel();
                zookeepers.add(zookeepersInsert);
                zookeepers.setLayout(new FlowLayout());
                zookeepers.setBackground(Color.DARK_GRAY);

                //Vets Tab
                JPanel vets = new JPanel();
                vets.add(vetsInsert);
                vets.setLayout(new FlowLayout());
                vets.setBackground(Color.DARK_GRAY);

                //Shops Tab
                JPanel shops = new JPanel();
                shops.add(shopsInsert);
                shops.setLayout(new FlowLayout());
                shops.setBackground(Color.DARK_GRAY);

                //Items Tab
                JPanel items = new JPanel();
                items.add(itemsInsert);
                items.setLayout(new FlowLayout());
                items.setBackground(Color.DARK_GRAY);


                //Add tabs to frame
                tabs.addTab("Places", places);
                tabs.addTab("Storage Units", storageUnits);
                tabs.addTab("Prepped Food", preppedFood);
                tabs.addTab("Raw Food Orders", rawFoodOrders);
                tabs.addTab("Computers", computers);
                tabs.addTab("Animals", animals);
                tabs.addTab("Habitats", habitats);
                tabs.addTab("Workers", workers);
                tabs.addTab("Vendors", vendors);
                tabs.addTab("Zookeepers", zookeepers);
                tabs.addTab("Veterinarians", vets);
                tabs.addTab("Shops", shops);
                tabs.addTab("Items", items);
                defaultFrame.getContentPane().add(tabs);
        }


        public void insertNewVet(){
            //Create a new pop-up window
            insertFrame = new JFrame();
            insertFrame.setTitle("Insert New Veterinarian - Insert New Worker");
            insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            insertFrame.setSize(400, 500);
            
            insertFrame.setLocationRelativeTo(null);

            //Create all the text fields required for animals
            JPanel forInsert = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField w_id = new JTextField("Worker's ID (REQUIRED)", 20); //REQUIRED
            JTextField pay_rate = new JTextField("Pay Rate (REQUIRED)"); //NOT NULL
            JTextField name = new JTextField("Worker's Name (REQUIRED)", 20); //NOT NULL
            JTextField email = new JTextField("Worker's email"); //UNIQUE
            JTextField phone = new JTextField("Worker's phone"); //UNIQUE
            JTextField address = new JTextField("Worker's Address (REQUIRED)"); //NOT NULL
            JTextField specialization = new JTextField("Veterinarian's Specialization");

            //Create Apply button and create action listener
            JButton apply = new JButton("Apply Insert");
            apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String w_idUserInput = w_id.getText(); //REQUIRED
                        float payRate_UserInput = Float.parseFloat(pay_rate.getText()); //NOT NULL
                        String nameInput = name.getText(); //NOT NULL
                        String emailInput = email.getText(); //UNIQUE
                        String phoneInput = phone.getText(); //UNIQUE
                        String addressInput = address.getText(); //NOT NULL
                        String specializationInput = specialization.getText();


                        if(w_idUserInput.equals("") || nameInput.equals("") || addressInput.equals("")){
                            throw new Exception();
                        }

                        //THEN CREATE ACTUAL VET OBJECT - call insertVet from DBCH
                        //Veterinarian newVet = new Veterinarian(w_idUserInput, nameInput, payRate_UserInput, addressInput, emailInput, phoneInput, specializationInput);
                        insertFrame.dispose();

                        //SHOW SUCCESS
                        showSuccessFrame();


                    }catch(Exception uhoh){
                        insertFrame.dispose();
                        JOptionPane.showMessageDialog(null, "You have entered a field incorrectly or did not fill in a required field" +
                                "", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                }
            });

            //Add all textfield and apply button to panel
            forInsert.add(w_id);
            forInsert.add(pay_rate);
            forInsert.add(name);
            forInsert.add(email);
            forInsert.add(phone);
            forInsert.add(address);
            forInsert.add(specialization);
            forInsert.add(apply);

            //Add panel to frame
            insertFrame.add(forInsert);

            this.insertFrame.setVisible(true);

        }

        public void showSuccessFrame(){
            successFrame = new JFrame();
            successFrame.setTitle("Success!");
            successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            successFrame.setSize(300, 300);
            successFrame.setLocationRelativeTo(null);
            this.successFrame.setVisible(true);
        }

        public void show() {
                this.defaultFrame.setVisible(true);
        }


}
