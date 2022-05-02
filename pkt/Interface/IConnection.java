package pkt.Interface;

public interface IConnection {
    public boolean login_username(String username);
    public boolean login_password(String password);

    public void setModuleStatus(boolean moduleStatus);
    public void setTargetTemp(double targetTemp);
    public void setRoomTemp(double roomTemp);
    public void setOverride(int override);

    public boolean getModuleStatus();
    public double getTargetTemp();
    public double getRoomTemp();
    public int getOverride();


    public void disconnect();
    public void addObserver(IObserver observer);
}
