package com.restaurante.inventario.controller;

import com.restaurante.inventario.entity.Usuario;
import com.restaurante.inventario.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("usuarios", usuarioService.listar());

        return "usuarios/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        Usuario usuario = new Usuario();
        usuario.setActivo(true);

        model.addAttribute("usuario", usuario);

        return "usuarios/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {

        usuarioService.guardar(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute("usuario", usuarioService.buscarPorId(id));

        return "usuarios/nuevo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        usuarioService.eliminar(id);

        return "redirect:/usuarios";
    }

}