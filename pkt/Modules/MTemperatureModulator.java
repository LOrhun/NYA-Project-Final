package pkt.Modules;

import java.util.Random;

import pkt.Room;
import pkt.Interface.IObserver;

public class MTemperatureModulator implements IObserver {
    
    private double target_temp;
    private double room_temp;
    private int Override;

    private boolean stat_cooling;
    private boolean stat_heating;
    
    private Random random = new Random();
    private Room room;

    public MTemperatureModulator(Room room){
        this.room = room;
        this.Override = 0;
    }

    private void stop_heating(){stat_heating = false;}
    private void stop_cooling(){stat_cooling = false;}
    private void start_heating(){stat_heating = true; room.setRoomTemp(room.getRoomTemp() + random.nextInt(50) / 100.0);}
    private void start_cooling(){stat_cooling = true; room.setRoomTemp(room.getRoomTemp() - random.nextInt(50) / 100.0);}

    @Override
    public void set_TargetTemp(double target_temp) {
        this.target_temp = target_temp;
    }

    @Override
    public void set_RoomTemp(double room_temp) {
        this.room_temp = room_temp;
    }

    @Override
    public void set_Override(int Override) {
        this.Override = Override;
    }

    @Override
    public boolean stat_ModuleStatus() {
        return ((stat_cooling & stat_heating) ? true : false);
    }

    @Override
    public int get_CurrentStatus() {
        if (stat_heating){return 1;}
        else if (stat_cooling){return 2;}
        else {return 0;}
    }

    @Override
    public void heatExchange() {
        if (Override == 0){
            if (room_temp - target_temp > 0.25){stop_heating();start_cooling();}
            else if (room_temp - target_temp < -0.25){start_heating();stop_cooling();}
            else {stop_heating();stop_cooling();}
        }
        else{
            switch (Override){
                case 1: //* Cooling
                stop_heating();
                start_cooling(); 
                break;
                case 2: //*Heating
                start_heating();
                stop_cooling();
                break;
                case 3: //* Stop
                stop_cooling();
                stop_heating();
                break;
            }
        }
    }

    @Override
    public void update(double targetTemp) {
        this.target_temp = targetTemp;
    }
}
