package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin(origins = "*")
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody UsuarioModel usuario) {
        String email = usuario.getEmail();
        String password = usuario.getPassword();

        // Buscar el usuario por el correo electrónico
        UsuarioModel usuarioExistente = usuarioService.findByEmail(email);

        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"El correo electrónico o contraseña no existe.\"}");
        }

        if (!usuarioExistente.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"El correo electrónico o contraseña no coincide.\"}");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"message\": \"Inicio de sesión exitoso\"}");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UsuarioModel usuario) {
        // Verificar si el formato del correo electrónico es válido
        if (!isValidEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El formato del correo electrónico no es válido.");
        }

        // Verificar si el correo electrónico ya está en uso
        if (usuarioService.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El correo electrónico ya está en uso.");
        }

        // Verificar si el nombre de usuario ya está en uso
        if (usuarioService.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El nombre de usuario ya está en uso.");
        }

        // Si todo está correcto, procede a registrar al usuario
        usuarioService.guardarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado con éxito.");
    }

    // Función para verificar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        // Utiliza una expresión regular para verificar el formato del correo
        // electrónico
        // Esta es una expresión regular simple y puede ser necesario ajustarla
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable Long id) {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }

}