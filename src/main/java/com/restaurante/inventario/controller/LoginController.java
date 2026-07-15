package com.restaurante.inventario.controller;

import com.restaurante.inventario.entity.Usuario;
import com.restaurante.inventario.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(HttpSession session) {

        // Si ya inició sesión, no volver al login
        if (session.getAttribute("usuario") != null) {
            return "redirect:/";
        }

        return "login/login";
    }

    @PostMapping("/login")
    public String validarLogin(
            @RequestParam String usuario,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Usuario usuarioBD = usuarioService.validarLogin(usuario, password);

        if (usuarioBD != null) {

            session.setAttribute("usuario", usuarioBD.getUsuario());
            session.setAttribute("nombre", usuarioBD.getNombre());
            session.setAttribute("rol", usuarioBD.getRol());

            return "redirect:/";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos.");

        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

}