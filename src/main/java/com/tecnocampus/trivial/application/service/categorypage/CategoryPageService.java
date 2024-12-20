package com.tecnocampus.trivial.application.service.categorypage;

import com.tecnocampus.trivial.application.port.in.categorypage.CategoryPageUseCase;

import java.util.concurrent.ConcurrentHashMap;

public class CategoryPageService implements CategoryPageUseCase {

    private final ConcurrentHashMap<Integer, Integer> categoryPageMap = new ConcurrentHashMap<>();

    // Obtener la página actual para una categoria
    @Override
    public int getCurrentPageForCategory(Integer categoryId) {
        return categoryPageMap.getOrDefault(categoryId, 0);
    }
    // Incrementar la página para una categoria
    @Override
    public void incrementPageForCategory(Integer categoryId) {
        categoryPageMap.put(categoryId, getCurrentPageForCategory(categoryId) + 1);
    }
    // Reiniciar la página para una categoria
    @Override
    public void resetPageForCategory(Integer categoryId) {
        categoryPageMap.put(categoryId, 0);
    }
}
