package com.tecnocampus.trivial.adapter.in.rest.common;


public record ErrorEntity(int httpStatus, String errorMessage) {}