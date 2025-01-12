package com.example.testplugin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a generated Service for demonstration purposes.
 */
@Service
public class TestUserServiceImpl extends TestUserBaseService implements TestUserService {
    /** This is an example repository. */
    private TestUserRepository repository;

    /** This is an example modelAssembler. */
    private TestUserModelAssembler assembler;

    public TestUserServiceImpl() {
        super();
    }

    @Autowired
    public TestUserServiceImpl(TestUserRepository repository, TestUserModelAssembler assembler) {
        super(repository,assembler);
        this.repository = repository;
        this.assembler = assembler;
    }
}