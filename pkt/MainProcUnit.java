package pkt;

import java.util.concurrent.TimeUnit;

import pkt.Interface.*;
import pkt.Modules.*;

public class MainProcUnit {
    //*Runs TemperatureUnit function every 100 + 1900 millisecs
    Runnable TUnit = new Runnable() {public void run(){while(!threadShouldStop){ try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}TemperatureUnit(); try {Thread.sleep(1400);} catch (InterruptedException e) {e.printStackTrace();}}}};
    
    private Thread modulesThread = new Thread(TUnit, "TUnit");
    private boolean threadShouldStop = false;

    private int IdleCounter;
    private Room room;
    private IConnection connection;
    private ITempMod tMod;
    private ITempReader tReader;
    private IConsole console;

    //! Get multiline editing online!
    //TODO: Add main menu
    //TODO: Add console editor

    public MainProcUnit() {
        console = new MConsoleEditor();
        room = new Room();
        tMod = new MTemperatureModulator(room);
        tReader = new MTemperatureReader(room);

        console.menu_status("SELF_CHECK");
        if (tMod.stat_ModuleStatus() ^ tReader.stat_ModuleStatus()) {
            console.menu_error("SELF_CHECK_FAILED");
            System.exit(1);
        }
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

    }

    private void TemperatureUnit(){     
        connection.setRoomTemp(tReader.get_RoomTemp());

        tMod.set_RoomTemp(tReader.get_RoomTemp());
        tMod.set_TargetTemp(connection.getTargetTemp());
        tMod.heatExchange();

        connection.setCoolingStatus((tMod.get_CurrentStatus() == 2) ? true : false);
        connection.setHeatingStatus((tMod.get_CurrentStatus() == 1) ? true : false);

        IdleCounter = ((tMod.get_CurrentStatus() == 0) ? IdleCounter + 1 : 0);
        console.thread_Update(tReader.get_RoomTemp(), connection.getTargetTemp());
        console.thread_MenuType((console.menu_getMainSelection() == 1) ? 0 : ((IdleCounter > 5) ? 2 : 1));
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
        modulesThread.start();
        
        while (console.menu_main() != 5){
            console.thread_MenuType(1);
            connection.setModuleStatus(true);
            switch (console.menu_getMainSelection()){
                case 1:
                    modulesThread.interrupt();
                    tMod.set_Override(0);
                    console.thread_MenuType(0);
                    connection.setTargetTemp(console.menu_newTarget(connection.getTargetTemp()));
                    modulesThread.start();
                    break;
                case 2:
                    tMod.set_Override((pressedBefore_2 == true) ? 3 : 1);
                    pressedBefore_2 = !pressedBefore_2;
                    break;
                case 3:
                    tMod.set_Override((pressedBefore_3 == true) ? 3 : 2);
                    pressedBefore_3 = !pressedBefore_3;
                    break;
                default:
                    break;
            }

        }

        connection.setModuleStatus(false);
        threadShouldStop = true;
        connection.disconnect();
        console.clearConsole();
    }
}
