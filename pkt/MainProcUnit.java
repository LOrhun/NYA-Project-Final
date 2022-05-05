package pkt;

import java.util.concurrent.TimeUnit;

import pkt.Interface.*;
import pkt.Modules.*;

public class MainProcUnit {
    //*Runs TemperatureUnit function every 100 + 1900 millisecs
    Runnable TUnit = new Runnable() {
        public void run(){
            while(!threadShouldStop)
            { 
                try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                TemperatureUnit(interruptThread);
                try {Thread.sleep(1400);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    };
    
    private Thread modulesThread = new Thread(TUnit, "TUnit");
    private boolean threadShouldStop = false, interruptThread = false;
    
    private int IdleCounter;
    private Room simulatedRoom;
    private IConnection connection;
    private IObserver tMod;
    private ITempReader tReader;
    private IConsole console;

    //! Get multiline editing online!
    //TODO: Add main menu
    //TODO: Add console editor

    public MainProcUnit() {
        console = new MConsoleEditor();
        simulatedRoom = new Room();
        tMod = new MTemperatureModulator(simulatedRoom);
        tReader = new MTemperatureReader(simulatedRoom);

        console.menu_status("SELF_CHECK");
        if (tMod.stat_ModuleStatus() ^ tReader.stat_ModuleStatus()) {
            console.menu_error("SELF_CHECK_FAILED");
            System.exit(1);
        }
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

    }

    private void TemperatureUnit(boolean interruptThread){     
        if (!interruptThread){
            connection.setRoomTemp(tReader.get_RoomTemp());

            tMod.set_RoomTemp(tReader.get_RoomTemp());
            tMod.heatExchange();
    
            //tMod.set_Override(connection.getOverride()); //! Add this to observer update method and remove it from here
            connection.getOverride();
    
            IdleCounter = ((tMod.get_CurrentStatus() == 0) ? IdleCounter + 1 : 0);
            console.thread_Update(tReader.get_RoomTemp(), connection.getTargetTemp());
            console.thread_MenuType((IdleCounter > 5) ? 2 : 1);
        }
    }


    private void logon(){
        boolean connect = false;
        while (connect == false){
            connect = connection.login_username(console.menu_login(1));
        }
        connect = false;
        while (connect == false){
            connect = connection.login_password(console.menu_login(2));
        }
        connection.getModuleStatus();
    }
    
    public void MPU_run(){
        
        connection = ConFactory.ConnectionFactory(console.menu_connectionType());
        logon();
        console.menu_status("CONNECTING");
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
        
        boolean pressedBefore_2 = false, pressedBefore_3 = false;
        connection.addObserver(tMod);
        modulesThread.start();

        while (console.menu_main(connection.getOverride()) != 5){
            console.thread_MenuType(1);
            connection.setModuleStatus(true);
            switch (console.menu_getMainSelection()){
                case 1:
                    interruptThread = true;
                    connection.setOverride(0);
                    console.thread_MenuType(0);
                    connection.setTargetTemp(console.menu_newTarget(connection.getTargetTemp()));
                    interruptThread = false;
                    break;
                case 2:
                    connection.setOverride((pressedBefore_2 == true) ? 3 : 1);
                    pressedBefore_2 = !pressedBefore_2;
                    break;
                case 3:
                    connection.setOverride((pressedBefore_3 == true) ? 3 : 2);
                    pressedBefore_3 = !pressedBefore_3;
                    break;
                default:
                    break;
            }

        }

        connection.setModuleStatus(false);
        connection.setOverride(0);
        threadShouldStop = true;
        connection.disconnect();
        console.clearConsole();
    }
}
