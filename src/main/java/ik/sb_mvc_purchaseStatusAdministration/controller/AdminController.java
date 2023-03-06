package ik.sb_mvc_purchaseStatusAdministration.controller;

import ik.sb_mvc_purchaseStatusAdministration.dto.UserDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Department;
import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;
import ik.sb_mvc_purchaseStatusAdministration.model.User;
import ik.sb_mvc_purchaseStatusAdministration.service.DepartmentService;
import ik.sb_mvc_purchaseStatusAdministration.service.PurchaseService;
import ik.sb_mvc_purchaseStatusAdministration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/admin/edit-purchase")
    public String editPurchase(Model model) {
        List<Purchase> allPurchases = purchaseService.getAllPurchasesFromDb();
        model.addAttribute("allPurchases", allPurchases);
        List<Department> allDepartments = departmentService.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);

        return "/admin/edit-purchase";
    }

    @PostMapping("/admin/edit-purchase")
    public String showChosenPurchaseToEdit(Model model,
                                           @RequestParam(name = "purchase-bmig") int bmig) {
        editPurchase(model);
        Purchase chosenPurchaseToEdit = purchaseService.getPurchaseByBmig(bmig);
        model.addAttribute("chosenPurchaseToEdit", chosenPurchaseToEdit);
        List<User> allUsers = userService.getAllUsersFromDb();
        model.addAttribute("allUsers", allUsers);

        return "/admin/edit-purchase";
    }

    @PostMapping("/admin/save-edited-purchase")
    public String saveEditedPurchase(Model model,
                                     @ModelAttribute("purchase") Purchase purchase) {
        try {
            purchaseService.savePurchaseToDb(purchase);
            model.addAttribute("purchaseEditResult", "Purchase was edited successfully!");
        } catch (Exception e) {
            model.addAttribute("purchaseEditResult", "Something went wrong! Purchase was NOT edited!");
        }
        editPurchase(model);
        return "/admin/edit-purchase";
    }

    @GetMapping("/admin/edit-user")
    public String editUser(Model model) {
        List<User> allUsers = userService.getAllUsersFromDb();
        model.addAttribute("allUsers", allUsers);

        return "/admin/edit-user.html";
    }

    @PostMapping("/admin/edit-user")
    public String editChoosenUser(Model model,
                                  @RequestParam(name = "user-id") int userId) {
        editUser(model);
        User chosenUser = userService.findUserById(userId);
        UserDto chosenUserDto = new UserDto(chosenUser.getId(), chosenUser.getName(), chosenUser.getEmail(), chosenUser.getPassword());
        model.addAttribute("chosenUser", chosenUserDto);

        return "/admin/edit-user.html";
    }

    @GetMapping("/admin/save-edited-user")
    public String saveEditedUser(Model model,
                                 @ModelAttribute("chosenUser") UserDto chosenUser,
                                 @RequestParam(name = "admin_role") boolean setAdminRole){

        try {
                userService.modifyUserWithAdminRole(chosenUser, setAdminRole);
                model.addAttribute("userEditResult", "User was edited successfully!");
        } catch (Exception e) {
            model.addAttribute("userEditResult", "Something went wrong! User was NOT edited!");
        }
        editUser(model);

        return "/admin/edit-user";
    }

    @GetMapping("/admin/add-department")
    public String addDepartment(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "/admin/add-department";
    }

    @PostMapping("admin/save-department")
    public String saveDepartmentToDb(Model model,
                                     @ModelAttribute("department") Department department) {
        try {
            departmentService.saveDepartmentToDb(department);
            model.addAttribute("departmentSaveResult", "Department was successfully saved to DB.");
        } catch (Exception e) {
            model.addAttribute("departmentSaveResult", "Something went wrong. Department was NOT saved to DB.");
        }
        return "/admin/add-department";
    }

    @GetMapping("/admin/edit-department")
    public String editDepartment(Model model) {
        List<Department> allDepartments = departmentService.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);

        return "/admin/edit-department.html";
    }

    @PostMapping("/admin/edit-department")
    public String editChosenDepartment(Model model,
                                       @RequestParam(name = "department-id") int departmentId) {
        Department chosenDepartment = departmentService.findDepartmentById(departmentId);
        model.addAttribute("chosenDepartment", chosenDepartment);

        return "/admin/edit-department.html";
    }

    @PostMapping("/admin/save-edited-department")
    public String saveEditedDepartment(Model model,
                                       @ModelAttribute(name = "chosenDepartment") Department chosenDepartment) {

        try {
            departmentService.saveDepartmentToDb(chosenDepartment);
            model.addAttribute("departmentSaveResult", "Department was saved successfully!");
        }catch (Exception e) {
            model.addAttribute("departmentSaveResult", "Something went wrong! Department was NOT saved!");
        }
        editDepartment(model);
        return "/admin/edit-department.html";
    }

    @GetMapping("/admin/add-purchase")
    public String addPurchaseToDb(Model model) {
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);

        List<User> allUsers = userService.getAllUsersFromDb();
        model.addAttribute("allUsers", allUsers);

        List<Department> allDepartments = departmentService.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);

        return "/admin/add-purchase.html";
    }

    @PostMapping("/admin/add-purchase")
    public String savePurchaseToDb(@ModelAttribute("purchase") Purchase purchase,
                                   Model model) {
        try {
            purchaseService.savePurchaseToDb(purchase);
            model.addAttribute("purchaseSaveResult", "Purchase was saved successfully");
        } catch (Exception e) {
            model.addAttribute("purchaseSaveResult", "Something went wrong! Purchase was NOT saved!");
        }

        return "/admin/add-purchase.html";
    }

}
