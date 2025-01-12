package com.example.testplugin.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.testplugin.model.TestUser;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    /** This is an example repository. */
    private TestUserRepository repository;

    /** This is an example modelAssembler. */
    private TestUserModelAssembler assembler;

    @Autowired
    public TestUserController(TestUserRepository repository, TestUserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/all_testuser")
    public CollectionModel<EntityModel<TestUser>> all() {
        List<EntityModel<TestUser>> testuser = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
        return CollectionModel.of(testuser, linkTo(methodOn(TestUserController.class).all()).withSelfRel());
    }

    @PostMapping("/testuser")
    public TestUser create(@RequestBody TestUser newTestUser) {
        return repository.save(newTestUser);
    }

    @GetMapping("/testuser/{id}")
    public EntityModel<TestUser> one(@PathVariable Long id) {
        TestUser testuser = repository.findById(id)
        .orElseThrow(() -> new TestUserNotFoundException(id));
        return assembler.toModel(testuser);
    }

    @PutMapping("/testuser/{id}")
    public ResponseEntity<?> replaceTestUser(@RequestBody TestUser newTestUser, @PathVariable Long id) {
        TestUser _updateModel = repository.findById(id)
        .map(_newTestUser -> {
            return repository.save(_newTestUser);
        })
        .orElseGet(() -> {
            return repository.save(newTestUser);
        });
        EntityModel<TestUser> entityModel = assembler.toModel(_updateModel);
        return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
    }

    @DeleteMapping("/testuser/{id}")
    public ResponseEntity<?> deleteTestUser(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}