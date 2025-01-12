package com.example.testplugin.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.testplugin.model.TestUser;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
@Component
public class TestUserModelAssembler implements RepresentationModelAssembler<TestUser, EntityModel<TestUser>> {
    @Override
    public EntityModel<TestUser> toModel(@NonNull TestUser testuser) {
        return EntityModel.of(testuser,
        linkTo(methodOn(TestUserController.class).one(testuser.getId())).withSelfRel(),
        linkTo(methodOn(TestUserController.class).all()).withRel("testuser"));
    }
}