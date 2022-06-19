package app.repository;


import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Collection<User> findAllByRole(String role);

    @Query(nativeQuery = true,
           value = "select * from user " +
                   "where concat_ws(' ', first_name, last_name) like %?1% " +
                   "or region like %?1% " +
                   "or locality like %?1% " +
                   "or email like %?1% " +
                   "or role like %?1% " +
                   "or phone like %?1% ")
    List<User> findAllByFilter(String filterText);
}
