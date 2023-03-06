package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.dto.PurchaseRestDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;

import java.util.HashMap;
import java.util.List;

public interface PurchaseService {

    public Purchase getPurchaseById(Integer id);

    public List<Purchase> findPurchasesByPurchaserId(Long purchaserId);

    public List<Purchase> findPurchasesByDepartmentId(int departmentId);

    public List<PurchaseRestDto> getAllPurchasesDtoByDep(List<Purchase> purchaseList);

    public Purchase getPurchaseByBmig(int bmig);

    public List<Purchase> getAllPurchasesFromDb();

    public List<Purchase> findAllPurchasesInDb();

    public void savePurchaseToDb(Purchase purchase);

    public HashMap<String, Integer> countPurchasesByDepartment(List<Purchase> purchaseList);

    public HashMap<String, Integer> countPurchasesByStatus(List<Purchase> purchaseList);

    public HashMap<String, Integer> countPurchasesByPurchaser(List<Purchase> purchaseList);

    public HashMap<String, Integer> countPurchasesByPurchaseType(List<Purchase> purchaseList);

}
