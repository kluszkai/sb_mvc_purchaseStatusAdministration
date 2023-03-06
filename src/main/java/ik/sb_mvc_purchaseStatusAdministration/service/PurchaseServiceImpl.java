package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.dto.PurchaseRestDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;
import ik.sb_mvc_purchaseStatusAdministration.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepo purchaseRepo;

    @Autowired
    public void setPurchaseRepo(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }

    public Purchase getPurchaseById(Integer id) {

        return purchaseRepo.findPurchaseById(id);
    }

    @Override
    public List<Purchase> findPurchasesByPurchaserId(Long purchaserId) {
        return purchaseRepo.findPurchasesByPurchaserId(purchaserId);
    }

    @Override
    public List<Purchase> findPurchasesByDepartmentId(int departmentId) {
        return purchaseRepo.findPurchasesByDepartment(departmentId);
    }
    @Override
    public List<PurchaseRestDto> getAllPurchasesDtoByDep(List<Purchase> purchaseList) {
        List<PurchaseRestDto> purchaseRestDtoList = new ArrayList<>();

        for(int i = 0; i < purchaseList.size(); i++) {
            Purchase purchase = purchaseList.get(i);
            PurchaseRestDto purchaseRestDto = new PurchaseRestDto();
            purchaseRestDto.setPurchaseTitle(purchase.getTitle());
            purchaseRestDto.setBmig(purchase.getBmig());
            purchaseRestDto.setEstimatedValue(purchase.getEstimatedValue());
            purchaseRestDto.setPurchaserName(purchase.getPurchaser().getName());
            purchaseRestDto.setStatus(purchase.getStatus());
            purchaseRestDto.setPurchaseType(purchase.getPurchaseType());
            purchaseRestDto.setRfqSent(purchase.getRfqSent());
            purchaseRestDto.setRfqDeadline(purchase.getRfqDeadline());
            purchaseRestDto.setDecisionDate(purchase.getDecisionDate());
            purchaseRestDto.setContractSignatureDate(purchase.getContractSignatureDate());
            purchaseRestDtoList.add(purchaseRestDto);
        }

        return purchaseRestDtoList;
    }

    @Override
    public Purchase getPurchaseByBmig(int bmig) {
        return purchaseRepo.getPurchaseByBmigFromDb(bmig);
    }

    public List<Purchase> getAllPurchasesFromDb() {
        return purchaseRepo.getAllPurchases();
    }

    public List<Purchase> findAllPurchasesInDb() {
        return purchaseRepo.findAll();

    }

    public void savePurchaseToDb(Purchase purchase) {
        purchaseRepo.save(purchase);
    }

    @Override
    public HashMap<String, Integer> countPurchasesByDepartment(List<Purchase> purchaseList) {
        HashMap<String, Integer> numOfPurchasesByDep = new HashMap<>();

        for(int i = 0; i < purchaseList.size(); i++) {
            String departmentName = purchaseList.get(i).getDepartment().getName();

            if(numOfPurchasesByDep.containsKey(departmentName)) {
                int actualValue = numOfPurchasesByDep.get(departmentName);
                numOfPurchasesByDep.put(departmentName, actualValue+1);
            } else {
                numOfPurchasesByDep.put(departmentName, 1);
            }
        }
        return numOfPurchasesByDep;
    }

    @Override
    public HashMap<String, Integer> countPurchasesByStatus(List<Purchase> purchaseList) {
        HashMap<String, Integer> numOfPurchasesByStatus = new HashMap<>();


        for(int i = 0; i < purchaseList.size(); i++) {
            String status = purchaseList.get(i).getStatus();

            if(numOfPurchasesByStatus.containsKey(status)) {
                int actualValue = numOfPurchasesByStatus.get(status);
                numOfPurchasesByStatus.put(status, actualValue+1);
            } else {
                numOfPurchasesByStatus.put(status, 1);
            }

        }

        return numOfPurchasesByStatus;
    }

    @Override
    public HashMap<String, Integer> countPurchasesByPurchaser(List<Purchase> purchaseList) {
        HashMap<String, Integer> numOfPurchasesByPurchaser = new HashMap<>();

        for(int i = 0; i < purchaseList.size(); i++) {
            String purchasersName = purchaseList.get(i).getPurchaser().getName();

            if(numOfPurchasesByPurchaser.containsKey(purchasersName)) {
                int actualValue = numOfPurchasesByPurchaser.get(purchasersName);
                numOfPurchasesByPurchaser.put(purchasersName, actualValue+1);
            } else {
                numOfPurchasesByPurchaser.put(purchasersName, 1);
            }
        }

        return numOfPurchasesByPurchaser;
    }

    @Override
    public HashMap<String, Integer> countPurchasesByPurchaseType(List<Purchase> purchaseList) {
        HashMap<String, Integer> numOfPurchasesByPurchaseType = new HashMap<>();

        for(int i = 0; i < purchaseList.size(); i++) {
            String purchaseType = purchaseList.get(i).getPurchaseType();

            if(numOfPurchasesByPurchaseType.containsKey(purchaseType)) {
                int actualValue = numOfPurchasesByPurchaseType.get(purchaseType);
                numOfPurchasesByPurchaseType.put(purchaseType, actualValue+1);
            } else {
                numOfPurchasesByPurchaseType.put(purchaseType, 1);
            }
        }

        return numOfPurchasesByPurchaseType;
    }


}
