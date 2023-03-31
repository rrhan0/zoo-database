package UI;

import javax.swing.*;
import java.awt.*;

public class JWindow {
        private JFrame frame;
        public JWindow() {
                initialize();
                show();
        }

        public void initialize() {
                //frame creation
                frame = new JFrame();

                frame.setTitle("Zoo Database");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(800, 500);
                frame.setLocationRelativeTo(null);

                //Create ability to add tabs and buttons to those tabs
                JTabbedPane tabs = new JTabbedPane();

                //Initialize buttons and action listeners
                JButton placesInsert = new JButton("INSERT");
                JButton storageInsert = new JButton("INSERT");


                //Places Tab creation
                JPanel places = new JPanel();
                places.add(placesInsert);
                places.setLayout(new FlowLayout());
                places.setBackground(Color.DARK_GRAY);

                //Storage_Units Tab
                JPanel storageUnits = new JPanel();
                storageUnits.add(storageInsert);
                storageUnits.setLayout((new FlowLayout()));

                //Prepped_Food Tab
                JPanel preppedFood = new JPanel();
                preppedFood.setLayout(new FlowLayout());

                //Raw_Food_Orders Tab
                JPanel rawFoodOrders = new JPanel();
                rawFoodOrders.setLayout(new FlowLayout());

                //Computers Tab
                JPanel computers = new JPanel();
                computers.setLayout(new FlowLayout());

                //Animals Tab
                JPanel animals = new JPanel();
                animals.setLayout(new FlowLayout());

                //Habitats Tab
                JPanel habitats = new JPanel();
                habitats.setLayout(new FlowLayout());

                //Workers Tab
                JPanel workers = new JPanel();
                workers.setLayout(new FlowLayout());

                //Vendors Tab
                JPanel vendors = new JPanel();
                vendors.setLayout(new FlowLayout());

                //Zookeepers Tab
                JPanel zookeepers = new JPanel();
                zookeepers.setLayout(new FlowLayout());

                //Vets Tab
                JPanel vets = new JPanel();
                vets.setLayout(new FlowLayout());

                //Shops Tab
                JPanel shops = new JPanel();
                shops.setLayout(new FlowLayout());

                //Items Tab
                JPanel items = new JPanel();
                items.setLayout(new FlowLayout());


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
                frame.getContentPane().add(tabs);
        }

        public void insert(){
            /*
            Will use this method to
            - use an action listener to listen to insert buttons
             */
        }

        public void show() {
                this.frame.setVisible(true);
        }
}
