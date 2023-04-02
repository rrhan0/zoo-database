package controller;

import UI.JWindow;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import database.DatabaseConnectionHandler;
import model.*;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Arrays;

import util.Constants;

public class ZooController {
    public static void main(String[] args) {
        //Run this program on the Event Dispatch Thread (EDT)
        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        dbHandler.login(System.getenv("USER"), System.getenv("PASSWORD"));
        ArrayList<String> col = new ArrayList<String>();
        col.add(Constants.C_ID);
        col.add(Constants.W_ID);
        col.add(Constants.MODEL);
        col.add(Constants.MANUFACTURER);
        col.add(Constants.TYPE);
        Computer[] result = dbHandler.getComputerInfo(col);

        col.clear();
        col.add(Constants.A_ID);
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.SPECIES);
        col.add(Constants.GENUS);

        Animal[] resulta = dbHandler.getAnimalInfo(col);

        col.clear();
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.BIOME);
        col.add(Constants.AREA);
        col.add(Constants.TEMPERATURE);
        col.add(Constants.HUMIDITY);

        Habitat[] resulth = dbHandler.getHabitatInfo(col);

        col.clear();
        col.add(Constants.I_ID);
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.STOCK);
        col.add(Constants.PRICE);

        Item[] resulti = dbHandler.getItemInfo(col);

        col.clear();
        col.add(Constants.A_ID);
        col.add(Constants.NAME);
        col.add(Constants.WEIGHT);

        PreppedFood[] resultp = dbHandler.getPreppedFoodInfo(col);

        col.clear();
        col.add(Constants.O_ID);
        col.add(Constants.CONTENTS);
        col.add(Constants.WEIGHT);
        col.add(Constants.DATE_RECEIVED);
        col.add(Constants.EXPIRY_DATE);

        RawFoodOrder[] resulto = dbHandler.getRawFoodOrderInfo(col);

        col.clear();
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.TYPE);

        Shop[] results = dbHandler.getShopInfo(col);

        col.clear();
        col.add(Constants.P_ID);
        col.add(Constants.NAME);
        col.add(Constants.TEMPERATURE);

        StorageUnit[] resultsu = dbHandler.getStorageUnitInfo(col);


        col.clear();
        col.add(Constants.W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        Worker[] resultw = dbHandler.getWorkerInfo(col);

        col.clear();
        col.add(Constants.VENDOR_W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        Worker[] resultven = dbHandler.getVendorInfo(col);

        col.clear();
        col.add(Constants.ZOO_W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);

        Worker[] resultz = dbHandler.getZookeeperInfo(col);

        col.clear();
        col.add(Constants.VET_W_ID);
        col.add(Constants.NAME);
        col.add(Constants.PAY_RATE);
        col.add(Constants.ADDRESS);
        col.add(Constants.EMAIL);
        col.add(Constants.PHONE);
        col.add(Constants.SPECIALIZATION);

        Worker[] resultvet = dbHandler.getVeterinarianInfo(col);
        // be careful of duplicates
//        Veterinarian vet = new Veterinarian("asdf", "Richardo", 11f, "11 nowhere street", "asdf@gmail.com", "1234", "Trolling");
//        dbHandler.insertVeterinarian(vet);
//        dbHandler.deleteAnimal("1002");

        SwingUtilities.invokeLater(() -> {
            JWindow frame1 = new JWindow();

        });

    }
}
