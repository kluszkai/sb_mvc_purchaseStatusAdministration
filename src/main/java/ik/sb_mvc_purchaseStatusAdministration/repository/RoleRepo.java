package ik.sb_mvc_purchaseStatusAdministration.repository;

import ik.sb_mvc_purchaseStatusAdministration.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    public Role findByName(String name);

}
