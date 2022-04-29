package pkt;

import pkt.Interface.IConnection;
import pkt.Connection.*;

public final class ConFactory {
    public static IConnection ConnectionFactory(String type){
        if (type.toLowerCase().equals("virtual")){return new virtualServerConnection();}
        else if (type.toLowerCase().equals("postgres")){return new postgreSQLConnection();}
        else {return new postgreSQLConnection();}
    }
}
