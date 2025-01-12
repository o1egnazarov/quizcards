package com.example.quizcardspetspring.service;

import com.example.quizcardspetspring.entity.Module;
import org.springframework.http.ResponseEntity;

public interface ModuleService {
    ResponseEntity<Module> createModule(Long userID, Module module);

    Module deleteModuleById(Long id);
}
