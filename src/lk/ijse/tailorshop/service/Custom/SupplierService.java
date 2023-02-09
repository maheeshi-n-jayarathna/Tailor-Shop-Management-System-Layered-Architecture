package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.SupplierDTO;
import lk.ijse.tailorshop.service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    public List<SupplierDTO> getAllSupplier();

    public boolean saveSupplier(SupplierDTO supplierDTO);

    public String getNextSupplierId();

    public boolean deleteSupplier(String text);

    public boolean updateSupplier(SupplierDTO supplierDTO);
}
