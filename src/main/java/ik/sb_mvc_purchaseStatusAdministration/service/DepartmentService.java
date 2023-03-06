package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.model.Department;

import java.util.List;


public interface DepartmentService {

    public List<Department> getAllDepartments();

    public Department findDepartmentById(int id);

    public void saveDepartmentToDb(Department department);

}
