package UI;

import database.DatabaseConnectionHandler;
import model.Veterinarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JWindow {
        private JFrame defaultFrame;
        private JFrame insertFrame;
        private JFrame deleteFrame;
        private JFrame updateFrame;
        private JFrame successFrame;
        //Create new DBHandler object for modification of Database
        private DatabaseConnectionHandler dbHandler;
        public JWindow() {
                initialize();
                show();
        }

        public void initialize() {
                this.dbHandler = new DatabaseConnectionHandler();
               //dbHandler.login(System.getenv("USER"), System.getenv("PASSWORD"));
                dbHandler.login(System.getenv("ora_arl20"), System.getenv("a43629526"));
                //frame creation
                defaultFrame = new JFrame();

                defaultFrame.setTitle("Zoo Database");
                defaultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                defaultFrame.setSize(800, 500);
                defaultFrame.setLocationRelativeTo(null);

                //Create ability to add tabs and buttons to those tabs
                JTabbedPane tabs = new JTabbedPane();


                //Initialize insert buttons and action listeners
                JButton storageInsert = new JButton("INSERT");
                JButton preppedFoodInsert = new JButton("INSERT");
                JButton rawFoodOrdersInsert = new JButton("INSERT");
                JButton computersInsert = new JButton("INSERT");
                JButton animalsInsert = new JButton("INSERT");
                JButton habitatsInsert = new JButton("INSERT");
                JButton workerInsert = new JButton("INSERT");
                JButton vendorsInsert = new JButton("INSERT");
                JButton zookeepersInsert = new JButton("INSERT");
                JButton shopsInsert = new JButton("INSERT");
                JButton itemsInsert = new JButton("INSERT");

                //vet's insert button will actually do something :D
                JButton vetsInsert = new JButton("INSERT");
                vetsInsert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertNewVet();
                    }
                });

                //Initialize DELETE buttons and action listeners
                JButton storageDelete = new JButton("DELETE");
                JButton preppedFoodDelete = new JButton("DELETE");
                JButton rawFoodOrdersDelete = new JButton("DELETE");
                JButton computersDelete = new JButton("DELETE");
                JButton habitatsDelete = new JButton("DELETE");
                JButton workerDelete = new JButton("DELETE");
                JButton vendorsDelete = new JButton("DELETE");
                JButton zookeepersDelete = new JButton("DELETE");
                JButton vetsDelete = new JButton("DELETE");
                JButton shopsDelete = new JButton("DELETE");
                JButton itemsDelete = new JButton("DELETE");

                // animal's delete button will actually do something :D
                JButton animalsDelete = new JButton("DELETE");
                animalsDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteAnimal();
                    }
                });

                //Initialize UPDATE buttons and action listeners
                JButton storageUpdate = new JButton("Update");
                JButton preppedFoodUpdate = new JButton("Update");
                JButton rawFoodOrdersUpdate = new JButton("Update");
                JButton computersUpdate = new JButton("Update");
                JButton animalsUpdate = new JButton("Update");
                JButton habitatsUpdate = new JButton("Update");
                JButton workersUpdate = new JButton("Update");
                JButton zookeepersUpdate = new JButton("Update");
                JButton vetsUpdate = new JButton("Update");
                JButton vendorsUpdate = new JButton("Update");
                JButton shopsUpdate = new JButton("Update");
                JButton itemsUpdate = new JButton("Update");

                //Workers update button will actually do something :D
                workersUpdate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateWorkers();
                    }
                });

                //Places Tab creation
                JPanel places = new JPanel();
                places.setLayout(new FlowLayout());
                places.setBackground(Color.DARK_GRAY);

                //Storage_Units Tab
                JPanel storageUnits = new JPanel();
                storageUnits.add(storageInsert);
                storageUnits.add(storageDelete);
                storageUnits.add(storageUpdate);
                storageUnits.setLayout((new FlowLayout()));
                storageUnits.setBackground(Color.DARK_GRAY);

                //Prepped_Food Tab
                JPanel preppedFood = new JPanel();
                preppedFood.add(preppedFoodInsert);
                preppedFood.add(preppedFoodDelete);
                preppedFood.add(preppedFoodUpdate);
                preppedFood.setLayout(new FlowLayout());
                preppedFood.setBackground(Color.DARK_GRAY);

                //Raw_Food_Orders Tab
                JPanel rawFoodOrders = new JPanel();
                rawFoodOrders.add(rawFoodOrdersInsert);
                rawFoodOrders.add(rawFoodOrdersDelete);
                rawFoodOrders.add(rawFoodOrdersUpdate);
                rawFoodOrders.setLayout(new FlowLayout());
                rawFoodOrders.setBackground(Color.DARK_GRAY);

                //Computers Tab
                JPanel computers = new JPanel();
                computers.add(computersInsert);
                computers.add(computersDelete);
                computers.add(computersUpdate);
                computers.setLayout(new FlowLayout());
                computers.setBackground(Color.DARK_GRAY);

                //Animals Tab
                JPanel animals = new JPanel();
                animals.add(animalsInsert);
                animals.add(animalsDelete);
                animals.add(animalsUpdate);
                animals.setLayout(new FlowLayout());
                animals.setBackground(Color.DARK_GRAY);

                //Habitats Tab
                JPanel habitats = new JPanel();
                habitats.add(habitatsInsert);
                habitats.add(habitatsDelete);
                habitats.add(habitatsUpdate);
                habitats.setLayout(new FlowLayout());
                habitats.setBackground(Color.DARK_GRAY);

                //Workers Tab
                JPanel workers = new JPanel();
                workers.add(workerInsert);
                workers.add(workerDelete);
                workers.add(workersUpdate);
                workers.setLayout(new FlowLayout());
                workers.setBackground(Color.DARK_GRAY);

                //Vendors Tab
                JPanel vendors = new JPanel();
                vendors.add(vendorsInsert);
                vendors.add(vendorsDelete);
                vendors.add(workersUpdate);
                vendors.setLayout(new FlowLayout());
                vendors.setBackground(Color.DARK_GRAY);

                //Zookeepers Tab
                JPanel zookeepers = new JPanel();
                zookeepers.add(zookeepersInsert);
                zookeepers.add(zookeepersDelete);
                zookeepers.add(zookeepersUpdate);
                zookeepers.setLayout(new FlowLayout());
                zookeepers.setBackground(Color.DARK_GRAY);

                //Vets Tab
                JPanel vets = new JPanel();
                vets.add(vetsInsert);
                vets.add(vetsDelete);
                vets.add(vetsUpdate);
                vets.setLayout(new FlowLayout());
                vets.setBackground(Color.DARK_GRAY);

                //Shops Tab
                JPanel shops = new JPanel();
                shops.add(shopsInsert);
                shops.add(shopsDelete);
                shops.add(shopsUpdate);
                shops.setLayout(new FlowLayout());
                shops.setBackground(Color.DARK_GRAY);

                //Items Tab
                JPanel items = new JPanel();
                items.add(itemsInsert);
                items.add(itemsDelete);
                items.add(itemsUpdate);
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

            //Create all the text fields required for vets
            JPanel forInsert = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField w_id = new JTextField("Worker's ID (REQUIRED)", 20); //REQUIRED
            JTextField pay_rate = new JTextField("Pay Rate (REQUIRED)"); //NOT NULL
            JTextField name = new JTextField("Worker's Name (REQUIRED)", 20); //NOT NULL
            JTextField email = new JTextField("Worker's email"); //UNIQUE
            JTextField phone = new JTextField("Worker's phone", 13); //UNIQUE
            JTextField address = new JTextField("Worker's Address (REQUIRED)", 30); //NOT NULL
            JTextField specialization = new JTextField("Veterinarian's Specialization", 20);

            //Create Apply button and create action listener
            JButton applyInsert = new JButton("Apply Insert");
            applyInsert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String w_idUserInput = w_id.getText(); //REQUIRED
                        String nameInput = name.getText(); //NOT NULL
                        float payRate_UserInput = Float.parseFloat(pay_rate.getText()); //NOT NULL
                        String addressInput = address.getText(); //NOT NULL
                        String emailInput = email.getText(); //UNIQUE
                        String phoneInput = phone.getText(); //UNIQUE
                        String specializationInput = specialization.getText();


                        if(w_idUserInput.equals("") || nameInput.equals("") || addressInput.equals("") || emailInput.equals("") || phoneInput.equals("")){
                            throw new Exception();
                        }

                        //THEN CREATE ACTUAL VET OBJECT AND INSERT IT

                        dbHandler.insertVeterinarian(new Veterinarian(w_idUserInput, nameInput, payRate_UserInput, addressInput, emailInput, phoneInput, specializationInput));
                        insertFrame.dispose();

                        //SHOW SUCCESS
                        showSuccessFrame();


                    }catch(Exception uhoh){
                        insertFrame.dispose();
                        JOptionPane.showMessageDialog(null, "You have entered a field incorrectly, did not fill in a required field," +
                                "or attempted to insert a duplicate", "Error", JOptionPane.ERROR_MESSAGE);

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
            forInsert.add(applyInsert);

            //Add panel to frame
            insertFrame.add(forInsert);

            this.insertFrame.setVisible(true);

        }

        public void deleteAnimal(){
            //create new frame
            deleteFrame = new JFrame();
            deleteFrame.setTitle("Delete Existing Animal - Cascades onto PreppedFood");
            deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            deleteFrame.setSize(400, 500);
            deleteFrame.setLocationRelativeTo(null);

            //create all text fields required for animals
            JPanel forDelete = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField a_id = new JTextField("Animal's ID (REQUIRED)", 20); //REQUIRED

            //Create Apply button and create action listener
            JButton applyDelete = new JButton("Apply Delete");
            applyDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String a_idInput = a_id.getText(); //REQUIRED

                        if(a_idInput.equals("")) {
                            throw new Exception();
                        }

                        //delete tuple from database
                        dbHandler.deleteAnimal(a_idInput);

                        //show success
                        deleteFrame.dispose();
                        showSuccessFrame();
                    }catch (Exception nono) {
                        deleteFrame.dispose();
                        JOptionPane.showMessageDialog(null, "You did not fill in a required field or entered a non-existing" +
                                " animal ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //add textfields and applyDelete button
            forDelete.add(a_id);
            forDelete.add(applyDelete);

            //add panel to frame
            deleteFrame.add(forDelete);

            //set frame as visible
            this.deleteFrame.setVisible(true);
    }

        public void updateWorkers(){
            //create new frame
            updateFrame = new JFrame();
            updateFrame.setTitle("Update Existing Worker");
            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateFrame.setSize(400, 500);
            updateFrame.setLocationRelativeTo(null);

            //TODO create all text fields required for workers
            JPanel forUpdate = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField w_id = new JTextField("Worker's ID (REQUIRED)", 20); //REQUIRED
            JTextField name = new JTextField("Worker's Name", 20); //NOT NULL
            JTextField payrate = new JTextField("Worker's Pay rate"); //NOT NULL
            JTextField email = new JTextField("Worker's Email", 30); //NOT NULL
            JTextField phone = new JTextField("Worker's Phone Number", 13); //NOT NULL
            JTextField address = new JTextField("Worker's Address", 30); //NOT NULL

            //Button to apply the update
            JButton applyUpdate = new JButton("Apply Update");

            try{
                String widInput = w_id.getText();
                String nameInput = name.getText();
                float payrateInput = Float.parseFloat(payrate.getText());
                String emailInput = email.getText();
                String phoneInput = phone.getText();
                String addressInput = address.getText();

                if(widInput.equals("") || nameInput.equals("") || emailInput.equals("") || phoneInput.equals("") || addressInput.equals("")){
                    throw new Error();
                }

                //actually updates here

                showSuccessFrame();

            }catch(Error e){
                deleteFrame.dispose();
                JOptionPane.showMessageDialog(null, "You entered the wrong type of input, did not fill in a required field, entered a duplicate, or" +
                        " entered a WorkerID that does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
