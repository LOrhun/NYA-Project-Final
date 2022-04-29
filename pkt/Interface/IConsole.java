package pkt.Interface;

public interface IConsole {
    public void thread_Update(double room_temp, double target_temp);
    public void thread_MenuType(int menu_type);

    public int menu_main();
    public int menu_getMainSelection();
    public String menu_login(int login_step);
    public String menu_connectionType();
    public boolean menu_close();
    
    public void clearConsole();
    public void menu_status(String status);
    public void menu_error(String status);
    public double menu_newTarget(double oldTarget_temp);

    public void close();
}
