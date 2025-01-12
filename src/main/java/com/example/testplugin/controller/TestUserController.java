package com.example.testplugin.controller;

import com.example.testplugin.model.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
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
    /** This is an example service. */
    private TestUserServiceImpl service;

    @Autowired
    public TestUserController(TestUserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all_testuser")
    public CollectionModel<EntityModel<TestUser>> all() {
        return service.all();
    }

    @PostMapping("/testuser")
    public TestUser create(@RequestBody TestUser newTestUser) {
        return service.create(newTestUser);
    }

    @GetMapping("/testuser/{id}")
    public EntityModel<TestUser> one(@PathVariable Long id) {
        return service.one(id);
    }

    @PutMapping("/testuser/{id}")
    public ResponseEntity<?> replaceTestUser(@RequestBody TestUser newTestUser, @PathVariable Long id) {
        return service.replaceTestUser(newTestUser,id);
    }

    @DeleteMapping("/testuser/{id}")
    public ResponseEntity<?> deleteTestUser(@PathVariable Long id) {
        return service.deleteTestUser(id);
    }
}