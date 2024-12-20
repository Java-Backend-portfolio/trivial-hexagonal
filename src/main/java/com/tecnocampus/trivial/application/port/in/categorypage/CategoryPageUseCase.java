package com.tecnocampus.trivial.application.port.in.categorypage;

public interface CategoryPageUseCase {

    int getCurrentPageForCategory(Integer categoryId);

    void incrementPageForCategory(Integer categoryId);

    void resetPageForCategory(Integer categoryId);

}
