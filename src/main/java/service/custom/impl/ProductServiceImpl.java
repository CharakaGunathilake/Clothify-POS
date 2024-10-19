package service.custom.impl;

import dto.Employee;
import dto.Product;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.DaoType;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = DaoFactory.getInstance().getServiceType(DaoType.PRODUCT);

    @Override
    public boolean addProduct(Product product) {
        ProductEntity productEntity = new ModelMapper().map(product, ProductEntity.class);
        return productDao.save(productEntity);
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity productEntity = new ModelMapper().map(product, ProductEntity.class);
        return productDao.update(productEntity);
    }

    @Override
    public boolean deleteProduct(String id) {
        return productDao.delete(id);
    }

    @Override
    public ObservableList<Product> getAll() {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        productDao.getAll().forEach(productEntity -> {
            productsList.add(new ModelMapper().map(productEntity, Product.class));
        });
        return productsList;
    }

    @Override
    public ObservableList<String> getProductIds() {
        return null;
    }
}
