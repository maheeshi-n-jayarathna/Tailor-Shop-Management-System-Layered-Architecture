package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.SupplierDAO;
import lk.ijse.tailorshop.dto.SupplierDTO;
import lk.ijse.tailorshop.entity.Supplier;
import lk.ijse.tailorshop.service.Custom.SupplierService;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierDAO supplierDAO;
    private final Convertor convertor;
    public SupplierServiceImpl() {
        supplierDAO = DaoFactory.getInstance().getDAO(DaoTypes.SUPPLIER);
        convertor = new Convertor();
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        try{
            List<SupplierDTO> supplierDTOS = new ArrayList<>();
            for (Supplier supplier : supplierDAO.findAll()){
                supplierDTOS.add(convertor.fromSupplier(supplier));
            }
            return supplierDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) {
        try {
            return supplierDAO.save(convertor.toSupplier(supplierDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextSupplierId() {
        try {
            Optional<String> lastPk = supplierDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("S%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "S001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String supplierId) {
        try {
            return supplierDAO.deleteByPk(supplierId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) {
        try {
            return supplierDAO.update(convertor.toSupplier(supplierDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
