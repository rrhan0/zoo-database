package UI;

import database.DatabaseConnectionHandler;
import model.*;
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
    private JFrame showJoinFrame;
    private JFrame successFrame;
    private JFrame viewFrame;
    private JFrame checkBoxFrame;
    private JFrame projectionFrame;
    private JFrame userSelectionFrame;
    private JFrame showSelectionFrame;
    private JFrame aggregationGroupByFrame;
    private JFrame aggregationHavingFrame;
    private JFrame divisionFrame;
    //Used to determine checked boxes for projection of tables
    private boolean col0 = false, col1 = false, col2 = false, col3 = false, col4 = false, col5 = false, col6 = false;

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

        //Workers update button will actually do something :D
        workersUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWorkers();
            }
        });

        //Initialize VIEW buttons and action listeners
        JButton storageView = new JButton("VIEW TABLE");
        JButton preppedFoodView = new JButton("VIEW TABLE");
        JButton rawFoodOrdersView = new JButton("VIEW TABLE");
        JButton computersView = new JButton("VIEW TABLE");
        JButton animalsView = new JButton("VIEW TABLE");
        JButton habitatsView = new JButton("VIEW TABLE");
        JButton workersView = new JButton("VIEW TABLE");
        JButton zookeepersView = new JButton("VIEW TABLE");
        JButton vetsView = new JButton("VIEW TABLE");
        JButton vendorsView = new JButton("VIEW TABLE");
        JButton shopsView = new JButton("VIEW TABLE");
        JButton itemsView = new JButton("VIEW TABLE");
        JButton assigned_toView = new JButton("VIEW TABLE");
        JButton cohabitates_withView = new JButton("VIEW TABLE");
        JButton feedsView = new JButton("VIEW TABLE");
        JButton located_atView = new JButton("VIEW TABLE");
        JButton made_fromView = new JButton("VIEW TABLE");
        JButton maintains_healthView = new JButton("VIEW TABLE");
        JButton stored_atView = new JButton("VIEW TABLE");
        JButton works_atView = new JButton("VIEW TABLE");
        storageView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStorage();
            }
        });
        preppedFoodView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPreppedFood();
            }
        });
        rawFoodOrdersView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRawFoodOrders();
            }
        });
        computersView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewComputers();
            }
        });
        animalsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAnimals();
            }
        });
        habitatsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewHabitats();
            }
        });
        workersView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewWorkers();
            }
        });
        zookeepersView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewZookeepers();
            }
        });
        vetsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewVets();
            }
        });
        vendorsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewVendors();
            }
        });
        shopsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewShops();
            }
        });
        itemsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewItems();
            }
        });
        assigned_toView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAssigned_to();
            }
        });
        cohabitates_withView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCohabitates_with();
            }
        });
        feedsView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFeeds();
            }
        });
        located_atView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewLocated_at();
            }
        });
        made_fromView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMade_from();
            }
        });
        maintains_healthView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMaintains_health();
            }
        });
        stored_atView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStored_at();
            }
        });
        works_atView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewWorks_at();
            }
        });

        // initialize join button between Animals and Prepped_Food
        JButton animalsPreppedFoodJoin = new JButton("Animals joined w/ Prepped_Food");

        animalsPreppedFoodJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinAnimalsWithPreppedFood();
            }
        });

        //initialize projection button
        JButton storageProjection = new JButton("PROJECT");
        JButton preppedFoodProjection = new JButton("PROJECT");
        JButton rawFoodOrdersProjection = new JButton("PROJECT");
        JButton computersProjection = new JButton("PROJECT");
        JButton animalsProjection = new JButton("PROJECT");
        JButton habitatsProjection = new JButton("PROJECT");
        JButton workersProjection = new JButton("PROJECT");
        JButton zookeepersProjection = new JButton("PROJECT");
        JButton vetsProjection = new JButton("PROJECT");
        JButton vendorsProjection = new JButton("PROJECT");
        JButton shopsProjection = new JButton("PROJECT");
        JButton itemsProjection = new JButton("PROJECT");
        JButton assigned_toProjection = new JButton("PROJECT");
        JButton cohabitates_withProjection = new JButton("PROJECT");
        JButton feedsProjection = new JButton("PROJECT");
        JButton located_atProjection = new JButton("PROJECT");
        JButton made_fromProjection = new JButton("PROJECT");
        JButton maintains_healthProjection = new JButton("PROJECT");
        JButton stored_atProjection = new JButton("PROJECT");
        JButton works_atProjection = new JButton("PROJECT");
        //initialize projection action listeners
        storageProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectStorage();
            }
        });
        preppedFoodProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectPreppedFood();
            }
        });
        rawFoodOrdersProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectRawFood();
            }
        });
        computersProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectComputers();
            }
        });
        animalsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectAnimals();
            }
        });
        habitatsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectHabitats();
            }
        });
        workersProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectWorkers();
            }
        });
        zookeepersProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectZookeepers();
            }
        });
        vetsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectVet();
            }
        });
        vendorsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectVendors();
            }
        });
        shopsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectShops();
            }
        });
        itemsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectItems();
            }
        });
        assigned_toProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectAssigned_to();
            }
        });
        cohabitates_withProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectcohab();
            }
        });
        feedsProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectFeeds();
            }
        });
        located_atProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectLocated();
            }
        });
        made_fromProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectMade();
            }
        });
        maintains_healthProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectMaintains();
            }
        });
        stored_atProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectStoredAt();
            }
        });
        works_atProjection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectWorksAt();
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

        //Initialize aggregation group by button
        JButton aggregateGroupBy = new JButton("AGGREGATE WITH GROUP BY");
        aggregateGroupBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggregationGroupBy();
            }
        });

        //initialize aggregation having button
        JButton aggregationHaving = new JButton("AGGREGATING WITH HAVING");
        aggregationHaving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggregationHaving();
            }
        });

        //initialize division button
        JButton division = new JButton("DIVISION");
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                division();
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
        storageUnits.add(storageView);
        storageUnits.add(storageProjection);
        storageUnits.add(aggregationHaving);
        storageUnits.setLayout((new FlowLayout()));
        storageUnits.setBackground(Color.DARK_GRAY);

        //Prepped_Food Tab
        JPanel preppedFood = new JPanel();
        preppedFood.add(preppedFoodInsert);
        preppedFood.add(preppedFoodDelete);
        preppedFood.add(preppedFoodUpdate);
        preppedFood.add(preppedFoodView);
        preppedFood.add(preppedFoodProjection);
        preppedFood.setLayout(new FlowLayout());
        preppedFood.setBackground(Color.DARK_GRAY);

        //Raw_Food_Orders Tab
        JPanel rawFoodOrders = new JPanel();
        rawFoodOrders.add(rawFoodOrdersInsert);
        rawFoodOrders.add(rawFoodOrdersDelete);
        rawFoodOrders.add(rawFoodOrdersUpdate);
        rawFoodOrders.add(rawFoodOrdersView);
        rawFoodOrders.add(rawFoodOrdersProjection);
        rawFoodOrders.add(aggregateGroupBy);
        rawFoodOrders.setLayout(new FlowLayout());
        rawFoodOrders.setBackground(Color.DARK_GRAY);

        //Computers Tab
        JPanel computers = new JPanel();
        computers.add(computersInsert);
        computers.add(computersDelete);
        computers.add(computersUpdate);
        computers.add(computersView);
        computers.add(computersProjection);
        computers.add(compsSelection);
        computers.setLayout(new FlowLayout());
        computers.setBackground(Color.DARK_GRAY);

        //Animals Tab
        JPanel animals = new JPanel();
        animals.add(animalsInsert);
        animals.add(animalsDelete);
        animals.add(animalsUpdate);
        animals.add(animalsView);
        animals.add(animalsProjection);
        animals.add(animalsPreppedFoodJoin);
        animals.setLayout(new FlowLayout());
        animals.setBackground(Color.DARK_GRAY);

        //Habitats Tab
        JPanel habitats = new JPanel();
        habitats.add(habitatsInsert);
        habitats.add(habitatsDelete);
        habitats.add(habitatsUpdate);
        habitats.add(habitatsView);
        habitats.add(habitatsProjection);
        habitats.setLayout(new FlowLayout());
        habitats.setBackground(Color.DARK_GRAY);

        //Workers Tab
        JPanel workers = new JPanel();
        workers.add(workerInsert);
        workers.add(workerDelete);
        workers.add(workersUpdate);
        workers.add(workersView);
        workers.add(workersProjection);
        workers.setLayout(new FlowLayout());
        workers.setBackground(Color.DARK_GRAY);

        //Vendors Tab
        JPanel vendors = new JPanel();
        vendors.add(vendorsInsert);
        vendors.add(vendorsDelete);
        vendors.add(vendorsUpdate);
        vendors.add(vendorsView);
        vendors.add(vendorsProjection);
        vendors.setLayout(new FlowLayout());
        vendors.setBackground(Color.DARK_GRAY);

        //Zookeepers Tab
        JPanel zookeepers = new JPanel();
        zookeepers.add(zookeepersInsert);
        zookeepers.add(zookeepersDelete);
        zookeepers.add(zookeepersUpdate);
        zookeepers.add(zookeepersView);
        zookeepers.add(zookeepersProjection);
        zookeepers.add(division);
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
        shops.add(shopsView);
        shops.add(shopsProjection);
        shops.setLayout(new FlowLayout());
        shops.setBackground(Color.DARK_GRAY);

        //Items Tab
        JPanel items = new JPanel();
        items.add(itemsInsert);
        items.add(itemsDelete);
        items.add(itemsUpdate);
        items.add(itemsView);
        items.add(itemsProjection);
        items.setLayout(new FlowLayout());
        items.setBackground(Color.DARK_GRAY);

        //Assigned_to Tab
        JPanel assigned_to = new JPanel();
        assigned_to.add(assigned_toInsert);
        assigned_to.add(assigned_toDelete);
        assigned_to.add(assigned_toUpdate);
        assigned_to.add(assigned_toView);
        assigned_to.add(assigned_toProjection);
        assigned_to.setLayout(new FlowLayout());
        assigned_to.setBackground(Color.DARK_GRAY);

        //cohabitates_with tab
        JPanel cohabitates_with = new JPanel();
        cohabitates_with.add(cohabitates_withInsert);
        cohabitates_with.add(cohabitates_withDelete);
        cohabitates_with.add(cohabitates_withUpdate);
        cohabitates_with.add(cohabitates_withView);
        cohabitates_with.add(cohabitates_withProjection);
        cohabitates_with.setLayout(new FlowLayout());
        cohabitates_with.setBackground(Color.DARK_GRAY);

        //feeds Tab
        JPanel feeds = new JPanel();
        feeds.add(feedsInsert);
        feeds.add(feedsDelete);
        feeds.add(feedsUpdate);
        feeds.add(feedsView);
        feeds.add(feedsProjection);
        feeds.setLayout(new FlowLayout());
        feeds.setBackground(Color.DARK_GRAY);

        //located_at tab
        JPanel located_at = new JPanel();
        located_at.add(located_atInsert);
        located_at.add(located_atDelete);
        located_at.add(located_atUpdate);
        located_at.add(located_atView);
        located_at.add(located_atProjection);
        located_at.setLayout(new FlowLayout());
        located_at.setBackground(Color.DARK_GRAY);

        //Made_from tab
        JPanel made_from = new JPanel();
        made_from.add(made_fromInsert);
        made_from.add(made_fromDelete);
        made_from.add(made_fromUpdate);
        made_from.add(made_fromView);
        made_from.add(made_fromProjection);
        made_from.setLayout(new FlowLayout());
        made_from.setBackground(Color.DARK_GRAY);

        //maintains_health tab
        JPanel maintains_health = new JPanel();
        maintains_health.add(maintains_healthInsert);
        maintains_health.add(maintains_healthDelete);
        maintains_health.add(maintains_healthUpdate);
        maintains_health.add(maintains_healthView);
        maintains_health.add(maintains_healthProjection);
        maintains_health.setLayout(new FlowLayout());
        maintains_health.setBackground(Color.DARK_GRAY);

        //stored_at tab
        JPanel stored_at = new JPanel();
        stored_at.add(stored_atInsert);
        stored_at.add(stored_atDelete);
        stored_at.add(stored_atUpdate);
        stored_at.add(stored_atView);
        stored_at.add(stored_atProjection);
        stored_at.setLayout(new FlowLayout());
        stored_at.setBackground(Color.DARK_GRAY);

        //works_at tab
        JPanel works_at = new JPanel();
        works_at.add(works_atInsert);
        works_at.add(works_atDelete);
        works_at.add(works_atUpdate);
        works_at.add(works_atView);
        works_at.add(works_atProjection);
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


    public void insertNewVet() {
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
        JTextField email = new JTextField("Worker's email (REQUIRED)"); //UNIQUE
        JTextField phone = new JTextField("Worker's phone (REQUIRED)", 13); //UNIQUE
        JTextField address = new JTextField("Worker's Address (REQUIRED)", 30); //NOT NULL
        JTextField specialization = new JTextField("Veterinarian's Specialization", 20);

        //Create Apply button and create action listener
        JButton applyInsert = new JButton("Apply Insert");
        applyInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String w_idUserInput = w_id.getText(); //REQUIRED
                    String nameInput = name.getText(); //NOT NULL
                    float payRate_UserInput = Float.parseFloat(pay_rate.getText()); //NOT NULL
                    String addressInput = address.getText(); //NOT NULL
                    String emailInput = email.getText(); //UNIQUE
                    String phoneInput = phone.getText(); //UNIQUE
                    String specializationInput = specialization.getText();


                    if (w_idUserInput.equals("") || nameInput.equals("") || addressInput.equals("") || emailInput.equals("") || phoneInput.equals("")) {
                        throw new Exception();
                    }

                    //THEN CREATE ACTUAL VET OBJECT AND INSERT IT

                    dbHandler.insertVeterinarian(new Veterinarian(w_idUserInput, nameInput, payRate_UserInput, addressInput, emailInput, phoneInput, specializationInput));
                    insertFrame.dispose();

                    //SHOW SUCCESS
                    showSuccessFrame();


                } catch (Exception uhoh) {
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

    public void deleteAnimal() {
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
                try {
                    String a_idInput = a_id.getText(); //REQUIRED

                    if (a_idInput.equals("")) {
                        throw new Exception();
                    }

                    //delete tuple from database
                    dbHandler.deleteAnimal(a_idInput);

                    //show success
                    deleteFrame.dispose();
                    showSuccessFrame();
                } catch (Exception nono) {
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

    //updates worker tuple according to w_id
    public void updateWorkers() {
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
                        try {
                            String widInput = wid.getText();
                            String newName = update.getText();
                            if (widInput.equals("") || newName.equals(""))
                                throw new Error();

                            //TODO actual update here
                            dbHandler.updateWorker(widInput, Constants.NAME, newName);


                            updateFrame.dispose();
                            showSuccessFrame();
                        } catch (Error nameError) {
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
                        try {
                            String widInput = wid.getText();
                            float newPay = Float.parseFloat(update.getText());
                            if (widInput.equals(""))
                                throw new Error();


                            dbHandler.updateWorker(widInput, Constants.PAY_RATE, newPay);


                            updateFrame.dispose();
                            showSuccessFrame();
                        } catch (Error payError) {
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
                        try {
                            String widInput = wid.getText();
                            String newEmail = update.getText();
                            if (widInput.equals("") || newEmail.equals(""))
                                throw new Error();

                            //TODO actual update here
                            dbHandler.updateWorker(widInput, Constants.EMAIL, newEmail);

                            updateFrame.dispose();
                            showSuccessFrame();
                        } catch (Error payError) {
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
                        try {
                            String widInput = wid.getText();
                            String newPhone = update.getText();
                            if (widInput.equals("") || newPhone.equals(""))
                                throw new Error();

                            //TODO actual update here
                            dbHandler.updateWorker(widInput, Constants.PHONE, newPhone);


                            updateFrame.dispose();
                            showSuccessFrame();
                        } catch (Error payError) {
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
                        try {
                            String widInput = wid.getText();
                            String newAddress = update.getText();
                            if (widInput.equals("") || newAddress.equals(""))
                                throw new Error();


                            //TODO actual update here
                            dbHandler.updateWorker(widInput, Constants.ADDRESS, newAddress);

                            updateFrame.dispose();
                            showSuccessFrame();
                        } catch (Error payError) {
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
    public void displayUpdateFrame() {
        this.updateFrame.setVisible(true);
    }

    //Display error text
    public void displayError(String text) {
        JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public void joinAnimalsWithPreppedFood() {
        //creating the joinFrame
        joinFrame = new JFrame();
        joinFrame.setTitle("Join Animals With Prepped Food");
        joinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        joinFrame.setSize(800, 200);
        joinFrame.setLocationRelativeTo(null);

        showJoinFrame = new JFrame();
        showJoinFrame.setTitle("Joined Animals Based On Species");
        showJoinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showJoinFrame.setSize(300, 400);
        showJoinFrame.setLocationRelativeTo(null);

        //create JPanel
        JPanel forJoin = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //adds text to JFrame
        JLabel joinText = new JLabel("This join finds the name of the Prepped Food associated with animals according to species. Please select a species");
        JTextField joinSpecies = new JTextField("Animal Species [Required]");
        JButton applyJoin = new JButton("Apply Join");
        applyJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinFrame.dispose();
                try {
                    String speciesInput = joinSpecies.getText();
                    if (speciesInput.equals("")) {
                        throw new Error();
                    }

                    String[] arrayOfFood = dbHandler.getSpeciesPreppedFood(speciesInput); //Get names of food
                    JList<String> listOfFood = new JList<String>(); //Create a list for the food
                    listOfFood.setListData(arrayOfFood); //add food to JList


                    JPanel showJoin = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    showJoin.add(listOfFood);
                    showJoinFrame.add(showJoin);
                    setShowJoinVisible();


                } catch (Error | SQLException | NotExists joinError) {
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

    public void setShowJoinVisible() {
        this.showJoinFrame.setVisible(true);
    }


    public void showSuccessFrame() {
        successFrame = new JFrame();
        successFrame.setTitle("Success!");
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setSize(300, 300);
        successFrame.setLocationRelativeTo(null);
        this.successFrame.setVisible(true);
    }

    public void viewVets() {
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

        String[][] vetAttributes;
        String[] columnNames = {"ID", "Name", "Pay Rate", "Address", "Email", "Phone", "Specialization"};

        try {
            Veterinarian[] allVets = dbHandler.getVeterinarianInfo(col);
            vetAttributes = new String[allVets.length][7]; // [number of rows][number of columns]
            //7 is for number of attributes a vet has
            for (int row = 0; row < allVets.length; row++) {
                for (int column = 0; column < 7; column++) {
                    if (column == 0) {
                        vetAttributes[row][column] = allVets[row].getW_id();
                    } else if (column == 1) {
                        vetAttributes[row][column] = allVets[row].getName();
                    } else if (column == 2) {
                        vetAttributes[row][column] = allVets[row].getPay_rate() + "";
                    } else if (column == 3) {
                        vetAttributes[row][column] = allVets[row].getAddress();
                    } else if (column == 4) {
                        vetAttributes[row][column] = allVets[row].getEmail();
                    } else if (column == 5) {
                        vetAttributes[row][column] = allVets[row].getPhone();
                    } else {
                        String spec = allVets[row].getSpecialization();
                        if (spec.isEmpty()) {
                            vetAttributes[row][column] = "N/A";
                        } else {
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
        JScrollPane vetScrollPane = new JScrollPane(vetTable);
        vetScrollPane.setPreferredSize(new Dimension(700, 400));
        vetView.add(vetScrollPane);
        viewFrame.add(vetView);

        //set it visible
        this.viewFrame.setVisible(true);
    }

    //
    public void viewStorage() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Storage Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel storageView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable storageTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.TEMPERATURE);

        String[][] storageAttributes;
        String[] columnNames = {"Place ID", "Name", "Temperature"};

        try {
            StorageUnit[] allstorage = dbHandler.getStorageUnitInfo(col);
            storageAttributes = new String[allstorage.length][3]; // 3 = # attributes in StorageUnit
            for (int row = 0; row < allstorage.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        storageAttributes[row][column] = allstorage[row].getP_id();
                    } else if (column == 1) {
                        String name = allstorage[row].getName();
                        if (name.isEmpty()) {
                            storageAttributes[row][column] = "N/A";
                        } else {
                            storageAttributes[row][column] = allstorage[row].getName();
                        }
                    } else {
                        storageAttributes[row][column] = allstorage[row].getTemperature() + "";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //add table to panel, add panel to frame
        storageTable = new JTable(storageAttributes, columnNames);
        JScrollPane storageScrollPane = new JScrollPane(storageTable);
        storageScrollPane.setPreferredSize(new Dimension(700, 400));
        storageView.add(storageScrollPane);
        viewFrame.add(storageView);

        //set visible
        this.viewFrame.setVisible(true);
    }

    //view method for prepped_food
    public void viewPreppedFood() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Prepped Food Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel preppedFoodView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable preppedFoodTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.A_ID);
        col.add(Constants.NAME);
        col.add(Constants.WEIGHT);

        String[][] preppedFoodAttributes;
        String[] columnNames = {"Animal ID", "Food Name", "Food Weight"};

        try {
            PreppedFood[] allPreppedFood = dbHandler.getPreppedFoodInfo(col);
            preppedFoodAttributes = new String[allPreppedFood.length][3];
            for (int row = 0; row < allPreppedFood.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        preppedFoodAttributes[row][column] = allPreppedFood[row].getA_id();
                    } else if (column == 1) {
                        preppedFoodAttributes[row][column] = allPreppedFood[row].getName();
                    } else {
                        String weight = allPreppedFood[row].getWeight() + "";
                        if (weight.isEmpty()) {
                            preppedFoodAttributes[row][column] = "N/A";
                        } else {
                            preppedFoodAttributes[row][column] = allPreppedFood[row].getWeight() + "";
                        }
                    }
                }
            }
        } catch (Exception e) { //weird. never catches SQLexception when all the other ones do
            throw new RuntimeException(e);
        }
        //add table to panel, add panel to frame
        preppedFoodTable = new JTable(preppedFoodAttributes, columnNames);
        JScrollPane preppedFoodScrollPane = new JScrollPane(preppedFoodTable);
        preppedFoodScrollPane.setPreferredSize(new Dimension(700, 400));
        preppedFoodView.add(preppedFoodScrollPane);
        viewFrame.add(preppedFoodView);

        this.viewFrame.setVisible(true);
    }

    public void viewRawFoodOrders() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Raw Food Orders Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel rawFoodView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable rawFoodTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.O_ID);
        col.add(Constants.CONTENTS);
        col.add(Constants.WEIGHT);
        col.add(Constants.DATE_RECEIVED);
        col.add(Constants.EXPIRY_DATE);

        String[][] rawFoodAttributes;
        String[] columnNames = {"Order ID", "Contents", "Weight", "Date Received", "Expiry Date"};

        try {
            RawFoodOrder[] allRawFood = dbHandler.getRawFoodOrderInfo(col);
            rawFoodAttributes = new String[allRawFood.length][5];
            for (int row = 0; row < allRawFood.length; row++) {
                for (int column = 0; column < 5; column++) {
                    if (column == 0) {
                        rawFoodAttributes[row][column] = allRawFood[row].getO_id();
                    } else if (column == 1) {
                        rawFoodAttributes[row][column] = allRawFood[row].getContents();
                    } else if (column == 2) {
                        rawFoodAttributes[row][column] = allRawFood[row].getWeight() + "";
                    } else if (column == 3) {
                        rawFoodAttributes[row][column] = allRawFood[row].getDate_received() + "";
                    } else {
                        rawFoodAttributes[row][column] = allRawFood[row].getExpiry_date() + "";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //add table to panel, add panel to frame
        rawFoodTable = new JTable(rawFoodAttributes, columnNames);
        JScrollPane rawFoodScrollPane = new JScrollPane(rawFoodTable);
        rawFoodScrollPane.setPreferredSize(new Dimension(700, 400));
        rawFoodView.add(rawFoodScrollPane);
        viewFrame.add(rawFoodView);

        this.viewFrame.setVisible(true);
    }

    public void viewComputers() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Computers Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel computersView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable computersTable;

        //database entries
        ArrayList<String> col = new ArrayList<String>();
        col.add(Constants.C_ID);
        col.add(Constants.W_ID);
        col.add(Constants.MODEL);
        col.add(Constants.MANUFACTURER);
        col.add(Constants.TYPE);

        String[][] computersAttributes;
        String[] columnNames = {"Computer ID", "Associated Worker ID", "Model", "Manufacturer", "Computer Type"};

        try {
            Computer[] allComputers = dbHandler.getComputerInfo(col);
            computersAttributes = new String[allComputers.length][5];
            for (int row = 0; row < allComputers.length; row++) {
                for (int column = 0; column < 5; column++) {
                    if (column == 0) {
                        computersAttributes[row][column] = allComputers[row].getC_id();
                    } else if (column == 1) {
                        computersAttributes[row][column] = allComputers[row].getW_id();
                    } else if (column == 2) {
                        computersAttributes[row][column] = allComputers[row].getModel();
                    } else if (column == 3) {
                        computersAttributes[row][column] = allComputers[row].getManufacturer();
                    } else {
                        computersAttributes[row][column] = allComputers[row].getType();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //add table to panel, add panel to frame
        computersTable = new JTable(computersAttributes, columnNames);
        JScrollPane computersScrollPane = new JScrollPane(computersTable);
        computersScrollPane.setPreferredSize(new Dimension(700, 400));
        computersView.add(computersScrollPane);
        viewFrame.add(computersView);

        this.viewFrame.setVisible(true);
    }

    public void viewAnimals() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Animals Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel animalsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable animalsTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.A_ID);
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.SPECIES);
        col.add(Constants.GENUS);

        String[][] animalAttributes;
        String[] columnNames = {"Animal ID", "Habitat ID", "Animal Name", "Species", "Genus"};

        try {
            Animal[] allAnimal = dbHandler.getAnimalInfo(col);
            animalAttributes = new String[allAnimal.length][5];
            for (int row = 0; row < allAnimal.length; row++) {
                for (int column = 0; column < 5; column++) {
                    if (column == 0) {
                        animalAttributes[row][column] = allAnimal[row].getA_id();
                    } else if (column == 1) {
                        String p_id = allAnimal[row].getP_id();
                        if (p_id.isEmpty()) {
                            animalAttributes[row][column] = "N/A";
                        } else {
                            animalAttributes[row][column] = allAnimal[row].getP_id();
                        }
                    } else if (column == 2) {
                        String name = allAnimal[row].getName();
                        if (name.isEmpty()) {
                            animalAttributes[row][column] = "N/A";
                        } else {
                            animalAttributes[row][column] = allAnimal[row].getName();
                        }
                    } else if (column == 3) {
                        String species = allAnimal[row].getSpecies();
                        if (species.isEmpty()) {
                            animalAttributes[row][column] = "N/A";
                        } else {
                            animalAttributes[row][column] = allAnimal[row].getSpecies();
                        }
                    } else {
                        String genus = allAnimal[row].getGenus();
                        if (genus.isEmpty()) {
                            animalAttributes[row][column] = "N/A";
                        } else {
                            animalAttributes[row][column] = allAnimal[row].getGenus();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        animalsTable = new JTable(animalAttributes, columnNames);
        JScrollPane animalsScrollPane = new JScrollPane(animalsTable);
        animalsScrollPane.setPreferredSize(new Dimension(700, 400));
        animalsView.add(animalsScrollPane);
        viewFrame.add(animalsView);

        this.viewFrame.setVisible(true);
    }

    public void viewHabitats() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Habitats Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel habitatsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable habitatsTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.BIOME);
        col.add(Constants.AREA);
        col.add(Constants.TEMPERATURE);
        col.add(Constants.HUMIDITY);

        String[][] habitatsAttributes;
        String[] columnNames = {"Place ID", "Habitat Name", "Biome", "Area", "Temperature", "Humidity"};

        try {
            Habitat[] allHabitats = dbHandler.getHabitatInfo(col);
            habitatsAttributes = new String[allHabitats.length][6];
            for (int row = 0; row < allHabitats.length; row++) {
                for (int column = 0; column < 6; column++) {
                    if (column == 0) {
                        habitatsAttributes[row][column] = allHabitats[row].getP_id();
                    } else if (column == 1) {
                        String name = allHabitats[row].getName();
                        if (name.isEmpty()) {
                            habitatsAttributes[row][column] = "N/A";
                        } else {
                            habitatsAttributes[row][column] = allHabitats[row].getName();
                        }
                    } else if (column == 2) {
                        String biome = allHabitats[row].getBiome();
                        if (biome.isEmpty()) {
                            habitatsAttributes[row][column] = "N/A";
                        } else {
                            habitatsAttributes[row][column] = allHabitats[row].getBiome();
                        }
                    } else if (column == 3) {
                        String area = allHabitats[row].getArea() + "";
                        if (area.isEmpty()) {
                            habitatsAttributes[row][column] = "N/A";
                        } else {
                            habitatsAttributes[row][column] = allHabitats[row].getArea() + "";
                        }
                    } else if (column == 4) {
                        String temp = allHabitats[row].getTemperature() + "";
                        if (temp.isEmpty()) {
                            habitatsAttributes[row][column] = "N/A";
                        } else {
                            habitatsAttributes[row][column] = allHabitats[row].getTemperature() + "";
                        }
                    } else {
                        String humid = allHabitats[row].getHumidity() + "";
                        if (humid.isEmpty()) {
                            habitatsAttributes[row][column] = "N/A";
                        } else {
                            habitatsAttributes[row][column] = allHabitats[row].getHumidity() + "";
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        habitatsTable = new JTable(habitatsAttributes, columnNames);
        JScrollPane habitatsScrollPane = new JScrollPane(habitatsTable);
        habitatsScrollPane.setPreferredSize(new Dimension(700, 400));
        habitatsView.add(habitatsScrollPane);
        viewFrame.add(habitatsView);

        this.viewFrame.setVisible(true);
    }

    public void viewWorkers() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Workers Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel workersView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable workersTable;

        //databse entries
        ArrayList<String> col = new ArrayList<String>();
        col.clear();
        col.add(Constants.W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        String[][] workersAttributes;
        String[] columnNames = {"Worker ID", "Name", "Pay Rate", "Address", "Email", "Phone"};

        try {
            Worker[] allWorkers = dbHandler.getWorkerInfo(col);
            workersAttributes = new String[allWorkers.length][6];
            for (int row = 0; row < allWorkers.length; row++) {
                for (int column = 0; column < 6; column++) {
                    if (column == 0) {
                        workersAttributes[row][column] = allWorkers[row].getW_id();
                    } else if (column == 1) {
                        workersAttributes[row][column] = allWorkers[row].getName();
                    } else if (column == 2) {
                        workersAttributes[row][column] = allWorkers[row].getPay_rate() + "";
                    } else if (column == 3) {
                        workersAttributes[row][column] = allWorkers[row].getAddress();
                    } else if (column == 4) {
                        workersAttributes[row][column] = allWorkers[row].getEmail();
                    } else {
                        workersAttributes[row][column] = allWorkers[row].getPhone();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        workersTable = new JTable(workersAttributes, columnNames);
        JScrollPane workersScrollPane = new JScrollPane(workersTable);
        workersScrollPane.setPreferredSize(new Dimension(700, 400));
        workersView.add(workersScrollPane);
        viewFrame.add(workersView);

        this.viewFrame.setVisible(true);
    }

    public void viewZookeepers() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Zookeepers Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel zookeepersView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable zookeepersTable;

        //database entries
        ArrayList<String> col = new ArrayList<String>();
        col.clear();
        col.add(Constants.ZOO_W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        String[][] zookeepersAttributes;
        String[] columnNames = {"Worker ID", "Name", "Pay Rate", "Address", "Email", "Phone"};

        try {
            Zookeeper[] allZookeepers = dbHandler.getZookeeperInfo(col);
            zookeepersAttributes = new String[allZookeepers.length][6];
            for (int row = 0; row < allZookeepers.length; row++) {
                for (int column = 0; column < 6; column++) {
                    if (column == 0) {
                        zookeepersAttributes[row][column] = allZookeepers[row].getW_id();
                    } else if (column == 1) {
                        zookeepersAttributes[row][column] = allZookeepers[row].getName();
                    } else if (column == 2) {
                        zookeepersAttributes[row][column] = allZookeepers[row].getPay_rate() + "";
                    } else if (column == 3) {
                        zookeepersAttributes[row][column] = allZookeepers[row].getAddress();
                    } else if (column == 4) {
                        zookeepersAttributes[row][column] = allZookeepers[row].getEmail();
                    } else {
                        zookeepersAttributes[row][column] = allZookeepers[row].getPhone();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        zookeepersTable = new JTable(zookeepersAttributes, columnNames);
        JScrollPane zookeepersScrollPane = new JScrollPane(zookeepersTable);
        zookeepersScrollPane.setPreferredSize(new Dimension(700, 400));
        zookeepersView.add(zookeepersScrollPane);
        viewFrame.add(zookeepersView);

        this.viewFrame.setVisible(true);
    }

    public void viewVendors() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Vendors Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel vendorsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable vendorsTable;

        //database entries
        ArrayList<String> col = new ArrayList<String>();
        col.clear();
        col.add(Constants.VENDOR_W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        String[][] vendorsAttributes;
        String[] columnNames = {"Worker ID", "Name", "Pay Rate", "Address", "Email", "Phone"};

        try {
            Vendor[] allVendors = dbHandler.getVendorInfo(col);
            vendorsAttributes = new String[allVendors.length][6];
            for (int row = 0; row < allVendors.length; row++) {
                for (int column = 0; column < 6; column++) {
                    if (column == 0) {
                        vendorsAttributes[row][column] = allVendors[row].getW_id();
                    } else if (column == 1) {
                        vendorsAttributes[row][column] = allVendors[row].getName();
                    } else if (column == 2) {
                        vendorsAttributes[row][column] = allVendors[row].getPay_rate() + "";
                    } else if (column == 3) {
                        vendorsAttributes[row][column] = allVendors[row].getAddress();
                    } else if (column == 4) {
                        vendorsAttributes[row][column] = allVendors[row].getEmail();
                    } else {
                        vendorsAttributes[row][column] = allVendors[row].getPhone();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        vendorsTable = new JTable(vendorsAttributes, columnNames);
        JScrollPane vendorScrollPane = new JScrollPane(vendorsTable);
        vendorScrollPane.setPreferredSize(new Dimension(700, 400));
        vendorsView.add(vendorScrollPane);
        viewFrame.add(vendorsView);

        this.viewFrame.setVisible(true);
    }

    public void viewShops() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Shops Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel shopsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable shopsTable;

        //database entries
        ArrayList<String> col = new ArrayList<String>();
        col.clear();
        col.add(Constants.P_ID);
        col.add("name");
        col.add(Constants.TYPE);

        String[][] shopsAttributes;
        String[] columnNames = {"Place ID", "Shop Name", "Shop Type"};

        try {
            Shop[] allshops = dbHandler.getShopInfo(col);
            shopsAttributes = new String[allshops.length][3];
            for (int row = 0; row < allshops.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        shopsAttributes[row][column] = allshops[row].getP_id();
                    } else if (column == 1) {
                        shopsAttributes[row][column] = allshops[row].getName();
                    } else {
                        shopsAttributes[row][column] = allshops[row].getType();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        shopsTable = new JTable(shopsAttributes, columnNames);
        JScrollPane shopsPane = new JScrollPane(shopsTable);
        shopsPane.setPreferredSize(new Dimension(700, 400));
        shopsView.add(shopsPane);
        viewFrame.add(shopsView);

        this.viewFrame.setVisible(true);
    }

    public void viewItems() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Items Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel itemsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable itemsTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.I_ID);
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.STOCK);
        col.add(Constants.PRICE);

        String[][] itemsAttributes;
        String[] columnNames = {"Item ID", "Place ID", "Item Name", "Item Stock", "Item Price"};

        try {
            Item[] allItems = dbHandler.getItemInfo(col);
            itemsAttributes = new String[allItems.length][5];
            for (int row = 0; row < allItems.length; row++) {
                for (int column = 0; column < 5; column++) {
                    if (column == 0) {
                        itemsAttributes[row][column] = allItems[row].getI_id();
                    } else if (column == 1) {
                        itemsAttributes[row][column] = allItems[row].getP_id();
                    } else if (column == 2) {
                        itemsAttributes[row][column] = allItems[row].getName();
                    } else if (column == 3) {
                        itemsAttributes[row][column] = allItems[row].getStock() + "";
                    } else {
                        itemsAttributes[row][column] = allItems[row].getPrice() + "";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        itemsTable = new JTable(itemsAttributes, columnNames);
        JScrollPane itemsScrollPane = new JScrollPane(itemsTable);
        itemsScrollPane.setPreferredSize(new Dimension(700, 400));
        itemsView.add(itemsScrollPane);
        viewFrame.add(itemsView);

        this.viewFrame.setVisible(true);
    }

    public void viewAssigned_to() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Assigned_To Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel assignedView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable assignedTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add(Constants.W_ID);
        col.add(Constants.P_ID);

        String[][] assignedAttributes;
        String[] columnNames = {"Worker ID", "Place ID"};

        try {
            AssignedTo[] allAssigned = dbHandler.getAssignedToInfo(col);
            assignedAttributes = new String[allAssigned.length][2];
            for (int row = 0; row < allAssigned.length; row++) {
                for (int column = 0; column < 2; column++) {
                    if (column == 0) {
                        assignedAttributes[row][column] = allAssigned[row].getW_id();
                    } else {
                        assignedAttributes[row][column] = allAssigned[row].getP_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assignedTable = new JTable(assignedAttributes, columnNames);
        JScrollPane assignedPane = new JScrollPane(assignedTable);
        assignedPane.setPreferredSize(new Dimension(700, 400));
        assignedView.add(assignedPane);
        viewFrame.add(assignedView);

        this.viewFrame.setVisible(true);
    }

    public void viewCohabitates_with() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Cohabitates With Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel cohabView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable cohabTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("A_ID1");
        col.add("A_ID2");

        String[][] cohabAttributes;
        String[] columnNames = {"Animal ID #1", "Animal ID #2"};

        try {
            CohabitatesWith[] allCohab = dbHandler.getCohabitatesWithInfo(col);
            cohabAttributes = new String[allCohab.length][2];
            for (int row = 0; row < allCohab.length; row++) {
                for (int column = 0; column < 2; column++) {
                    if (column == 0) {
                        cohabAttributes[row][column] = allCohab[row].getA_id1();
                    } else {
                        cohabAttributes[row][column] = allCohab[row].getA_id2();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cohabTable = new JTable(cohabAttributes, columnNames);
        JScrollPane cohabPane = new JScrollPane(cohabTable);
        cohabPane.setPreferredSize(new Dimension(700, 400));
        cohabView.add(cohabPane);
        viewFrame.add(cohabView);

        this.viewFrame.setVisible(true);
    }

    public void viewFeeds() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Feeds Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel feedsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable feedsTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("w_id");
        col.add("a_id");

        String[][] feedsAttributes;
        String[] columnNames = {"Worker ID", "Animal ID"};

        try {
            Feeds[] allFeeds = dbHandler.getFeedsInfo(col);
            feedsAttributes = new String[allFeeds.length][2];
            for (int row = 0; row < allFeeds.length; row++) {
                for (int column = 0; column < 2; column++) {
                    if (column == 0) {
                        feedsAttributes[row][column] = allFeeds[row].getW_id();
                    } else {
                        feedsAttributes[row][column] = allFeeds[row].getA_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        feedsTable = new JTable(feedsAttributes, columnNames);
        JScrollPane feedsPane = new JScrollPane(feedsTable);
        feedsPane.setPreferredSize(new Dimension(700, 400));
        feedsView.add(feedsPane);
        viewFrame.add(feedsView);

        this.viewFrame.setVisible(true);
    }

    public void viewLocated_at() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Located At Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);


        //create panel for table to be added to
        JPanel locatedView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable locatedTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("o_id");
        col.add("p_id");

        String[][] locatedAttributes;
        String[] columnNames = {"Order ID", "Storage Unit Place ID"};

        try {
            LocatedAt[] allLocated = dbHandler.getLocatedAtInfo(col);
            locatedAttributes = new String[allLocated.length][2];
            for (int row = 0; row < allLocated.length; row++) {
                for (int column = 0; column < 2; column++) {
                    if (column == 0) {
                        locatedAttributes[row][column] = allLocated[row].getO_id();
                    } else {
                        locatedAttributes[row][column] = allLocated[row].getP_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        locatedTable = new JTable(locatedAttributes, columnNames);
        JScrollPane locatedScrollPane = new JScrollPane(locatedTable);
        locatedScrollPane.setPreferredSize(new Dimension(700, 400));
        locatedView.add(locatedScrollPane);
        viewFrame.add(locatedView);

        this.viewFrame.setVisible(true);
    }

    public void viewMade_from() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Made From Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel madeView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable madeTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("a_id");
        col.add("name");
        col.add("o_id");

        String[][] madeAttributes;
        String[] columnNames = {"Animal ID", "Name", "Order ID"};

        try {
            MadeFrom[] allMade = dbHandler.getMadeFromInfo(col);
            madeAttributes = new String[allMade.length][3];
            for (int row = 0; row < allMade.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        madeAttributes[row][column] = allMade[row].getA_id();
                    } else if (column == 1) {
                        madeAttributes[row][column] = allMade[row].getName();
                    } else {
                        madeAttributes[row][column] = allMade[row].getO_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        madeTable = new JTable(madeAttributes, columnNames);
        JScrollPane madePane = new JScrollPane(madeTable);
        madePane.setPreferredSize(new Dimension(700, 400));
        madeView.add(madePane);
        viewFrame.add(madeView);

        this.viewFrame.setVisible(true);
    }

    public void viewMaintains_health() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Maintains Health Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel maintainsView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable maintainsTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("w_id");
        col.add("a_id");

        String[][] maintainsAttributes;
        String[] columnNames = {"Worker ID", "Animal ID"};

        try {
            MaintainsHealthOf[] allMaintains = dbHandler.getMaintainsHealthOfInfo(col);
            maintainsAttributes = new String[allMaintains.length][2];
            for (int row = 0; row < allMaintains.length; row++) {
                for (int column = 0; column < 2; column++) {
                    if (column == 0) {
                        maintainsAttributes[row][column] = allMaintains[row].getW_id();
                    } else {
                        maintainsAttributes[row][column] = allMaintains[row].getA_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        maintainsTable = new JTable(maintainsAttributes, columnNames);
        JScrollPane maintainsPane = new JScrollPane(maintainsTable);
        maintainsPane.setPreferredSize(new Dimension(700, 400));
        maintainsView.add(maintainsPane);
        viewFrame.add(maintainsView);

        this.viewFrame.setVisible(true);
    }

    public void viewStored_at() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Stored At Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel storedView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable storedTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("a_id");
        col.add("name");
        col.add("p_id");

        String[][] storedAttributes;
        String[] columnNames = {"Animal ID", "Name", "Place ID"};

        try {
            StoredAt[] allStored = dbHandler.getStoredAtInfo(col);
            storedAttributes = new String[allStored.length][3];
            for (int row = 0; row < allStored.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        storedAttributes[row][column] = allStored[row].getA_id();
                    } else if (column == 1) {
                        storedAttributes[row][column] = allStored[row].getName();
                    } else {
                        storedAttributes[row][column] = allStored[row].getP_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storedTable = new JTable(storedAttributes, columnNames);
        JScrollPane storedPane = new JScrollPane(storedTable);
        storedPane.setPreferredSize(new Dimension(700, 400));
        storedView.add(storedPane);
        viewFrame.add(storedView);

        this.viewFrame.setVisible(true);
    }

    public void viewWorks_at() {
        viewFrame = new JFrame();
        viewFrame.setTitle("Works At Table");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(800, 500);
        viewFrame.setLocationRelativeTo(null);

        //create panel for table to be added to
        JPanel worksAtView = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //1D to 2D array to fill JTable
        JTable worksAtTable;

        //database entries
        ArrayList<String> col = new ArrayList<>();
        col.clear();
        col.add("w_id");
        col.add("p_id");

        String[][] worksAtAttributes;
        String[] columnNames = {"Worker ID", "Place ID"};

        try {
            WorksAt[] allWorksAt = dbHandler.getWorksAtInfo(col);
            worksAtAttributes = new String[allWorksAt.length][3];
            for (int row = 0; row < allWorksAt.length; row++) {
                for (int column = 0; column < 3; column++) {
                    if (column == 0) {
                        worksAtAttributes[row][column] = allWorksAt[row].getW_id();
                    } else {
                        worksAtAttributes[row][column] = allWorksAt[row].getP_id();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        worksAtTable = new JTable(worksAtAttributes, columnNames);
        JScrollPane worksPane = new JScrollPane(worksAtTable);
        worksPane.setPreferredSize(new Dimension(700, 400));
        worksAtView.add(worksPane);
        viewFrame.add(worksAtView);

        this.viewFrame.setVisible(true);
    }

    public void projectVet() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Veterinarian Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forVetsProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        JCheckBox wid = new JCheckBox("Worker ID");
        wid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        JCheckBox name = new JCheckBox("Worker's Name");
        name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        JCheckBox payRate = new JCheckBox("Worker's Pay Rate");
        payRate.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 2
                col2 = true;
            }
        });
        JCheckBox addr = new JCheckBox("Worker's Address");
        addr.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 3
                col3 = true;
            }
        });
        JCheckBox email = new JCheckBox("Worker's Email");
        email.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 4
                col4 = true;
            }
        });
        JCheckBox phone = new JCheckBox("Worker's Phone #");
        phone.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 5
                col5 = true;
            }
        });
        JCheckBox spec = new JCheckBox("Worker's Specialization");
        spec.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 6
                col6 = true;
            }
        });

        //Button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.VET_W_ID);
                    numCols++;
                    colNames.add("Veterinarian's IDs");
                }
                if (col1) {
                    col.add(Constants.NAME);
                    numCols++;
                    colNames.add("Veterinarian's Names");
                }
                if (col2) {
                    col.add(Constants.PAY_RATE);
                    numCols++;
                    colNames.add("Veterinarian's Pay Rates");
                }
                if (col3) {
                    col.add(Constants.ADDRESS);
                    numCols++;
                    colNames.add("Veterinarian's Addresses");
                }
                if (col4) {
                    col.add(Constants.EMAIL);
                    numCols++;
                    colNames.add("Veterinarian's Emails");
                }
                if (col5) {
                    col.add(Constants.PHONE);
                    numCols++;
                    colNames.add("Veterinarian's Phone #s");
                }
                if (col6) {
                    col.add(Constants.SPECIALIZATION);
                    numCols++;
                    colNames.add("Veterinarian's Specializations");
                }

                    try {
                        String projection = String.join(", ", col);

                        Object[][] rawResults = dbHandler.getTableInfo(projection, "VETERINARIANS v, WORKERS w WHERE v.W_ID = w.W_ID");
                        String [][] VetAttributes = new String[rawResults.length][numCols];
                        for (int i = 0; i < rawResults.length; i++) {
                            for (int j = 0; j < rawResults[0].length; j++) {
                                VetAttributes[i][j] = String.valueOf(rawResults[i][j]);
                            }
                        }
//                        Veterinarian [] allVets = dbHandler.getVeterinarianInfo(col);
//                        String [][] VetAttributes = new String[allVets.length][numCols];
                        Object [] columnNames =  colNames.toArray();
//                        for (int i = 0; i < allVets.length; i++) {
//                            int j = 0;
//                            if (col0) {
//                                VetAttributes[i][j] = allVets[i].getW_id();
//                                j++;
//                            }
//                            if (col1) {
//                                VetAttributes[i][j] = allVets[i].getName();
//                                j++;
//                            }
//                            if (col2) {
//                                VetAttributes[i][j] = Float.toString(allVets[i].getPay_rate());
//                                j++;
//                            }
//                            if (col3) {
//                                VetAttributes[i][j] = allVets[i].getAddress();
//                                j++;
//                            }
//                            if (col4) {
//                                VetAttributes[i][j] = allVets[i].getEmail();
//                                j++;
//                            }
//                            if (col5) {
//                                VetAttributes[i][j] = allVets[i].getPhone();
//                                j++;
//                            }
//                            if (col6) {
//                                VetAttributes[i][j] = allVets[i].getSpecialization();
//                            }
//                        }
                        resetCols();
                        //Make JTable to display projected table
                        JTable vetProj = new JTable(VetAttributes, columnNames);
                        //Add JTable to scroll pane
                        JScrollPane scroll = new JScrollPane(vetProj);
                        scroll.setPreferredSize(new Dimension(700, 400));
                        //Call display
                        displayProjection(scroll);

                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add Checkboxes and button to panel
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

    public void projectStorage() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Storage Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forStorageProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox pid = new JCheckBox("Place ID");
        JCheckBox name = new JCheckBox("Name");
        JCheckBox temperature = new JCheckBox("Temperature");
        //create item listeners for each checkbox
        pid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        temperature.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });

        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.P_ID);
                    numCols++;
                    colNames.add("Place ID");
                }
                if (col1) {
                    col.add(Constants.NAME);
                    numCols++;
                    colNames.add("Name");
                }
                if (col2) {
                    col.add(Constants.TEMPERATURE);
                    numCols++;
                    colNames.add("Temperature");
                }

                try {
                    StorageUnit[] allStorage = dbHandler.getStorageUnitInfo(col);
                    String[][] storageAttributes = new String[allStorage.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for (int i = 0; i < allStorage.length; i++) {
                        int j = 0;
                        if (col0) {
                            storageAttributes[i][j] = allStorage[i].getP_id();
                            j++;
                        }
                        if (col1) {
                            storageAttributes[i][j] = allStorage[i].getName();
                            j++;
                        }
                        if (col2) {
                            storageAttributes[i][j] = allStorage[i].getTemperature() + "";
                        }
                    }
                    resetCols();
                    //make JTable to display projected table
                    JTable storageProj = new JTable(storageAttributes, columnNames);
                    //Add JTable to scroll pane
                    JScrollPane storagePane = new JScrollPane(storageProj);
                    storagePane.setPreferredSize(new Dimension(700, 400));
                    //call display
                    displayProjection(storagePane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button to panel
        forStorageProjection.add(pid);
        forStorageProjection.add(name);
        forStorageProjection.add(temperature);
        forStorageProjection.add(applyProj);

        //add panel to frame and display
        checkBoxFrame.add(forStorageProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectPreppedFood() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Prepped Food Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forFoodProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox aid = new JCheckBox("Animal ID");
        JCheckBox name = new JCheckBox("Name");
        JCheckBox weight = new JCheckBox("Weight");
        //create item listeners for each checkbox
        aid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        weight.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });
        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.A_ID);
                    numCols++;
                    colNames.add("Animal ID");
                }
                if (col1) {
                    col.add(Constants.NAME);
                    numCols++;
                    colNames.add("Name");
                }
                if (col2) {
                    col.add(Constants.WEIGHT);
                    numCols++;
                    colNames.add("Weight");
                }

                try {
                    PreppedFood[] allFood = dbHandler.getPreppedFoodInfo(col);
                    String[][] foodAttributes = new String[allFood.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for (int i = 0; i < allFood.length; i++) {
                        int j = 0;
                        if (col0) {
                            foodAttributes[i][j] = allFood[i].getA_id();
                            j++;
                        }
                        if (col1) {
                            foodAttributes[i][j] = allFood[i].getName();
                            j++;
                        }
                        if (col2) {
                            foodAttributes[i][j] = allFood[i].getWeight() + "";
                        }
                    }
                    resetCols();
                    //make JTable to display projected table
                    JTable foodProj = new JTable(foodAttributes, columnNames);
                    //add JTable to scroll pane
                    JScrollPane foodPane = new JScrollPane(foodProj);
                    foodPane.setPreferredSize(new Dimension(700, 400));
                    //call display
                    displayProjection(foodPane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button to panel
        forFoodProjection.add(aid);
        forFoodProjection.add(name);
        forFoodProjection.add(weight);
        forFoodProjection.add(applyProj);

        //add panel to frame and display frame
        checkBoxFrame.add(forFoodProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectRawFood() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Raw Food Orders Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forRawFoodProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox oid = new JCheckBox("Order ID");
        JCheckBox contents = new JCheckBox("Contents");
        JCheckBox weight = new JCheckBox("Weight");
        JCheckBox dateRec = new JCheckBox("Date Received");
        JCheckBox dateExp = new JCheckBox("Expiry Date");
        //create item listeners for each checkbox
        oid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        contents.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        weight.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });
        dateRec.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col3 = true;
            }
        });
        dateExp.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col4 = true;
            }
        });

        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.O_ID);
                    numCols++;
                    colNames.add("Order ID");
                }
                if (col1) {
                    col.add(Constants.CONTENTS);
                    numCols++;
                    colNames.add("Contents");
                }
                if (col2) {
                    col.add(Constants.WEIGHT);
                    numCols++;
                    colNames.add("Weight");
                }
                if (col3) {
                    col.add(Constants.DATE_RECEIVED);
                    numCols++;
                    colNames.add("Date Received");
                }
                if (col4) {
                    col.add(Constants.EXPIRY_DATE);
                    numCols++;
                    colNames.add("Expiry Date");
                }

                try {
                    RawFoodOrder[] allRawFood = dbHandler.getRawFoodOrderInfo(col);
                    String[][] rawFoodAttributes = new String[allRawFood.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for (int i = 0; i < allRawFood.length; i++) {
                        int j = 0;
                        if (col0) {
                            rawFoodAttributes[i][j] = allRawFood[i].getO_id();
                            j++;
                        }
                        if (col1) {
                            rawFoodAttributes[i][j] = allRawFood[i].getContents();
                            j++;
                        }
                        if (col2) {
                            rawFoodAttributes[i][j] = allRawFood[i].getWeight()+"";
                            j++;
                        }
                        if (col3) {
                            rawFoodAttributes[i][j] = allRawFood[i].getDate_received()+"";
                            j++;
                        }
                        if (col4) {
                            rawFoodAttributes[i][j] = allRawFood[i].getExpiry_date()+"";
                        }
                    }
                    resetCols();
                    //make JTable to display projected table
                    JTable rawFoodProj = new JTable(rawFoodAttributes,columnNames);
                    //add JTable to scroll pane
                    JScrollPane rawFoodPane = new JScrollPane(rawFoodProj);
                    rawFoodPane.setPreferredSize(new Dimension(700, 400));
                    //call display
                    displayProjection(rawFoodPane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button to panel
        forRawFoodProjection.add(oid);
        forRawFoodProjection.add(contents);
        forRawFoodProjection.add(weight);
        forRawFoodProjection.add(dateRec);
        forRawFoodProjection.add(dateExp);
        forRawFoodProjection.add(applyProj);

        //add panel to frame and display
        checkBoxFrame.add(forRawFoodProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectComputers() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Computers Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forComputersProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox cid = new JCheckBox("Computer ID");
        JCheckBox wid = new JCheckBox("Associated Worker ID");
        JCheckBox model = new JCheckBox("Model");
        JCheckBox manufacturer = new JCheckBox("Manufacturer");
        JCheckBox type = new JCheckBox("Type");
        //create item listeners for each checkbox
        cid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        wid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        model.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });
        manufacturer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col3 = true;
            }
        });
        type.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col4 = true;
            }
        });

        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.C_ID);
                    numCols++;
                    colNames.add("Computer ID");
                }
                if (col1) {
                    col.add(Constants.W_ID);
                    numCols++;
                    colNames.add("Associated Worker ID");
                }
                if (col2) {
                    col.add(Constants.MODEL);
                    numCols++;
                    colNames.add("Model");
                }
                if (col3) {
                    col.add(Constants.MANUFACTURER);
                    numCols++;
                    colNames.add("Manufacturer");
                }
                if (col4) {
                    col.add(Constants.TYPE);
                    numCols++;
                    colNames.add("Type");
                }
                try{
                    Computer[] allComputers = dbHandler.getComputerInfo(col);
                    String[][] computersAttributes = new String[allComputers.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for(int i=0;i< allComputers.length;i++){
                        int j = 0;
                        if(col0){
                            computersAttributes[i][j] = allComputers[i].getC_id();
                            j++;
                        }
                        if(col1){
                            computersAttributes[i][j] = allComputers[i].getW_id();
                            j++;
                        }
                        if(col2){
                            computersAttributes[i][j] = allComputers[i].getModel();
                            j++;
                        }
                        if(col3){
                            computersAttributes[i][j] = allComputers[i].getManufacturer();
                            j++;
                        }
                        if(col4){
                            computersAttributes[i][j] = allComputers[i].getType();
                        }
                    }
                    resetCols();
                    JTable computersProj = new JTable(computersAttributes,columnNames);
                    JScrollPane computersPane = new JScrollPane(computersProj);
                    computersPane.setPreferredSize(new Dimension(700, 400));
                    displayProjection(computersPane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button to panel
        forComputersProjection.add(cid);
        forComputersProjection.add(wid);
        forComputersProjection.add(model);
        forComputersProjection.add(manufacturer);
        forComputersProjection.add(type);
        forComputersProjection.add(applyProj);

        //add panel to frame and display
        checkBoxFrame.add(forComputersProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectAnimals() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Animals Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forAnimalsProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox aid = new JCheckBox("Animal ID");
        JCheckBox pid = new JCheckBox("Place ID");
        JCheckBox name = new JCheckBox("Name");
        JCheckBox species = new JCheckBox("Species");
        JCheckBox genus = new JCheckBox("Genus");
        //create item listeners for each checkbox
        aid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        pid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });
        species.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col3 = true;
            }
        });
        genus.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col4 = true;
            }
        });

        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.A_ID);
                    numCols++;
                    colNames.add("Animal ID");
                }
                if (col1) {
                    col.add(Constants.P_ID);
                    numCols++;
                    colNames.add("Place ID");
                }
                if (col2) {
                    col.add(Constants.NAME);
                    numCols++;
                    colNames.add("Name");
                }
                if (col3) {
                    col.add(Constants.SPECIES);
                    numCols++;
                    colNames.add("Species");
                }
                if (col4) {
                    col.add(Constants.GENUS);
                    numCols++;
                    colNames.add("Genus");
                }

                try{
                    Animal[] allAnimals = dbHandler.getAnimalInfo(col);
                    String[][] animalAttributes = new String[allAnimals.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for(int i = 0; i< allAnimals.length; i++) {
                        int j = 0;
                        if (col0) {
                            animalAttributes[i][j] = allAnimals[i].getA_id();
                            j++;
                        }
                        if (col1) {
                            animalAttributes[i][j] = allAnimals[i].getP_id();
                            j++;
                        }
                        if (col2) {
                            animalAttributes[i][j] = allAnimals[i].getName();
                            j++;
                        }
                        if (col3) {
                            animalAttributes[i][j] = allAnimals[i].getSpecies();
                            j++;
                        }
                        if (col4) {
                            animalAttributes[i][j] = allAnimals[i].getGenus();
                        }
                    }
                    resetCols();
                    JTable animalProj = new JTable(animalAttributes, columnNames);
                    JScrollPane animalPane = new JScrollPane(animalProj);
                    animalPane.setPreferredSize(new Dimension(700,400));
                    displayProjection(animalPane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button
        forAnimalsProjection.add(aid);
        forAnimalsProjection.add(pid);
        forAnimalsProjection.add(name);
        forAnimalsProjection.add(species);
        forAnimalsProjection.add(genus);
        forAnimalsProjection.add(applyProj);

        //add panel to frame and display
        checkBoxFrame.add(forAnimalsProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectHabitats() {
        checkBoxFrame = new JFrame();
        checkBoxFrame.setTitle("Habitats Projection");
        checkBoxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkBoxFrame.setSize(800, 500);
        checkBoxFrame.setLocationRelativeTo(null);

        //Pop up new window with checkboxes for attributes to be projected
        JPanel forHabitatsProjection = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Reset Projection Array
        resetCols();

        //create checkboxes for each attribute
        JCheckBox pid = new JCheckBox("Place ID");
        JCheckBox name = new JCheckBox("Name");
        JCheckBox biome = new JCheckBox("Biome");
        JCheckBox area = new JCheckBox("Area");
        JCheckBox temp = new JCheckBox("Temperature");
        JCheckBox humid = new JCheckBox("Humidity");
        //create item listeners for each checkbox
        pid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place zero
                col0 = true;
            }
        });
        name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //place 1
                col1 = true;
            }
        });
        biome.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col2 = true;
            }
        });
        area.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col3 = true;
            }
        });
        temp.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col4 = true;
            }
        });
        humid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                col5 = true;
            }
        });

        //create button to apply projection
        JButton applyProj = new JButton("Apply Projection");
        applyProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxFrame.dispose();
                int numCols = 0;
                ArrayList<String> col = new ArrayList<String>();
                ArrayList<String> colNames = new ArrayList<String>();
                col.clear();
                if (col0) {
                    col.add(Constants.P_ID);
                    numCols++;
                    colNames.add("Place ID");
                }
                if (col1) {
                    col.add(Constants.NAME);
                    numCols++;
                    colNames.add("Name");
                }
                if (col2) {
                    col.add(Constants.BIOME);
                    numCols++;
                    colNames.add("Biome");
                }
                if (col3) {
                    col.add(Constants.AREA);
                    numCols++;
                    colNames.add("Area");
                }
                if (col4) {
                    col.add(Constants.TEMPERATURE);
                    numCols++;
                    colNames.add("Temperature");
                }
                if (col5) {
                    col.add(Constants.HUMIDITY);
                    numCols++;
                    colNames.add("Humidity");
                }

                try{
                    Habitat[] allHabitats = dbHandler.getHabitatInfo(col);
                    String[][] habitatsAttributes = new String[allHabitats.length][numCols];
                    Object[] columnNames = colNames.toArray();
                    for(int i = 0; i< allHabitats.length; i++) {
                        int j = 0;
                        if(col0){
                            habitatsAttributes[i][j] = allHabitats[i].getP_id();
                            j++;
                        }
                        if(col1){
                            habitatsAttributes[i][j] = allHabitats[i].getName();
                            j++;
                        }
                        if(col2){
                            habitatsAttributes[i][j] = allHabitats[i].getBiome();
                            j++;
                        }
                        if(col3){
                            habitatsAttributes[i][j] = allHabitats[i].getArea()+"";
                            j++;
                        }
                        if(col4){
                            habitatsAttributes[i][j] = allHabitats[i].getTemperature()+"";
                            j++;
                        }
                        if(col5){
                            habitatsAttributes[i][j] = allHabitats[i].getHumidity()+"";
                        }
                    }
                    resetCols();
                    JTable habitatsProj = new JTable(habitatsAttributes, columnNames);
                    JScrollPane habitatsPane = new JScrollPane(habitatsProj);
                    habitatsPane.setPreferredSize(new Dimension(700, 400));
                    displayProjection(habitatsPane);
                } catch (SQLException ex) {
                    displayError("Something went wrong");
                }
            }
        });

        //add checkboxes and button to panel
        forHabitatsProjection.add(pid);
        forHabitatsProjection.add(name);
        forHabitatsProjection.add(biome);
        forHabitatsProjection.add(area);
        forHabitatsProjection.add(temp);
        forHabitatsProjection.add(humid);
        forHabitatsProjection.add(applyProj);

        //add panel to frame and display
        checkBoxFrame.add(forHabitatsProjection);
        this.checkBoxFrame.setVisible(true);
    }

    public void projectWorkers() {

    }

    public void projectZookeepers() {

    }

    public void projectVendors() {

    }

    public void projectShops() {

    }

    public void projectItems() {

    }

    public void projectAssigned_to() {

    }

    public void projectcohab() {

    }

    public void projectFeeds() {

    }

    public void projectLocated() {

    }

    public void projectMade() {

    }

    public void projectMaintains() {

    }

    public void projectStoredAt() {

    }

    public void projectWorksAt() {

    }

    public void displayProjection(JScrollPane table) {
        projectionFrame = new JFrame("Projected Table");
        projectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        projectionFrame.setSize(800, 500);
        projectionFrame.setLocationRelativeTo(null);

        JPanel toProject = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toProject.add(table);
        projectionFrame.add(toProject);
        this.projectionFrame.setVisible(true);

    }

    //Reset the values of projectionArray before each use
    public void resetCols() {
        col0 = false;
        col1 = false;
        col2 = false;
        col3 = false;
        col4 = false;
        col5 = false;
        col6 = false;
    }

    //For selection
    public void selectComputer() {
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
                try {
                    String inputtedMan = userMan.getText();
                    userSelectionFrame.dispose();
                    if (inputtedMan.isEmpty()) {
                        displayError("You did not enter a manufacturer");
                    } else {
                        Computer[] validComputers = dbHandler.selectManufacturer(inputtedMan);
                        showSelectionFrame = new JFrame("Selected Computers");
                        showSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        showSelectionFrame.setSize(800, 500);
                        showSelectionFrame.setLocationRelativeTo(null);

                        JPanel showComputers = new JPanel(new FlowLayout(FlowLayout.CENTER));

                        String[] colNames = {"Computer ID", "Worker ID", "Model", "Manufacturer", "Type"};
                        String[][] compAttributes = new String[validComputers.length][colNames.length];

                        for (int row = 0; row < validComputers.length; row++) {
                            for (int col = 0; col < colNames.length; col++) {
                                if (col == 0) {
                                    compAttributes[row][col] = validComputers[row].getC_id();
                                } else if (col == 1) {
                                    if (validComputers[row].getW_id().isEmpty()) {
                                        compAttributes[row][col] = "N/A";
                                    } else {
                                        compAttributes[row][col] = validComputers[row].getW_id();
                                    }
                                } else if (col == 2) {
                                    compAttributes[row][col] = validComputers[row].getModel();
                                } else if (col == 3) {
                                    compAttributes[row][col] = validComputers[row].getManufacturer();
                                } else {
                                    compAttributes[row][col] = validComputers[row].getType();
                                }
                            }
                        }

                        JTable compSelectionTable = new JTable(compAttributes, colNames);
                        JScrollPane compSelectionPane = new JScrollPane(compSelectionTable);
                        compSelectionPane.setPreferredSize(new Dimension(700, 400));

                        showComputers.add(compSelectionPane);
                        showSelectionFrame.add(showComputers);
                        showSelectionFrame(); //Unable to do it in try catch I think?

                    }
                } catch (Error | SQLException selectionError) {
                    userSelectionFrame.dispose();
                    displayError("You did not enter a valid manufacturer");
                }
            }
        });

    }


    public void aggregationGroupBy() {
        //Instantiate Frame
        aggregationGroupByFrame = new JFrame("Aggregation With Group By");
        aggregationGroupByFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aggregationGroupByFrame.setSize(800, 500);
        aggregationGroupByFrame.setLocationRelativeTo(null);


        JLabel text = new JLabel("Sum the weights of raw food orders grouped by their storage unit.");
        JPanel displayAggregation = new JPanel();

        try {
            SumWeights[] allSums = dbHandler.getSumWeights();
            String[] colNames = {"Place ID", "Name", "Weight Sum"}; //Name can be null
            String[][] sumData = new String[allSums.length][colNames.length];
            for (int r = 0; r < allSums.length; r++) {
                for (int c = 0; c < colNames.length; c++) {
                    if (c == 0) {
                        sumData[r][c] = allSums[r].getP_id();
                    } else if (c == 1) {
                        String isNull = allSums[r].getName();
                        if (isNull.isEmpty()) {
                            sumData[r][c] = "N/A";
                        } else {
                            sumData[r][c] = isNull;
                        }
                    } else {
                        sumData[r][c] = allSums[r].getSumWeight() + "";
                    }
                }
            }

            //Create table and pane
            JTable aggregationTable = new JTable(sumData, colNames);
            JScrollPane aggregationScroll = new JScrollPane(aggregationTable);
            aggregationScroll.setPreferredSize(new Dimension(700, 400));

            //Add table/pane and text to panel
            displayAggregation.add(text);
            displayAggregation.add(aggregationScroll);

            //Add panel to frame
            aggregationGroupByFrame.add(displayAggregation);

            //Display frame
            showAggregationGroupByFrame();

        } catch (SQLException e) {
            displayError("Something went wrong!");
        }

    }

    public void aggregationHaving(){
        //Instantiate frame
        aggregationHavingFrame = new JFrame("Aggregation With Having");
        aggregationHavingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aggregationHavingFrame.setSize(800, 500);
        aggregationHavingFrame.setLocationRelativeTo(null);

        //Create Label
        JLabel text = new JLabel("Storage units found that meets restriction of weight < 50.");
        //Create Panel
        JPanel displayAggregation = new JPanel();

        try {
            SumWeights[] allSums = dbHandler.getFreeStorage();
            String[] colNames = {"Place ID", "Name", "Weight Sum"}; //Column names
            String[][] sumData = new String[allSums.length][colNames.length];

            //Add
            for (int r = 0; r < allSums.length; r++) {
                for (int c = 0; c < colNames.length; c++) {
                    if (c == 0) {
                        sumData[r][c] = allSums[r].getP_id();
                    } else if (c == 1) {
                        String isNull = allSums[r].getName();
                        if (isNull.isEmpty()) {
                            sumData[r][c] = "N/A";
                        } else {
                            sumData[r][c] = isNull;
                        }
                    } else {
                        sumData[r][c] = allSums[r].getSumWeight() + "";
                    }
                }
            }

            //Create table and pane
            JTable aggregationTable = new JTable(sumData, colNames);
            JScrollPane aggregationScroll = new JScrollPane(aggregationTable);
            aggregationScroll.setPreferredSize(new Dimension(700, 400));

            //Add table/pane and text to panel
            displayAggregation.add(text);
            displayAggregation.add(aggregationScroll);

            //Add panel to frame
            aggregationHavingFrame.add(displayAggregation);

            //Display frame
            showAggregationHavingFrame();

        } catch (SQLException e) {
            displayError("Something went wrong!");
        }

    }

    public void division() {
        //Instantiate Frame
        divisionFrame = new JFrame("Get Zookeeper That Feeds All The Animals");
        divisionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        divisionFrame.setSize(800, 500);
        divisionFrame.setLocationRelativeTo(null);


        JLabel text = new JLabel("Find super zookeepers that feed every animal in the zoo.");
        JPanel displayDivision = new JPanel();

        try {
            Zookeeper[] allSuper = dbHandler.getSuperZookeepers();
            String[] colNames = {"ID", "Name", "Pay Rate", "Address", "Email", "Phone"};
            String[][] superData = new String[allSuper.length][colNames.length];
            for (int i = 0; i < allSuper.length; i++) {
                superData[i][0] = String.valueOf(allSuper[i].getW_id());
                superData[i][1] = String.valueOf(allSuper[i].getName());
                superData[i][2] = String.valueOf(allSuper[i].getPay_rate());
                superData[i][3] = String.valueOf(allSuper[i].getAddress());
                superData[i][4] = String.valueOf(allSuper[i].getEmail());
                superData[i][5] = String.valueOf(allSuper[i].getPhone());
            }

            //Create table and pane
            JTable divisionTable = new JTable(superData, colNames);
            JScrollPane divisionScroll = new JScrollPane(divisionTable);
            divisionScroll.setPreferredSize(new Dimension(700, 400));

            //Add table/pane and text to panel
            displayDivision.add(text);
            displayDivision.add(divisionScroll);

            //Add panel to frame
            divisionFrame.add(displayDivision);

            //Display frame
            showdivisionFrame();

        } catch (SQLException e) {
            displayError("Something went wrong!");
        }

    }


    public void show() {
        this.defaultFrame.setVisible(true);
    }

    public void showSelectionFrame() {
        this.showSelectionFrame.setVisible(true);
    }

    public void showAggregationGroupByFrame() {
        this.aggregationGroupByFrame.setVisible(true);
    }

    public void showdivisionFrame() {
        this.divisionFrame.setVisible(true);
    }

    public void showAggregationHavingFrame(){this.aggregationHavingFrame.setVisible((true));}
}
