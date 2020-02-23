package com.oufk.foodguru.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oufk.foodguru.models.Role;
import com.oufk.foodguru.models.RoleName;
import java.util.Optional;

/*
 * Repository used to access Role data from MySQL database
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
