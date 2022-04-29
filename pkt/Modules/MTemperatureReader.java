package pkt.Modules;

import java.util.concurrent.ThreadLocalRandom;

import pkt.Room;
import pkt.Interface.ITempReader;

public class MTemperatureReader implements ITempReader{
    private Room room;

    public MTemperatureReader(Room room){
        this.room = room;
        room.setRoomTemp(ThreadLocalRandom.current().nextDouble(18, 35));
    }

    @Override
    public double get_RoomTemp() {
        return room.getRoomTemp();
    }

    @Override
    public boolean stat_ModuleStatus() {
        return ((room.getRoomTemp() > 70 | room.getRoomTemp() < -70) ? true : false);
    }
}
