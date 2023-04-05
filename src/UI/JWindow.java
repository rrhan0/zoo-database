package UI;

import database.DatabaseConnectionHandler;
import exceptions.NotExists;
import model.Computer;
import model.Veterinarian;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class JWindow {
        private JFrame defaultFrame;
        private JFrame insertFrame;
        private JFrame deleteFrame;
        private JFrame updateFrame;
        private JFrame updateButtons;
        private JFrame joinFrame;
        private JFrame successFrame;
        private JFrame viewFrame;
        private JFrame checkBoxFrame;
        private JFrame projectionFrame;
        private JFrame userSelectionFrame;
        private JFrame showSelectionFrame;
        //Used to determine checked boxes for projection of tables
        private boolean [] projectionArray = {false, false, false, false, false, false, false};

        //Create new DBHandler object for modification of Database
        private DatabaseConnectionHandler dbHandler;
        public JWindow() {
                initialize();
                show();
        }

        public void initialize() {
                this.dbHandler = new DatabaseConnectionHandler();
                dbHandler.login(System.getenv("USER"), System.getenv("PASSWORD"));
//                dbHandler.login(System.getenv("ora_arl20"), System.getenv("a43629526"));
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
                JButton assigned_toInsert = new JButton("INSERT");
                JButton cohabitates_withInsert = new JButton("INSERT");
                JButton feedsInsert = new JButton("INSERT");
                JButton located_atInsert = new JButton("INSERT");
                JButton made_fromInsert = new JButton("INSERT");
                JButton maintains_healthInsert = new JButton("INSERT");
                JButton stored_atInsert = new JButton("INSERT");
                JButton works_atInsert = new JButton("INSERT");

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
                JButton assigned_toDelete = new JButton("DELETE");
                JButton cohabitates_withDelete = new JButton("DELETE");
                JButton feedsDelete = new JButton("DELETE");
                JButton located_atDelete = new JButton("DELETE");
                JButton made_fromDelete = new JButton("DELETE");
                JButton maintains_healthDelete = new JButton("DELETE");
                JButton stored_atDelete = new JButton("DELETE");
                JButton works_atDelete = new JButton("DELETE");

                // animal's delete button will actually do something :D
                JButton animalsDelete = new JButton("DELETE");
                animalsDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteAnimal();
                    }
                });

                //Initialize UPDATE buttons and action listeners
                JButton storageUpdate = new JButton("UPDATE");
                JButton preppedFoodUpdate = new JButton("UPDATE");
                JButton rawFoodOrdersUpdate = new JButton("UPDATE");
                JButton computersUpdate = new JButton("UPDATE");
                JButton animalsUpdate = new JButton("UPDATE");
                JButton habitatsUpdate = new JButton("UPDATE");
                JButton workersUpdate = new JButton("UPDATE");
                JButton zookeepersUpdate = new JButton("UPDATE");
                JButton vetsUpdate = new JButton("UPDATE");
                JButton vendorsUpdate = new JButton("UPDATE");
                JButton shopsUpdate = new JButton("UPDATE");
                JButton itemsUpdate = new JButton("UPDATE");
                JButton assigned_toUpdate = new JButton("UPDATE");
                JButton cohabitates_withUpdate = new JButton("UPDATE");
                JButton feedsUpdate = new JButton("UPDATE");
                JButton located_atUpdate = new JButton("UPDATE");
                JButton made_fromUpdate = new JButton("UPDATE");
                JButton maintains_healthUpdate = new JButton("UPDATE");
                JButton stored_atUpdate = new JButton("UPDATE");
                JButton works_atUpdate = new JButton("UPDATE");


                //Initialize VIEW buttons and action listeners
                JButton vetsView = new JButton("VIEW TABLE");
                vetsView.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewVets();
                    }
                });

                //Workers update button will actually do something :D
                workersUpdate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateWorkers();
                    }
                });

                // initialize join button between Animals and Prepped_Food
                JButton animalsPreppedFoodJoin = new JButton("Animals joined w/ Prepped_Food");

                animalsPreppedFoodJoin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        joinAnimals();
                    }
                });

                //initialize projection button
                JButton vetsProjection = new JButton("PROJECT");
                vetsProjection.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        projectVet();
                    }
                });

                //Initialize selection button
                JButton compsSelection = new JButton("SELECT");
                compsSelection.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            selectComputer();
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
                computers.add(compsSelection);
                computers.setLayout(new FlowLayout());
                computers.setBackground(Color.DARK_GRAY);

                //Animals Tab
                JPanel animals = new JPanel();
                animals.add(animalsInsert);
                animals.add(animalsDelete);
                animals.add(animalsUpdate);
                animals.add(animalsPreppedFoodJoin);
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
                vendors.add(vendorsUpdate);
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
                vets.add(vetsView);
                vets.add(vetsProjection);
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

                //Assigned_to Tab
                JPanel assigned_to = new JPanel();
                assigned_to.add(assigned_toInsert);
                assigned_to.add(assigned_toDelete);
                assigned_to.add(assigned_toUpdate);
                items.setLayout(new FlowLayout());
                items.setBackground(Color.DARK_GRAY);

                //cohabitates_with tab
                JPanel cohabitates_with = new JPanel();
                cohabitates_with.add(cohabitates_withInsert);
                cohabitates_with.add(cohabitates_withDelete);
                cohabitates_with.add(cohabitates_withUpdate);
                cohabitates_with.setLayout(new FlowLayout());
                cohabitates_with.setBackground(Color.DARK_GRAY);

                //feeds Tab
                JPanel feeds = new JPanel();
                feeds.add(feedsInsert);
                feeds.add(feedsDelete);
                feeds.add(feedsUpdate);
                feeds.setLayout(new FlowLayout());
                feeds.setBackground(Color.DARK_GRAY);

                //located_at tab
                JPanel located_at = new JPanel();
                located_at.add(located_atInsert);
                located_at.add(located_atDelete);
                located_at.add(located_atUpdate);
                located_at.setLayout(new FlowLayout());
                located_at.setBackground(Color.DARK_GRAY);

                //Made_from tab
                JPanel made_from = new JPanel();
                made_from.add(made_fromInsert);
                made_from.add(made_fromDelete);
                made_from.add(made_fromUpdate);
                made_from.setLayout(new FlowLayout());
                made_from.setBackground(Color.DARK_GRAY);

                //maintains_health tab
                JPanel maintains_health = new JPanel();
                maintains_health.add(maintains_healthInsert);
                maintains_health.add(maintains_healthDelete);
                maintains_health.add(maintains_healthUpdate);
                maintains_health.setLayout(new FlowLayout());
                maintains_health.setBackground(Color.DARK_GRAY);

                //stored_at tab
                JPanel stored_at = new JPanel();
                stored_at.add(stored_atInsert);
                stored_at.add(stored_atDelete);
                stored_at.add(stored_atUpdate);
                stored_at.setLayout(new FlowLayout());
                stored_at.setBackground(Color.DARK_GRAY);

                //works_at tab
                JPanel works_at = new JPanel();
                works_at.add(works_atInsert);
                works_at.add(works_atDelete);
                works_at.add(works_atUpdate);
                works_at.setLayout(new FlowLayout());
                works_at.setBackground(Color.DARK_GRAY);

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
                tabs.addTab("Assigned To", assigned_to);
                tabs.addTab("Cohabitates With", cohabitates_with);
                tabs.addTab("Feeds", feeds);
                tabs.addTab("Located At", located_at);
                tabs.addTab("Made From", made_from);
                tabs.addTab("Maintains Health of", maintains_health);
                tabs.addTab("Stored At", stored_at);
                tabs.addTab("Works At", works_at);
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
            //Create A JFrame to show text entries for update
            updateFrame = new JFrame();
            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateFrame.setTitle("Entries For Update");
            updateFrame.setSize(400, 500);
            updateFrame.setLocationRelativeTo(null);


            //CREATE A JFRAME & JPANEL FOR A BUTTON FOR ALL ATTRIBUTES
            updateButtons = new JFrame();
            updateButtons.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            updateButtons.setTitle("Update Existing Worker");
            updateButtons.setSize(400, 500);
            updateFrame.setLocationRelativeTo(null);
            JPanel forUpdate = new JPanel(new FlowLayout(FlowLayout.CENTER)); //To show all the buttons
            JPanel updateText = new JPanel(new FlowLayout(FlowLayout.CENTER)); //To show text for actual update
            JTextField wid = new JTextField("Worker's Current ID [Required]"); //wid text field REQUIRED FOR ALL UPDATES

            //Create Buttons & associated action listeners
