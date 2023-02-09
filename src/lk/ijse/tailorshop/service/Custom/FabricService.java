package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.FabricDTO;
import lk.ijse.tailorshop.service.SuperService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;

import java.util.List;

public interface FabricService extends SuperService {
    public List<FabricDTO> getAllFabric();

    public boolean saveFabric(FabricDTO fabric) throws DuplicateException;

    public String getNextFabricId();

    public boolean deleteFabric(String text) throws InUseException;

    public boolean updateFabric(FabricDTO fabric) throws DuplicateException;

    public FabricDTO getFabricByFabricId(String fabId);
}
