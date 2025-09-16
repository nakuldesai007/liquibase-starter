package com.example.liquibasestarter.repository;

import com.example.liquibasestarter.entity.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
    
    List<UserPreferences> findByUserId(Long userId);
    
    List<UserPreferences> findByPreferenceKey(String preferenceKey);
    
    List<UserPreferences> findByUserIdAndPreferenceKey(Long userId, String preferenceKey);
}
