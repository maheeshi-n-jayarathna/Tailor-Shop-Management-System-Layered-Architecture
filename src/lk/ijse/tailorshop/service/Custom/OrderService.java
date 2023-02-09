package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.MeasurementDTO;
import lk.ijse.tailorshop.dto.OrderDTO;
import lk.ijse.tailorshop.dto.OrderDetailsDTO;
import lk.ijse.tailorshop.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    public String getNextMeasurementId();

    public String getNextOrderId();

    public boolean saveOrder(OrderDTO orderDTO);

    public List<OrderDTO> getAllNonReturnOrdersByCustomerId(String customerId);

    public List<OrderDetailsDTO> getOrderDetailByOrderId(String orderId);

    public MeasurementDTO getMeasurementByMeasurementId(String measurementId);

    public OrderDTO getOrdersByOrderId(String orderId);

    public boolean purchaseOrders(List<String> orderIds);
}
