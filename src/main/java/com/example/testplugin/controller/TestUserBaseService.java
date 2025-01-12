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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This is a generated BaseService for demonstration purposes.
 */
@Service
public class TestUserBaseService {
    /** This is an example repository. */
    private TestUserRepository repository;

    /** This is an example modelAssembler. */
    private TestUserModelAssembler assembler;

    public TestUserBaseService() {
    }

    @Autowired
    public TestUserBaseService(TestUserRepository repository, TestUserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<TestUser>> all() {
        List<EntityModel<TestUser>> testuser = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
        return CollectionModel.of(testuser, linkTo(methodOn(TestUserController.class).all()).withSelfRel());
    }

    public TestUser create(@RequestBody TestUser newTestUser) {
        return repository.save(newTestUser);
    }

    public EntityModel<TestUser> one(@PathVariable Long id) {
        TestUser testuser = repository.findById(id)
        .orElseThrow(() -> new TestUserNotFoundException(id));
        return assembler.toModel(testuser);
    }

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

    public ResponseEntity<?> deleteTestUser(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}