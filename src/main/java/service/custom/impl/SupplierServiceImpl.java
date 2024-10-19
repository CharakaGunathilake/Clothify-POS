package service.custom.impl;

import dto.Product;
import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao = DaoFactory.getInstance().getServiceType(DaoType.SUPPLIER);

    public boolean addSupplier(Supplier supplier){
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.save(supplierEntity);
    }
    public boolean updateSupplier(Supplier supplier){
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.update(supplierEntity);
    }
    public boolean deleteSupplier(String id){
        return supplierDao.delete(id);
    }
    public ObservableList<Supplier> getAll(){
        ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();
        supplierDao.getAll().forEach(supplierEntity -> {
            suppliersList.add(new ModelMapper().map(supplierEntity, Supplier.class));
        });
        return suppliersList;
    }
    public ObservableList<String> getSupplierIds(){
        return null;
    }
}
