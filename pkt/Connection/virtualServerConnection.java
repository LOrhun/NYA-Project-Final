package pkt.Connection;

import java.util.ArrayList;

import pkt.Connection.VirtualDB.User;
import pkt.Interface.IConnection;
import pkt.Interface.IObserver;

public class virtualServerConnection implements IConnection{
    
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();

    private String username;
    private String password;

    private double targetTemp = 0;
    private int override = 0;
    
    public virtualServerConnection(){
        users.add(new User("test", "test", 1, false, 20, 20, false, false, false));
        users.add(new User("test2", "test2", 2, false, 20, 20, false, false, false));
        users.add(new User("test3", "test3", 3, false, 20, 20, false, false, false));
    }

    private void notify(double target_temp){
        observers.forEach(observer -> observer.update(target_temp, this.override));
    }

    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);        
    }

    @Override
    public boolean login_username(String username) {
        return users.stream().anyMatch(user -> {if (User.getUsername(user).equals(username)){this.username = User.getUsername(user); return true;} else {return false;}});
    }

    @Override
    public boolean login_password(String password) {
        return users.stream().anyMatch(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(password)){this.password = User.getPassword(user); return true;} else {return false;}});
    }

    @Override
    public void setModuleStatus(boolean moduleStatus) {
        users.forEach(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setModule_status(user, moduleStatus);}});
    }

    @Override
    public void setTargetTemp(double targetTemp) {
        users.forEach(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setTarget_temp(user, targetTemp);}});
    }

    @Override
    public void setRoomTemp(double roomTemp) {
        users.forEach(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setRoom_temp(user, roomTemp);}});
    }

    @Override
    public boolean getModuleStatus() {
        return users.stream().anyMatch(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){return User.getModule_status(user);} else {return false;}});
    }

    @Override
    public double getTargetTemp() {
        for (User user : users) {
            if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){if (this.targetTemp != User.getTarget_temp(user)){notify(User.getTarget_temp(user)); this.targetTemp = User.getTarget_temp(user);} return User.getTarget_temp(user);} 
        }
        return -999999;
    }

    @Override
    public double getRoomTemp() {
        for (User user : users) {
            if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){return User.getRoom_temp(user);} 
        }
        return -999999;  
    }

    @Override
    public void disconnect() {}

    @Override
    public void setOverride(int override) {
        boolean b_override, cooling, heating;
        switch (override){
            case 0:
                b_override = false;
                cooling = false;
                heating = false;
                break;
            case 1:
                b_override = true;
                cooling = true;
                heating = false;
                break;
            case 2:
                b_override = true;
                cooling = false;
                heating = true;
                break;
            case 3:
                b_override = true;
                cooling = false;
                heating = false;
                break;
            default:
                b_override = false;
                cooling = false;
                heating = false;
                break;
        }

        for (User user : users) {
            if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setOverride(user, b_override); User.setCoolingStatus(user, cooling); User.setHeatingStatus(user, heating);} 
        }
    }

    @Override
    public int getOverride() {
        boolean b_override = false, cooling = false, heating = false;
        for (User user : users) {
            if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){b_override = User.getOverride(user); cooling = User.getCoolingStatus(user); heating = User.getHeatingStatus(user);} 
        }

        if (b_override == true) {
            if (cooling == true) {if (this.override != 1){this.override = 1; notify(this.targetTemp);} return 1;} 
            else if (heating == true) {if (this.override != 2){this.override = 2; notify(this.targetTemp);} return 2;}
            else {if (this.override != 3){this.override = 3; notify(this.targetTemp);} return 3;}
        }
        else {
            if (this.override != 0){this.override = 0; notify(this.targetTemp);}
            return 0;
        }
    }

}
