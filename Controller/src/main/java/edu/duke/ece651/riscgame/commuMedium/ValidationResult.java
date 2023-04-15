package edu.duke.ece651.riscgame.commuMedium;

import java.io.Serializable;

public class ValidationResult implements Serializable {
    private String errMessage;
    private boolean isCommitted;
    public ValidationResult(String err, boolean isCommitted) {
        this.errMessage = err;
        this.isCommitted = isCommitted;
    }
    public String getErrMessage () {return errMessage;}

    public boolean isCommitted() {
        return isCommitted;
    }

    /**
     * describe whether the order is illegal
     * @return return true when there is no err message but false with err message
     */
    public boolean isLegal () {
        return errMessage == null;
    }
}
