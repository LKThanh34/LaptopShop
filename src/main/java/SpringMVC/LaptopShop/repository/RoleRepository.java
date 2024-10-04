package SpringMVC.LaptopShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringMVC.LaptopShop.domain.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    
}
