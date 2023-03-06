package ik.sb_mvc_purchaseStatusAdministration.controller;


import ik.sb_mvc_purchaseStatusAdministration.dto.UserDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Department;
import ik.sb_mvc_purchaseStatusAdministration.model.Purchase;
import ik.sb_mvc_purchaseStatusAdministration.model.User;
import ik.sb_mvc_purchaseStatusAdministration.repository.DepartmentRepo;
import ik.sb_mvc_purchaseStatusAdministration.service.EmailService;
import ik.sb_mvc_purchaseStatusAdministration.service.PurchaseServiceImpl;
import ik.sb_mvc_purchaseStatusAdministration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseServiceImpl purchaseService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private DepartmentRepo departmentRepo;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {

        List<Purchase> allPurchases = purchaseService.getAllPurchasesFromDb();
        HashMap<String, Integer> mapOfPurchasesByDepartment = purchaseService.countPurchasesByDepartment(allPurchases);
        model.addAttribute("mapOfPurchasesByDepartment", mapOfPurchasesByDepartment);

        HashMap<String, Integer> mapOfPurchasesByStatus = purchaseService.countPurchasesByStatus(allPurchases);
        model.addAttribute("mapOfPurchasesByStatus", mapOfPurchasesByStatus);

        HashMap<String, Integer> mapOfPurchasesByPurchaser = purchaseService.countPurchasesByPurchaser(allPurchases);
        model.addAttribute("mapOfPurchasesByPurchaser", mapOfPurchasesByPurchaser);

        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegister(@ModelAttribute("user") UserDto userDto,
                                  BindingResult result,
                                  Model model) {

            User existingUser = userService.findUserByEmail(userDto.getEmail());

            if (existingUser != null)
                result.rejectValue("email", null,
                        "User already registered !!!");

            if (result.hasErrors()) {
                model.addAttribute("user", userDto);
                return "/registration";
            }
            emailService.sendRegistrationMessage(userDto.getEmail());
            userService.saveUser(userDto);
            return "redirect:/registration?success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsersFromDb();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/all-purchases")
    public String listPurchases(Model model) {
        List<Purchase> allPurchases = purchaseService.getAllPurchasesFromDb();
        model.addAttribute("purchases", allPurchases);

        return "all-purchases";
    }

    @GetMapping("/my-purchases/my-purchase-list")
    public String loadPurchasesByPurchaser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User loggedUser = userService.findUserByEmail(currentPrincipalName);
        Long loggedUserId = loggedUser.getId();

        List<Purchase> loggedUsersPurchases = purchaseService.findPurchasesByPurchaserId(loggedUserId);
        model.addAttribute("loggedUsersPurchases", loggedUsersPurchases);

        HashMap<String, Integer> mapOfPurchasesByDepartment = purchaseService.countPurchasesByDepartment(loggedUsersPurchases);
        model.addAttribute("mapOfPurchasesByDepartment", mapOfPurchasesByDepartment);

        HashMap<String, Integer> mapOfPurchasesByStatus = purchaseService.countPurchasesByStatus(loggedUsersPurchases);
        model.addAttribute("mapOfPurchasesByStatus", mapOfPurchasesByStatus);

        HashMap<String, Integer> mapOfPurchasesByType = purchaseService.countPurchasesByPurchaseType(loggedUsersPurchases);
        model.addAttribute("mapOfPurchasesByType", mapOfPurchasesByType);

        return "/my-purchases/my-purchases.html";
    }

    @GetMapping("/my-purchases/add-purchase")
    public String addPurchase(Model model) {
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);

        List<Department> allDepartments = departmentRepo.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);

        return "my-purchases/add-purchase.html";
    }

    @PostMapping("/my-purchases/add-purchase")
    public String savePurchaseToDb(Model model,
                                   @ModelAttribute(name = "purchase") Purchase purchase) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        purchase.setPurchaser(userService.findUserByEmail(currentPrincipalName));

        try {
            purchaseService.savePurchaseToDb(purchase);
            model.addAttribute("purchaseSaveResult", "Purchase was saved successfully!");
        } catch (Exception e) {
            model.addAttribute("purchaseSaveResult", "Something went wrong! Purchase was NOT saved!");
        }

        return "my-purchases/add-purchase.html";
    }

    @GetMapping("/my-purchases/edit-purchase")
    public String editPurchase(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long loggedUserId = userService.findUserByEmail(currentPrincipalName).getId();
        List<Purchase> allMyPurchases = purchaseService.findPurchasesByPurchaserId(loggedUserId);

        model.addAttribute("allMyPurchases", allMyPurchases);

        return "/my-purchases/edit-purchase.html";
    }

    @PostMapping("/my-purchases/edit-purchase")
    public String editChosenPurchase(Model model,
                                     @RequestParam(name = "purchase-bmig") int bmig) {
        Purchase chosenPurchaseToEdit = purchaseService.getPurchaseByBmig(bmig);
        model.addAttribute("chosenPurchaseToEdit", chosenPurchaseToEdit);

        editPurchase(model);
        List<Department> allDepartments = departmentRepo.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);

        return "/my-purchases/edit-purchase.html";
    }

    @PostMapping("/my-purchases/save-edited-purchase")
    public String saveEditedPurchase(Model model,
                                     @ModelAttribute(name = "chosenPurchaseToEdit") Purchase editedPurchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        editedPurchase.setPurchaser(userService.findUserByEmail(currentPrincipalName));

        try {
            purchaseService.savePurchaseToDb(editedPurchase);
            model.addAttribute("purchaseEditResult", "Purchase was edited successfully!");
        } catch (Exception e) {
            model.addAttribute("purchaseEditResult", "Something went wrong! Purchase was NOT edited!");
        }
        editPurchase(model);

        return "/my-purchases/edit-purchase.html";
    }

}
