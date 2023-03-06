package ik.sb_mvc_purchaseStatusAdministration.repository;

import ik.sb_mvc_purchaseStatusAdministration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findUserById(int id);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    public List<User> getAllUsersFromDb();


}
