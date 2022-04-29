package pkt.Interface;

public interface IConnection {
    public boolean login_username(String username);
    public boolean login_password(String password);

    public void setModuleStatus(boolean moduleStatus);
    public void setTargetTemp(double targetTemp);
    public void setRoomTemp(double roomTemp);

    public boolean getModuleStatus();
    public double getTargetTemp();
    public double getRoomTemp();

    public void setCoolingStatus(boolean status);
    public void setHeatingStatus(boolean status);
    public boolean getCoolingStatus();
    public boolean getHeatingStatus();

    public void disconnect();
    public void addObserver(IObserver observer);
}
