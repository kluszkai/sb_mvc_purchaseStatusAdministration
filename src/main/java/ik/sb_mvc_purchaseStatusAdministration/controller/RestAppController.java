package ik.sb_mvc_purchaseStatusAdministration.controller;

import ik.sb_mvc_purchaseStatusAdministration.dto.PurchaseRestDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;
import ik.sb_mvc_purchaseStatusAdministration.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAppController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases/{department_id}")
    public List<PurchaseRestDto> getListOfPurchasesByDep(@PathVariable(name = "department_id") int departmentId) {
        List<Purchase> purchaseListByDep = purchaseService.findPurchasesByDepartmentId(departmentId);
        List<PurchaseRestDto> purchaseListRestDtoByDep = purchaseService.getAllPurchasesDtoByDep(purchaseListByDep);

        return purchaseListRestDtoByDep;
    }

}
