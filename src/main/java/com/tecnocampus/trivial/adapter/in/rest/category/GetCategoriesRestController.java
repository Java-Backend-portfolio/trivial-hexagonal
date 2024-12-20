package com.tecnocampus.trivial.adapter.in.rest.category;

import com.tecnocampus.trivial.adapter.in.rest.category.webmodel.CategoryDTO;
import com.tecnocampus.trivial.application.port.in.category.FindAllCategoriesUseCase;
import com.tecnocampus.trivial.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.tecnocampus.trivial.adapter.in.rest.common.ControllerCommons.clientErrorException;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class GetCategoriesRestController {

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;

    public GetCategoriesRestController(FindAllCategoriesUseCase findAllCategoriesUseCase) {
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories;

        try {
            categories = findAllCategoriesUseCase.findAll();
        } catch (IllegalArgumentException e) {
            throw clientErrorException(HttpStatus.NOT_FOUND, "Categories not found'");
        }

        return categories.stream().map(CategoryDTO::fromDomainModel).toList();
    }
}