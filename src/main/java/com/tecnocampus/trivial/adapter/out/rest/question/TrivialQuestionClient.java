package com.tecnocampus.trivial.adapter.out.rest.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnocampus.trivial.adapter.out.rest.entityDTO.QuestionApiDTO;
import com.tecnocampus.trivial.adapter.out.rest.mapper.QuestionMapper;
import com.tecnocampus.trivial.application.port.out.api.question.DownloadQuestionsApi;
import com.tecnocampus.trivial.model.Category;
import com.tecnocampus.trivial.model.Question;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrivialQuestionClient implements DownloadQuestionsApi {

    private final RestTemplate restTemplate;
    private static final String QUESTIONS_URL = "https://opentdb.com/api.php";
    private static final int AMOUNT = 50;
    private static final String TYPE = "multiple";
    private static final int MAX_RETRIES = 3;  // Número máximo de reintentos
    private static final int RETRY_DELAY_SECONDS = 5; // Retraso entre reintentos (en segundos)
    private final ObjectMapper objectMapper;

    public TrivialQuestionClient(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Question> downloadQuestions(String categoryId) {
        List<QuestionApiDTO> questions = new ArrayList<>();
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                // Construir la URL de forma dinámica usando UriComponentsBuilder
                String url = buildUrl(categoryId);

                // Realizar la solicitud y obtener la respuesta como un String
                String jsonResponse = restTemplate.getForObject(url, String.class);

                // Parsear el String JSON para obtener el nodo "results"
                JsonNode rootNode = objectMapper.readTree(jsonResponse);
                JsonNode categoriesNode = rootNode.get("results");

                // Convertir el nodo en una lista de QuestionApiDTO
                if (categoriesNode != null && categoriesNode.isArray()) {
                    questions = objectMapper.readValue(categoriesNode.toString(),
                            objectMapper.getTypeFactory()
                                    .constructCollectionType(List.class, QuestionApiDTO.class));
                    break; // Salir del bucle si la solicitud fue exitosa
                }
            } catch (HttpClientErrorException.TooManyRequests e) {
                // Si recibimos el error 429, esperamos antes de reintentar
                retries++;
                System.err.println("Error 429: Too Many Requests. Esperando " + RETRY_DELAY_SECONDS + " segundos antes de reintentar...");
                try {
                    Thread.sleep(RETRY_DELAY_SECONDS * 1000L); // Esperar el tiempo de reintento
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break; // Salir del bucle si la interrupción ocurre
                }
            } catch (JsonProcessingException e) {
                System.err.println("Error al procesar el JSON: " + e.getMessage());
                break; // Salir del bucle si hay un error al procesar el JSON
            }
        }

        if (retries == MAX_RETRIES) {
            System.err.println("Se ha alcanzado el número máximo de reintentos. No se pudieron obtener las preguntas.");
        }

        return QuestionMapper.toModelEntities(questions);
    }

    // Método para construir la URL dinámicamente con UriComponentsBuilder
    private String buildUrl(String categoryId) {
        return UriComponentsBuilder.fromHttpUrl(QUESTIONS_URL)
                .queryParam("amount", AMOUNT)
                .queryParam("type", TYPE)
                .queryParam("category", categoryId)
                .toUriString();
    }
}