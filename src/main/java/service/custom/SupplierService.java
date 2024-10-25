package service.custom;

import dto.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplierService extends SuperService{
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
    ObservableList<Supplier> getAll();
    ObservableList<String> getSupplierNames();
    ObservableList<String> getSupplierIds();
    String generateId ();
}
