package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao = DaoFactory.getInstance().getServiceType(DaoType.EMPLOYEE);

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.save(entity);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.update(entity);
    }

    @Override
    public boolean deleteEmployee(String id) {
        return employeeDao.delete(id);
    }

    @Override
    public ObservableList<Employee> getAll() throws NullPointerException{
        ObservableList<Employee> employeesList = FXCollections.observableArrayList();
        employeeDao.getAll().forEach(employeeEntity -> {
            System.out.println(employeeEntity);
            employeesList.add(new ModelMapper().map(employeeEntity, Employee.class));
        });
        return employeesList;
    }

    @Override
    public ObservableList<String> getEmployeeIds() {
        ObservableList<String> employeesIdList = FXCollections.observableArrayList();
        employeeDao.getAll().forEach(employeeEntity -> {
            employeesIdList.add(new ModelMapper().map(employeeEntity.getId(), String.class));
        });
        return employeesIdList;
    }

    @Override
    public String generateId() {
        List<String> employeeIdList = getEmployeeIds();
        if (!employeeIdList.isEmpty()) {
            String last = employeeIdList.get((employeeIdList.size())-1);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(last);
            Integer id = null;
            while (m.find()) {
                id = Integer.parseInt(m.group());
            }
            try {
                if (id < 10) {
                    return "E00" + (id + 1);
                } else if (id < 100) {
                    return "E0" + (id + 1);
                } else {
                    return "E" + (id + 1);
                }
            }catch (NullPointerException e){
                return "E001";
            }
        }else {
            return "E001";
        }
    }
}
