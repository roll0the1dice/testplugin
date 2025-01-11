package com.example.testplugin.controller;

import com.example.testplugin.model.TestUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a generated interface for demonstration purposes.
 */
@RestController
public class TestUserController {
    /** This is an example repository. */
    private TestUserRepository repository;

    public TestUserController(TestUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all_testuser")
    public java.util.List<TestUser> all() {
        return repository.findAll();
    }

    @PostMapping("/testuser")
    public TestUser create(@RequestBody TestUser newTestUser) {
        return repository.save(newTestUser);
    }

    @GetMapping("/testuser/{id}")
    public TestUser one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new TestUserNotFoundException(id));
    }

    @PutMapping("/testuser/{id}")
    public TestUser replaceTestUser(@RequestBody TestUser newTestUser, @PathVariable Long id) {
        return repository.findById(id)
        .map(_newTestUser -> {
            return repository.save(_newTestUser);
        })
        .orElseGet(() -> {
            return repository.save(newTestUser);
        });
    }

    @DeleteMapping("/testuser/{id}")
    public void deleteTestUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}