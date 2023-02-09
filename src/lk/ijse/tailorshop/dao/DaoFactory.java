package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DaoTypes daoType) {
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case FABRIC:
                return (T) new FabricDAOImpl();
            case MEASUREMENT:
                return (T) new MeasurementDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIES:
                return (T) new SuppliesDAOImpl();
            case SUPPLIES_DETAILS:
                return (T) new SuppliesDetailsDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }

    }
}
