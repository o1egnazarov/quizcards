package com.example.quizcardspetspring.controller;

import com.example.quizcardspetspring.entity.Module;
import com.example.quizcardspetspring.service.ModuleService;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping("/start")
    public ResponseEntity<Module> createOfListCards(@RequestParam("userId") Long userID,
                                                    @RequestBody Module module) {
        return this.moduleService.createModule(userID, module);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Module> deleteModuleById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.moduleService.deleteModuleById(id));
    }


}
