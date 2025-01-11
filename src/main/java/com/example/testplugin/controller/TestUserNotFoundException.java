package com.example.testplugin.controller;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
public class TestUserNotFoundException extends RuntimeException {
    public TestUserNotFoundException() {
    }

    public TestUserNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}