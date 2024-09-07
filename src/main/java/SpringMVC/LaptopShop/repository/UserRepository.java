package SpringMVC.LaptopShop.repository;


import SpringMVC.LaptopShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// CRUD : create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User eric);

    void deleteById(long id);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);


}