//            JButton w_id = new JButton("Update Worker ID");
//            w_id.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    updateButtons.remove(forUpdate);
//
//                    JTextField update = new JTextField("New Worker ID [Required]"); //text for new worker ID
//                    //Add components to panel
//                    updateText.add(wid);
//                    updateText.add(update);
//
//
//                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
//                    updateText.add(applyUpdate);
//
//                    //Add panel to frame
//                    updateButtons.add(updateText);
//                    applyUpdate.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            try{
//                                String oldWID = wid.getText();
//                                String newWID = update.getText();
//                                if(oldWID.equals("") || newWID.equals(""))
//                                    throw new Error();
//                                //TODO actual update here
//
//
//                                showSuccessFrame();
//                            }catch(Error widError){
//                                displayError("Current worker ID does not exist or you did not fill in a required field");
//                            }
//                            //Remove for next use
//                            updateText.removeAll();
//                            updateButtons.removeAll();
//
//
//                        }
//                    });
//                }
//            });
            JButton name = new JButton("Update Worker's Name");
            name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateButtons.dispose();
//                    updateButtons.remove(forUpdate);
                    JTextField update = new JTextField("Worker's New Name [Required]"); //text for new Worker name
                    //Add components to panel
                    updateText.add(wid);
                    updateText.add(update);


                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
                    updateText.add(applyUpdate);

                    //Add panel to frame
                    updateFrame.add(updateText);
                    displayUpdateFrame(); //Display frame with text entries for user and button to apply changes
                    applyUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                String widInput = wid.getText();
                                String newName = update.getText();
                                if(widInput.equals("") || newName.equals(""))
                                    throw new Error();

                                //TODO actual update here
                                dbHandler.updateWorker(widInput, Constants.NAME, newName);


                                updateFrame.dispose();
                                showSuccessFrame();
                            }catch(Error nameError){
                                updateFrame.dispose();
                                displayError("You did not fill in a required field");
                            } catch (SQLException throwables) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist or did not fill in a required field");
                            } catch (NotExists notExists) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist");
                            }


                            //Remove for next use
                            updateText.removeAll();
                            updateFrame.removeAll();

                        }
                    });
                }
            });
            JButton payrate = new JButton("Update Worker's Pay Rate");
            payrate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateButtons.dispose();
