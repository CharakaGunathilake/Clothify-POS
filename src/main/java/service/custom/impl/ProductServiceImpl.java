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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        ObservableList<String> productsList = FXCollections.observableArrayList();
        productDao.getAll().forEach(productEntity -> {
            productsList.add(new ModelMapper().map(productEntity.getId(), String.class));
        });
        return productsList;
    }

    @Override
    public String generateId() {
        List<String> productIdList = getProductIds();
        if (!productIdList.isEmpty()) {
            String last = productIdList.get((productIdList.size())-1);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(last);
            Integer id = null;
            while (m.find()) {
                id = Integer.parseInt(m.group());
            }
            if (id < 10) {
                return "P00" + (id + 1);
            } else if (id < 100) {
                return "P0" + (id + 1);
            } else {
                return "P" + (id + 1);
            }
        }else {
            return "P001";
        }
    }
}
