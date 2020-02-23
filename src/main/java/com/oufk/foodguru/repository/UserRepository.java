package com.oufk.foodguru.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.oufk.foodguru.models.User;
import java.util.List;
import java.util.Optional;

/*
 * Repository used to access User data from MySQL database, with custom queries.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1")
	//@Query("Select u From User u WHERE u.username = :username")
    Optional<User> findUserWithUsername(String username);

	//@Query("select u from User u where u.username = ?1 AND u.password = ?1")
	@Query("Select u From User u WHERE u.password = :password AND u.username = :username")
	Optional<User> findUserWithUsernameAndPassword (String password, String username);
	
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
