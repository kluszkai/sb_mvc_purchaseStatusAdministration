package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.model.Department;
import ik.sb_mvc_purchaseStatusAdministration.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.getAllDepartments();
    }


    @Override
    public Department findDepartmentById(int id) {
        return departmentRepo.findDepartmentById(id);
    }

    @Override
    public void saveDepartmentToDb(Department department) {
        departmentRepo.save(department);
    }
}
