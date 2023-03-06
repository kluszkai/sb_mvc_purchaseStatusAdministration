package ik.sb_mvc_purchaseStatusAdministration.repository;

import ik.sb_mvc_purchaseStatusAdministration.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    @Query(value = "SELECT * FROM departments", nativeQuery = true)
    public List<Department> getAllDepartments();

    @Query("SELECT d FROM Department d WHERE d.id = ?1")
    public Department findDepartmentById(int id);

}