//                    updateButtons.remove(forUpdate);
                    JTextField update = new JTextField("Worker's New Pay Rate [Required]"); //text for new worker pay rate
                    //Add components to panel
                    updateText.add(wid);
                    updateText.add(update);


                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
                    updateText.add(applyUpdate);

                    //Add panel to frame
                    updateFrame.add(updateText);
                    displayUpdateFrame();
                    applyUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                String widInput = wid.getText();
                                float newPay = Float.parseFloat(update.getText());
                                if(widInput.equals(""))
                                    throw new Error();

                                //TODO actual update here
                                dbHandler.updateWorker(widInput, Constants.PAY_RATE, newPay);


                                updateFrame.dispose();
                                showSuccessFrame();
                            }catch(Error payError){
                                updateFrame.dispose();
                                displayError("You did not fill in a required field, or did not input a float for new pay rate");
                            } catch (SQLException throwables) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist or did not fill in a required field");
                            } catch (NotExists notExists) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist");
                            }
                            //Remove for next use
                            updateText.removeAll();
                            updateFrame.removeAll();
                        }
                    });

                }
            });
            JButton email = new JButton("Update Worker's Email");
            email.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateButtons.dispose();
 //                   updateButtons.remove(forUpdate);
                    JTextField update = new JTextField("Worker's New Email [Required]"); //text for new worker pay rate
                    //Add components to panel
                    updateText.add(wid);
                    updateText.add(update);


                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
                    updateText.add(applyUpdate);

                    //Add panel to frame
                    updateFrame.add(updateText);
                    displayUpdateFrame();
                    applyUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                String widInput = wid.getText();
                                String newEmail = update.getText();
                                if(widInput.equals("") || newEmail.equals(""))
                                    throw new Error();

                                //TODO actual update here
                                dbHandler.updateWorker(widInput, Constants.EMAIL, newEmail);

                                updateFrame.dispose();
                                showSuccessFrame();
                            }catch(Error payError){
                                updateFrame.dispose();
                                displayError("You did not fill in a required field");
                            } catch (SQLException throwables) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist or did not fill in a required field");
                            } catch (NotExists notExists) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist");
                            }
                            //Remove for next use
                            updateText.removeAll();
                            updateFrame.removeAll();
                        }
                    });
                }
            });
            JButton phone = new JButton("Update Worker's Phone #");
            phone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateButtons.dispose();
