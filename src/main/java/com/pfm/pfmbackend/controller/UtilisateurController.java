package com.pfm.pfmbackend.controller;

import com.pfm.pfmbackend.model.Utilisateur;
import com.pfm.pfmbackend.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> enregistrerUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUser = utilisateurService.enregistrerUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable String email) {
        return utilisateurService.trouverParEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.listerUtilisateurs());
    }

    @GetMapping("/id")
    public Optional<Long> findMaxId() {
        return utilisateurService.findMaxId();
    }
}
