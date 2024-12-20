package com.tecnocampus.trivial.adapter.out.rest.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnocampus.trivial.adapter.out.rest.entityDTO.CategoryApiDTO;
import com.tecnocampus.trivial.adapter.out.rest.mapper.CategoryMapper;
import com.tecnocampus.trivial.application.port.out.api.category.DownloadCategoriesApi;
import com.tecnocampus.trivial.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrivialCategoryClient implements DownloadCategoriesApi {

    private final RestTemplate restTemplate;
    private static final String CATEGORIES_URL = "https://opentdb.com/api_category.php";
    private final ObjectMapper objectMapper;


    public TrivialCategoryClient(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Category> downloadCategories() {

        List<CategoryApiDTO> categories = new ArrayList<>();

        try {
            // Realizar la solicitud y obtener la respuesta como un String
            String jsonResponse = restTemplate.getForObject(CATEGORIES_URL, String.class);

            // Parsear el String JSON para obtener el nodo "trivia_categories"
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode categoriesNode = rootNode.get("trivia_categories");

            // Convertir el nodo en una lista de CategoryDTO
            if (categoriesNode != null && categoriesNode.isArray()) {
                categories =  objectMapper.readValue(categoriesNode.toString(),
                                                     objectMapper.getTypeFactory()
                                                                 .constructCollectionType(List.class,
                                                                                          CategoryApiDTO.class)
                );
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
        }

        return CategoryMapper.toModelEntities(categories);
    }

}

