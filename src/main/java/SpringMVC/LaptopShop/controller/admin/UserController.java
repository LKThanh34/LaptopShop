package SpringMVC.LaptopShop.controller.admin;


import SpringMVC.LaptopShop.domain.User;
import SpringMVC.LaptopShop.service.UploandService;
import SpringMVC.LaptopShop.service.UserService;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UploandService uploandService;
    private final PasswordEncoder passwordEncoder;

    public UserController(
        UploandService uploandService,
            UserService userService,
        PasswordEncoder passwordEncoder
        ) {
        this.userService = userService;
        this.uploandService = uploandService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("1@gmail.com");
        System.out.println(arrUsers);

        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users  = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }
// bài tập
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
                                 @ModelAttribute("newUser") User hoidanit,
                                 @RequestParam("file")MultipartFile file
    ) {
       String avatar = this.uploandService.handleSaveUploandFile(file, "avatar");

       String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());

        hoidanit.setAvatar(avatar);
        hoidanit.setPassword(hashPassword);

        hoidanit.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
        // save
        this.userService.handleSaveUser(hoidanit);
       return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model,@PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user){
        User currentUser = this.userService.getUserById(user.getId());
        if(currentUser != null ){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id){
        model.addAttribute("id", id);
//        User user = new User();
//        user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUserPage(Model model, @ModelAttribute("newUser") User thanh){
        this.userService.deleteAUser(thanh.getId());
        return "redirect:/admin/user";
    }

}
