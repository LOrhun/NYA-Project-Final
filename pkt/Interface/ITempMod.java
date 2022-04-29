package pkt.Interface;

public interface ITempMod {
    public void set_TargetTemp(double target_temp);
    public void set_RoomTemp(double room_temp);
    public void set_Override(int Override);

    public boolean stat_ModuleStatus();

    public int get_CurrentStatus();
    
    public void heatExchange();
}
