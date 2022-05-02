package pkt.Connection.VirtualDB;

public class User {
    private String username;
    private String password;

    private int unitID;
    private boolean module_status;

    private double target_temp;
    private double room_temp;

    private boolean cooling_status;
    private boolean heating_status;
    private boolean override;

    
    public User(String username, String password, int unitID, boolean module_status, double target_temp,
            double room_temp, boolean cooling_status, boolean heating_status, boolean override) {
        this.username = username;
        this.password = password;
        this.unitID = unitID;
        this.module_status = module_status;
        this.target_temp = target_temp;
        this.room_temp = room_temp;
        this.cooling_status = cooling_status;
        this.heating_status = heating_status;
        this.override = override;
    }
    
    public static String getUsername(User user) {
        return user.username;
    }
    
    public static void setUsername(User user, String username) {
        user.username = username;
    }
    
    public static String getPassword(User user) {
        return user.password;
    }
    
    public static void setPassword(User user, String password) {
        user.password = password;
    }
    
    public static int getUnitID(User user) {
        return user.unitID;
    }
    
    public static void setUnitID(User user, int unitID) {
        user.unitID = unitID;
    }
    
    public static boolean getModule_status(User user) {
        return user.module_status;
    }
    
    public static void setModule_status(User user, boolean module_status) {
        user.module_status = module_status;
    }
    
    public static double getTarget_temp(User user) {
        return user.target_temp;
    }
    
    public static void setTarget_temp(User user, double target_temp) {
        user.target_temp = target_temp;
    }
    
    public static double getRoom_temp(User user) {
        return user.room_temp;
    }
    
    public static void setRoom_temp(User user, double room_temp) {
        user.room_temp = room_temp;
    }

    public static void setCoolingStatus(User user, boolean cooling_status) {
        user.cooling_status = cooling_status;
    }

    public static void setHeatingStatus(User user, boolean heating_status) {
        user.heating_status = heating_status;
    }

    public static boolean getCoolingStatus(User user) {
        return user.cooling_status;
    }

    public static boolean getHeatingStatus(User user) {
        return user.heating_status;
    }

    public static boolean setOverride(User user, boolean override) {
        user.override = override;
        return user.override;
    }

    public static boolean getOverride(User user) {
        return user.override;
    }

}
