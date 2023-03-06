package ik.sb_mvc_purchaseStatusAdministration.service;

import ik.sb_mvc_purchaseStatusAdministration.dto.UserDto;
import ik.sb_mvc_purchaseStatusAdministration.model.Role;
import ik.sb_mvc_purchaseStatusAdministration.model.User;
import ik.sb_mvc_purchaseStatusAdministration.repository.RoleRepo;
import ik.sb_mvc_purchaseStatusAdministration.repository.UserRepo;
import ik.sb_mvc_purchaseStatusAdministration.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void modifyUserWithAdminRole(UserDto userDto, boolean setAdminRole) {

        Role roleUser = roleRepository.findByName(TbConstants.Roles.USER);

        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        if(setAdminRole) {
            Role roleAdmin = roleRepository.findByName(TbConstants.Roles.ADMIN);
            roles.add(roleAdmin);
        }

        User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(),
                    passwordEncoder.encode(userDto.getPassword()), roles);

        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUsersFromDb() {
        List<User> users = userRepository.getAllUsersFromDb();
        return users;
    }


}
