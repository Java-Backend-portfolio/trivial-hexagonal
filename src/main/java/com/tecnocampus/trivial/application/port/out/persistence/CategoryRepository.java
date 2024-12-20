package com.tecnocampus.trivial.application.port.out.persistence;

import java.util.List;
import com.tecnocampus.trivial.model.Category;

public interface CategoryRepository {

    void save(Category category);

    List<Category> findAll();

}