//                    updateButtons.remove(forUpdate);
                    JTextField update = new JTextField("Worker's New Phone # [Required]"); //text for new worker pay rate
                    //Add components to panel
                    updateText.add(wid);
                    updateText.add(update);


                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
                    updateText.add(applyUpdate);

                    //Add panel to frame
                    updateFrame.add(updateText);
                    displayUpdateFrame();
                    applyUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                String widInput = wid.getText();
                                String newPhone = update.getText();
                                if(widInput.equals("") || newPhone.equals(""))
                                    throw new Error();

                                //TODO actual update here
                                dbHandler.updateWorker(widInput, Constants.PHONE, newPhone);


                                updateFrame.dispose();
                                showSuccessFrame();
                            }catch(Error payError){
                                updateFrame.dispose();
                                displayError("Current worker ID does not exist or you did not fill in a required field");
                            } catch (SQLException throwables) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist or did not fill in a required field");
                            } catch (NotExists notExists) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist");
                            }
                            //Remove for next use
                            updateText.removeAll();
                            updateFrame.removeAll();
                        }
                    });
                }
            });
            JButton address = new JButton("Update Worker's Address");
            address.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateButtons.dispose();
                    //updateButtons.remove(forUpdate);
                    JTextField update = new JTextField("Worker's New Address [Required]"); //text for new worker pay rate
                    //Add components to panel
                    updateText.add(wid);
                    updateText.add(update);


                    JButton applyUpdate = new JButton("Apply Update"); //Button to actually apply update
                    updateText.add(applyUpdate);

                    //Add panel to frame
                    updateFrame.add(updateText);
                    displayUpdateFrame();
                    applyUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                String widInput = wid.getText();
                                String newAddress =update.getText();
                                if(widInput.equals("") || newAddress.equals(""))
                                    throw new Error();


                                //TODO actual update here
                                dbHandler.updateWorker(widInput, Constants.ADDRESS, newAddress);

                                updateFrame.dispose();
                                showSuccessFrame();
                            }catch(Error payError){
                                updateFrame.dispose();
                                displayError("Current worker ID does not exist or you did not fill in a required field");
                            } catch (SQLException throwables) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist or did not fill in a required field");
                            } catch (NotExists notExists) {
                                updateFrame.dispose();
                                displayError("You entered a worker ID that does not exist");
                            }
                            //Remove for next use
                            updateText.removeAll();
                            updateFrame.removeAll();
                        }
                    });
                }
            });


            //Add Buttons to JPanel
