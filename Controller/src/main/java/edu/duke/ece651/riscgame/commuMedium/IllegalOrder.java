package edu.duke.ece651.riscgame.commuMedium;

public class IllegalOrder {
    private String errMessage;
    public IllegalOrder (String err) {
        this.errMessage = err;
    }
    public String getErrMessage () {return errMessage;}

    /**
     * describe whether the order is illegal
     * @return return true when there is no err message but false with err message
     */
    public boolean isLegal () {
        return errMessage == null;
    }
}
