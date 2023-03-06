package ik.sb_mvc_purchaseStatusAdministration.repository;

import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

    public Purchase findByTitle(String title);

    public List<Purchase> findAll();

    @Query("SELECT p FROM Purchase p WHERE p.id = ?1")
    public Purchase findPurchaseById(Integer id);

    @Query(value = "SELECT * FROM purchases WHERE purchaser_id = ?1", nativeQuery = true)
    public List<Purchase> findPurchasesByPurchaserId(Long purchaserId);

    @Query(value = "SELECT * FROM purchases WHERE department_id = ?1", nativeQuery = true)
    public List<Purchase> findPurchasesByDepartment(int departmentId);

    @Query(value = "SELECT * FROM purchases", nativeQuery = true)
    public List<Purchase> getAllPurchases();

    @Query("SELECT p FROM Purchase p WHERE bmig = ?1")
    public Purchase getPurchaseByBmigFromDb(int bmig);


}
