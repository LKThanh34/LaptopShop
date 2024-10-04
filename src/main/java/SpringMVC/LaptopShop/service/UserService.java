package SpringMVC.LaptopShop.service;


import SpringMVC.LaptopShop.domain.Role;
import SpringMVC.LaptopShop.domain.User;
import SpringMVC.LaptopShop.repository.RoleRepository;
import SpringMVC.LaptopShop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello(){
        return "Hello from service";
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user){
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public User getUserById(long id){
        return this.userRepository.findById(id);
    }

    public void deleteAUser(long id){
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

}
