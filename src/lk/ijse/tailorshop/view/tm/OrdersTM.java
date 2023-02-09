package lk.ijse.tailorshop.view.tm;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class OrdersTM {
    private String orderId;
    private String description;
    private double advance;
    private double total;
    private FontAwesomeIconView action;

    public OrdersTM() {
    }

    public OrdersTM(String orderId, String description, double advance, double total, FontAwesomeIconView action) {
        this.orderId = orderId;
        this.description = description;
        this.advance = advance;
        this.total = total;
        this.action = action;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FontAwesomeIconView getAction() {
        return action;
    }

    public void setAction(FontAwesomeIconView action) {
        this.action = action;
    }
}
