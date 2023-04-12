package edu.duke.ece651.riscgame.commuMedium;

import java.io.Serializable;

import edu.duke.ece651.riscgame.order.Order;

/*
 * This class is used to send the action order to the server
 * Order check will be done at the server side
 */

public class ActionInfo implements Serializable{
    private Order order;

    public ActionInfo(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
    

}
