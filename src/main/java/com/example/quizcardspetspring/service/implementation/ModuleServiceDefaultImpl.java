package com.example.quizcardspetspring.service.implementation;

import com.example.quizcardspetspring.entity.Card;
import com.example.quizcardspetspring.entity.Module;
import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.exception.UserDoesNotExistException;
import com.example.quizcardspetspring.repository.ModuleRepository;
import com.example.quizcardspetspring.repository.UserRepository;
import com.example.quizcardspetspring.service.ModuleService;
import com.example.quizcardspetspring.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceDefaultImpl implements ModuleService {

    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final ValidationUtils validationUtils;

    @Override
    public ResponseEntity<Module> createModule(Long userID, Module module) {
        User user = this.userRepository
                .findById(userID)
                .orElseThrow(() -> new UserDoesNotExistException(HttpStatus.NOT_FOUND,
                        "user with id = %d, does not exist".formatted(userID)));

        validationUtils.validationRequest(module);


        module.setUser(user);
        for (Card card : module.getCards()) {
            card.setModule(module);
        }

        user.getModules().add(module);
        this.userRepository.save(user);

        return new ResponseEntity<>(module, HttpStatus.CREATED);

    }

    @Override
    public Module deleteModuleById(Long id) {
        Module module = this.moduleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("module with id: %d, does not exist".formatted(id)));
        this.moduleRepository.deleteById(id);
        return module;
    }
}
