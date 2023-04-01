package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JWindow {
        private JFrame defaultFrame;
        private JFrame insertFrame;
        private JFrame errorFrame;
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
                animalsInsert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean success;
                        success = insertNewAnimal();
                    }
                });

                JButton habitatsInsert = new JButton("INSERT");
                JButton workerInsert = new JButton("INSERT");
                JButton vendorsInsert = new JButton("INSERT");
                JButton zookeepersInsert = new JButton("INSERT");
                JButton vetsInsert = new JButton("INSERT");
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


        public boolean insertNewAnimal(){
            //Create a new pop-up window
            insertFrame = new JFrame();
            insertFrame.setTitle("Insert New Animal");
            insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            insertFrame.setSize(200, 500);

            insertFrame.setLocationRelativeTo(null);

            //Create all the text fields required for animals
            JPanel forInsert = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField a_id = new JTextField("Animal's ID (REQUIRED)"); //MUST BE ERROR CHECKED
            JTextField p_id = new JTextField("Place ID (REQUIRED)");
            JTextField name = new JTextField("Animal's name");
            JTextField species = new JTextField("Animal's species");
            JTextField genus = new JTextField("Animal's genus");

            //Create Apply button and create action listener
            JButton apply = new JButton("Apply Insert");
            apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        int a_idUserInput = Integer.parseInt(a_id.getText());
                        int p_idUserInput = Integer.parseInt(p_id.getText());
                        String nameInput = name.getText();
                        String speciesInput = species.getText();
                        String genusInput = genus.getText();

                        //THEN ACTUALLY INSERTS
                    }catch(Exception uhoh){
                        insertFrame.dispose();
                        errorFrame = new JFrame("Error");
                        JOptionPane.showMessageDialog(null, "You have entered the wrong kind of data or did not fill in a required field" +
                                "", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

            //Add all textfield and apply button to panel
            forInsert.add(a_id);
            forInsert.add(p_id);
            forInsert.add(name);
            forInsert.add(species);
            forInsert.add(genus);
            forInsert.add(apply);

            //Add panel to frame
            insertFrame.add(forInsert);

            this.insertFrame.setVisible(true);

            return false;
        }


        public void show() {
                this.defaultFrame.setVisible(true);
        }


}
