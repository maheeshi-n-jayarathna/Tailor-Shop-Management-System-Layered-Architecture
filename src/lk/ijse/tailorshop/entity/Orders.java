package lk.ijse.tailorshop.entity;


public class Orders implements SuperEntity{
    private String orderId;
    private String customerId;
    private String description;
    private Double advance;
    private double totalPrice;
    private String status;

    public Orders() {
    }

    public Orders(String orderId, String customerId, String description, Double advance, double totalPrice, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.description = description;
        this.advance = advance;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getAdvance() {
            return advance;
        }

        public void setAdvance(Double advance) {
            this.advance = advance;
        }


        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", description='" + description + '\'' +
                ", advance=" + advance +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

