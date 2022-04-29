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

    private double targetTemp = 25;
    
    public virtualServerConnection(){
        users.add(new User("test", "test", 1, false, 20, 20, false, false));
        users.add(new User("test2", "test2", 2, false, 20, 20, false, false));
        users.add(new User("test3", "test3", 3, false, 20, 20, false, false));
    }

    private void notify(double target_temp){
        observers.forEach(observer -> observer.update(target_temp));
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
    public void setCoolingStatus(boolean status) {
        users.forEach(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setCoolingStatus(user, status);}});
    }

    @Override
    public void setHeatingStatus(boolean status) {
        users.forEach(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){User.setHeatingStatus(user, status);}});
    }

    @Override
    public boolean getCoolingStatus() {
        return users.stream().anyMatch(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){return User.getCoolingStatus(user);} else {return false;}});
    }

    @Override
    public boolean getHeatingStatus() {
        return users.stream().anyMatch(user -> {if (User.getUsername(user).equals(this.username) & User.getPassword(user).equals(this.password)){return User.getHeatingStatus(user);} else {return false;}});
    }

}
