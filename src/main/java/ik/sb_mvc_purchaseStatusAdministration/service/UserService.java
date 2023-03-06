package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.dto.UserDto;
import ik.sb_mvc_purchaseStatusAdministration.model.User;

import java.util.List;

public interface UserService {

    public void saveUser(UserDto userDto);

    public void modifyUserWithAdminRole(UserDto userDto, boolean setAdminRole);

    public void saveUser(User user);

    public User findUserByEmail(String email);

    public User findUserById(int id);

    public List<User> getAllUsersFromDb();

}
