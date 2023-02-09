package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.FabricDAO;
import lk.ijse.tailorshop.dao.custom.QueryDAO;
import lk.ijse.tailorshop.dto.FabricDTO;
import lk.ijse.tailorshop.entity.Fabric;
import lk.ijse.tailorshop.service.Custom.FabricService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FabricServiceImpl implements FabricService {
    private final FabricDAO fabricDAO;
    private final QueryDAO queryDAO;
    private final Convertor convertor;
    public FabricServiceImpl() {
        fabricDAO = DaoFactory.getInstance().getDAO(DaoTypes.FABRIC);
        queryDAO = DaoFactory.getInstance().getDAO(DaoTypes.QUERY);
        convertor = new Convertor();
    }

    @Override
    public List<FabricDTO> getAllFabric() {
        try {
            List<FabricDTO> fabricDTOS = new ArrayList<>();
            for (Fabric fabric : fabricDAO.findAll()){
                fabricDTOS.add(convertor.fromFabric(fabric));
            }
            return fabricDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveFabric(FabricDTO fabricDTO) throws DuplicateException {
        try {
            if (fabricDAO.findByDescription(fabricDTO.getDescription()).isPresent())
                throw new DuplicateException("Fabric description is exists !");
            return fabricDAO.save(convertor.toFabric(fabricDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextFabricId() {
        try {
            Optional<String> lastPk = fabricDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("F%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "F001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFabric(String fabricId) throws InUseException {
        try {
            if (queryDAO.findAllNonReturnedOrdersByFabricId(fabricId).size() > 0)
                throw new InUseException("There are undelivered orders with this fabric !");
            return fabricDAO.deleteByPk(fabricId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateFabric(FabricDTO fabricDTO) throws DuplicateException {
        try {
            Optional<Fabric> optional = fabricDAO.findByDescription(fabricDTO.getDescription());
            if (optional.isPresent() && (!optional.get().getFabricId().equals(fabricDTO.getFabricId())))
                throw new DuplicateException("Fabric description is exists !");
            return fabricDAO.save(convertor.toFabric(fabricDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FabricDTO getFabricByFabricId(String fabId) {
        try {
            return convertor.fromFabric(fabricDAO.findByPk(fabId).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
