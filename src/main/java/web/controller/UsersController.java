package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping("/read")
    public String readUser(@RequestParam(value = "id", required = true, defaultValue = "") int id, ModelMap model) {
        model.addAttribute("user", userService.readUser(id));

        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, ModelMap model ) {
        model.addAttribute("user", userService.readUser(id));
        return "edit";
    }
    @PostMapping("/edit")
    public String updateEditUser (@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}