//            forUpdate.add(w_id);
            forUpdate.add(name);
            forUpdate.add(payrate);
            forUpdate.add(email);
            forUpdate.add(phone);
            forUpdate.add(address);

            //Add JPanel to JWindow & set to visible
            updateButtons.add(forUpdate);
            this.updateButtons.setVisible(true);

        }

        //Display update Frame for update worker
        public void displayUpdateFrame(){
            this.updateFrame.setVisible(true);
        }

        //Display error text
        public void displayError(String text){
            JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
        }


        public void joinAnimals(){
            //creating the joinFrame
            joinFrame = new JFrame();
            joinFrame.setTitle("Update Existing Worker");
            joinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            joinFrame.setSize(800, 200);
            joinFrame.setLocationRelativeTo(null);

            //create JPanel
            JPanel forJoin = new JPanel(new FlowLayout(FlowLayout.CENTER));

            //adds text to JFrame
            JLabel joinText = new JLabel("This join finds the name of the Prepped Food associated with animals according to species. Please select a species");
            JTextField joinSpecies = new JTextField("animal species [Required]");
            JButton applyJoin = new JButton("Apply Join");
            applyJoin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String speciesJoin = joinSpecies.getText();
                        if(speciesJoin.equals("")) {
                            throw new Error();
                        }
                        //show table data here
                        showJoinData();

                    }catch(Error e2){
                        deleteFrame.dispose();
                        JOptionPane.showMessageDialog(null, "You entered the wrong type of input, did not fill in a required field, or" +
                                " entered a species that does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //add components to JPanel
            forJoin.add(joinText);
            forJoin.add(joinSpecies);
            forJoin.add(applyJoin);

            //add panel to frame
            joinFrame.add(forJoin);

            //set window as visible
            this.joinFrame.setVisible(true);

        }

        public void showJoinData(){
            //shows table data
        }

        public void showSuccessFrame(){
            successFrame = new JFrame();
            successFrame.setTitle("Success!");
            successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            successFrame.setSize(300, 300);
            successFrame.setLocationRelativeTo(null);
            this.successFrame.setVisible(true);
        }

        public void viewVets(){
            viewFrame = new JFrame();
            viewFrame.setTitle("Veterinarian Table");
            viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewFrame.setSize(800, 500);
            viewFrame.setLocationRelativeTo(null);

            //get table info onto jpanel
            JPanel vetView = new JPanel(new FlowLayout(FlowLayout.CENTER));

            //1D to 2D array to fill JTable
            JTable vetTable;

            ArrayList<String> col = new ArrayList<String>();
            col.clear();
            col.add(Constants.VET_W_ID);
            col.add(Constants.NAME);
            col.add(Constants.PAY_RATE);
            col.add(Constants.ADDRESS);
            col.add(Constants.EMAIL);
            col.add(Constants.PHONE);
            col.add(Constants.SPECIALIZATION);

            String [][] vetAttributes;
            String [] columnNames ={"ID","Name", "Pay Rate", "Address", "Email", "Phone", "Specialization"};

            try{
                Veterinarian [] allVets = dbHandler.getVeterinarianInfo(col);
                vetAttributes = new String[allVets.length][7];
                //7 is for number of attributes a vet has
                for(int row = 0; row<allVets.length; row++){
                    for(int column = 0; column < 7; column++){
                        if(column == 0){
                            vetAttributes[row][column] = allVets[row].getW_id();
                        }else if(column == 1){
                            vetAttributes[row][column] = allVets[row].getName();
                        }else if(column == 2){
                            vetAttributes[row][column] = allVets[row].getPay_rate() + "";
                        }else if(column == 3){
                            vetAttributes[row][column] = allVets[row].getAddress();
                        }else if(column == 4){
                            vetAttributes[row][column] = allVets[row].getEmail();
                        }else if(column == 5){
                            vetAttributes[row][column] = allVets[row].getPhone();
                        }else{
                            String spec = allVets[row].getSpecialization();
                            if(spec.isEmpty()){
                                vetAttributes[row][column] = "N/A";
                            }else{
                                vetAttributes[row][column] = allVets[row].getSpecialization();
                            }

                        }

                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Add table to panel, add panel to frame
            vetTable = new JTable(vetAttributes, columnNames);
            vetView.add(vetTable);
            viewFrame.add(vetView);

            //set it visible
            this.viewFrame.setVisible(true);
        }

        public void projectVet(){
            checkBoxFrame = new JFrame();
            checkBoxFrame.setTitle("Projection");
            checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            checkBoxFrame.setSize(800, 500);
            checkBoxFrame.setLocationRelativeTo(null);

            //Pop up new window with checkboxes for attributes to be projected
            JPanel forVetsProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

            //Reset Projection Array
            resetProjectionArray();

            JCheckBox wid = new JCheckBox("Worker ID");
            wid.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e)  {
                        //place zero
                    projectionArray[0] = true;
                }
            });
            JCheckBox name = new JCheckBox("Worker's Name");
            name.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 1
                    projectionArray[1] = true;
                }
            });
            JCheckBox payRate = new JCheckBox("Worker's Pay Rate");
            payRate.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 2
                    projectionArray[2] = true;
                }
            });
            JCheckBox addr = new JCheckBox("Worker's Address");
            addr.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 3
                    projectionArray[3] = true;
                }
            });
            JCheckBox email = new JCheckBox("Worker's Email");
            email.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 4
                    projectionArray[4] = true;
                }
            });
            JCheckBox phone = new JCheckBox("Worker's Phone #");
            phone.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 5
                    projectionArray[5] = true;
                }
            });
            JCheckBox spec = new JCheckBox("Worker's Specialization");
            spec.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //place 6
                    projectionArray[6] = true;
                }
            });

            //Button to apply projection
            JButton applyProj = new JButton("Apply Projection");
            applyProj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkBoxFrame.dispose();

                }
            });

            //Checkboxes
            forVetsProjection.add(wid);
            forVetsProjection.add(name);
            forVetsProjection.add(payRate);
            forVetsProjection.add(addr);
            forVetsProjection.add(email);
            forVetsProjection.add(phone);
            forVetsProjection.add(spec);
            forVetsProjection.add(applyProj);

            //Add Panel to frame
            checkBoxFrame.add(forVetsProjection);
            this.checkBoxFrame.setVisible(true);


        }

        public void displayProjection(JTable projTable){
            projectionFrame = new JFrame("Projected Table");
            projectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            projectionFrame.setSize(800, 500);
            projectionFrame.setLocationRelativeTo(null);

            JPanel toProject = new JPanel(new FlowLayout(FlowLayout.CENTER));
            toProject.add(projTable);
            projectionFrame.add(toProject);
            this.projectionFrame.setVisible(true);

        }

        //Reset the values of projectionArray before each use
        public void resetProjectionArray(){
            for(int i = 0; i<projectionArray.length; i++){
                projectionArray[i] = false;
            }
        }


        //For selection
        public void selectComputer(){
            userSelectionFrame = new JFrame("Select");
            userSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userSelectionFrame.setSize(300, 400);
            userSelectionFrame.setLocationRelativeTo(null);

            JPanel forUserInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextArea message = new JTextArea("Manufacturer equal to ");
            JTextField userMan = new JTextField("[Insert Manufacturer]");
            JButton applySelection = new JButton("Apply Selection");
            forUserInput.add(message);
            forUserInput.add(userMan);
            forUserInput.add(applySelection);

            userSelectionFrame.add(forUserInput);
            this.userSelectionFrame.setVisible(true);


            applySelection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String inputtedMan = userMan.getText();
                        userSelectionFrame.dispose();
                        if(inputtedMan.isEmpty()){
                            displayError("You did not enter a manufacturer");
                        }else{
                            Computer [] validComputers = dbHandler.selectManufacturer(inputtedMan);
                            showSelectionFrame = new JFrame("Selected Computers");
                            showSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            showSelectionFrame.setSize(700, 400);
                            showSelectionFrame.setLocationRelativeTo(null);

                            JPanel showComputers = new JPanel(new FlowLayout(FlowLayout.CENTER));

                            String[] colNames = {"Computer ID", "Worker ID", "Model", "Manufacturer", "Type"};
                            String [][] compAttributes = new String[validComputers.length][colNames.length];

                            for(int row = 0; row<validComputers.length; row++){
                                for(int col = 0; col<colNames.length; col++){
                                    if(col == 0){
                                        compAttributes[row][col] = validComputers[row].getC_id();
                                    }else if(col == 1){
                                        if(validComputers[row].getW_id().isEmpty()){
                                            compAttributes[row][col] = "N/A";
                                        }else{
                                            compAttributes[row][col] = validComputers[row].getW_id();
                                        }
                                    }else if(col == 2){
                                        compAttributes[row][col] = validComputers[row].getModel();
                                    }else if(col == 3){
                                        compAttributes[row][col] = validComputers[row].getManufacturer();
                                    }else{
                                        compAttributes[row][col] = validComputers[row].getType();
                                    }
                                }
                            }

                            JTable compSelectionTable = new JTable(compAttributes, colNames);
                            JScrollPane compSelectionPane = new JScrollPane(compSelectionTable);
                            compSelectionPane.setPreferredSize(new Dimension(700, 400));

                            showComputers.add(compSelectionPane);
                            showSelectionFrame.add(showComputers);
                            showSelectionFrame();

                        }
                    }catch(Error | SQLException selectionError){
                        userSelectionFrame.dispose();
                        displayError("You did not enter a valid manufacturer");
                    }
                }
            });

        }

        public void show() {
                this.defaultFrame.setVisible(true);
        }

        public void showSelectionFrame(){
            this.showSelectionFrame.setVisible(true);
        }


}
