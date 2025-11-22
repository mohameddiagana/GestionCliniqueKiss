package sn.seck.GestionCliniqueKissi.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/auth/")
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
    @GetMapping(value = "")
    public String accueil() {
        return "redirect:/logon";
    }
}
