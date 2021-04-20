package com.jl.product.restClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.product.exception.RecordNotFoundException;
import com.jl.product.model.SearchProductCategoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.SealedObject;
import java.util.List;
import java.util.Map;

@Service
public class RestTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateClient.class);

    @Autowired
    RestTemplate restTemplate;

    public <T> T  getForEntity(Class<T> responseType, String requestServiceurl, MultiValueMap<String,String> params) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);

        //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestServiceurl).queryParams(params);
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET, entity,
                    responseType
            );
            return responseEntity.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public <T> List<T> getForList(Class<T> clazz, String requestServiceurl, String requestBody) {
        List<T> result = null;
        try {

            HttpEntity<?> requestEntity = new HttpEntity<>(requestBody);
            long startTime = System.currentTimeMillis();
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestServiceurl,HttpMethod.GET,requestEntity, String.class);
            String responseTime = (System.currentTimeMillis() - startTime) / 1000 + "s";
//            CollectionType collectionType = restTemplate.getMessageConverters().getTypeFactory().constructCollectionType(List.class, clazz);
//            result = readList(responseEntity, collectionType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOGGER.error("No data found {}", requestServiceurl);
            } else {
                LOGGER.error("rest client exception", exception.getMessage());
            }
        }
        return result;
    }
}
