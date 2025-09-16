package com.example.liquibasestarter.controller;

import com.example.liquibasestarter.entity.UserPreferences;
import com.example.liquibasestarter.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-preferences")
@CrossOrigin(origins = "*")
public class UserPreferencesController {
    
    @Autowired
    private UserPreferencesRepository userPreferencesRepository;
    
    @GetMapping
    public ResponseEntity<List<UserPreferences>> getAllUserPreferences() {
        List<UserPreferences> preferences = userPreferencesRepository.findAll();
        return ResponseEntity.ok(preferences);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPreferences>> getUserPreferencesByUserId(@PathVariable Long userId) {
        List<UserPreferences> preferences = userPreferencesRepository.findByUserId(userId);
        return ResponseEntity.ok(preferences);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserPreferences> getUserPreferenceById(@PathVariable Long id) {
        Optional<UserPreferences> preference = userPreferencesRepository.findById(id);
        return preference.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/key/{key}")
    public ResponseEntity<List<UserPreferences>> getUserPreferencesByKey(@PathVariable String key) {
        List<UserPreferences> preferences = userPreferencesRepository.findByPreferenceKey(key);
        return ResponseEntity.ok(preferences);
    }
}
