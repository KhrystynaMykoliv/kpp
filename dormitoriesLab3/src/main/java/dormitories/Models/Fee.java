package dormitories.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;

public class Fee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private double amount;
    @Expose
    private boolean isDiscount;

    public Fee() {}

    public Fee(double amount, boolean isDiscount) {
        this.amount = amount;
        this.isDiscount = isDiscount;
    }

    public double getAmount() {
        return amount;
    }

    public boolean getDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fee: " + amount;
    }
}
