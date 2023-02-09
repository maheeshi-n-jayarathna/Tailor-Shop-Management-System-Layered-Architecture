package lk.ijse.tailorshop.service;


import lk.ijse.tailorshop.service.Custom.Impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case EMPLOYEE:
                return (T) new EmployeeServiceImpl();
            case FABRIC:
                return (T) new FabricServiceImpl();
            case ORDER:
                return (T) new OrderServiceImpl();
            case USER:
                return (T) new UserServiceImpl();
            case SUPPLIER:
                return (T) new SupplierServiceImpl();
            case SUPPLIES:
                return (T) new SuppliesServiceImpl();
            default:
                return null;
        }
    }
}
