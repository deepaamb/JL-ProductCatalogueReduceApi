package com.jl.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.product.exception.RecordNotFoundException;
import com.jl.product.model.SearchProductCategoryResponse;
import com.jl.product.restClient.RestTemplateClient;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductCategorySearchResource.class)
@AutoConfigureMockMvc
public class ProductCategorySearchTest {
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    RestTemplateClient restTemplateClient;

   private ObjectMapper objectMapper = new ObjectMapper();
    @Before
    public void setup() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @Test
    public void retrieveDressesReduced_InvalidParam() throws Exception {
        final MockHttpServletResponse response  = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced"  )
                                .param("labelType","1101")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse();

    }

    @Test
    public void retrieveDressesReduced_DefaultParam() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"\",\t\"then2\": \"\",\t\"now\": \"25.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                )
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

    }

    @Test
    public void retrieveDressesReduced_SHOWWASNOW() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"\",\t\"then2\": \"\",\t\"now\": \"25.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);

        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

    }

    @Test
    public void retrieveDressesReduced_SHOWWASTHENNOW() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"\",\t\"then2\": \"\",\t\"now\": \"25.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASTHENNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",  AllOf.allOf(
                        StringContains.containsString("£")

                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['priceLabel']",  AllOf.allOf(
                        StringContains.containsString("Was"),
                        StringContains.containsString("now")
                ))).andReturn().getResponse();
    }

    @Test
    public void retrieveDressesReduced_JLApi_Fail() throws Exception {

        when(restTemplateClient.getForEntity(any(),any(),any())).thenThrow(new RecordNotFoundException("No Data Found"));
        mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.notNullValue()));

    }
    @Test
    public void retrieveDressesReduced_SHOWWASNOW_Mocked_NoReduction() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"\",\t\"then2\": \"\",\t\"now\": \"50.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[0]",Matchers.is("There are no reduced dresses at the moment")));

    }

    @Test
    public void retrieveDressesReduced_SHOWWASNOW_Mocked_NowPriceFormat_lessThan10() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"49.00\",\t\"then2\": \"\",\t\"now\": \"9.23\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",Matchers.is("£9.23")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",  AllOf.allOf(
                        StringContains.containsString("£")

                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['priceLabel']",  AllOf.allOf(
                        StringContains.containsString("Was")

                ))).andReturn().getResponse();

    }

    @Test
    public void retrieveDressesReduced_SHOWWASNOW_Mocked_NowPriceFormat() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"49.00\",\t\"then2\": \"\",\t\"now\": \"13.23\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",Matchers.is("£13")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",  AllOf.allOf(
                        StringContains.containsString("£")

                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['priceLabel']",  AllOf.allOf(
                        StringContains.containsString("Was")

                ))).andReturn().getResponse();
    }

    @Test
    public void retrieveDressesReduced_SHOWWASTHENNOW_Mocked_ThenPrice() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"49.00\",\t\"then2\": \"\",\t\"now\": \"29.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWWASTHENNOW")

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",  AllOf.allOf(
                        StringContains.containsString("£")

                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['priceLabel']",  AllOf.allOf(
                        StringContains.containsString("Was"),
                        StringContains.containsString("then"),
                        StringContains.containsString("now")
                ))).andReturn().getResponse();
    }


    @Test
    public void retrieveDressesReduced_SHOWPERCDISCOUNT() throws Exception {
        String mockedResponse = "{\"showInStockOnly\": true,\"products\": [{\"productId\": \"4919177\",\"type\": \"product\",\"title\": \"John Lewis & Partners Jersey Bandeau Dress, Blue\",\"price\": {\t\"was\": \"50.00\",\"then1\": \"49.00\",\t\"then2\": \"\",\t\"now\": \"29.00\",\t\"uom\": \"\",\t\"currency\": \"GBP\"},\"colorSwatches\": [\t{\t\t\"color\": \"\",\t\t\"basicColor\": \"Blue\",\t\t\"colorSwatchUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612alt5?cropN=0.33473684210526317,0.7368421052631579,0.16842105263157894,0.16842105263157894&\",\t\t\"imageUrl\": \"//johnlewis.scene7.com/is/image/JohnLewis/004823612?\",\t\t\"isAvailable\": true,\t\t\"skuId\": \"238460015\"}]}]}";
        final SearchProductCategoryResponse searchProductCategoryResponse = objectMapper.readValue(mockedResponse, SearchProductCategoryResponse.class);
        when(restTemplateClient.getForEntity(any(),any(),any())).thenReturn(searchProductCategoryResponse);
        final MockHttpServletResponse response = mockMvc
                .perform(
                        get("/catalogue/products/dresses/reduced")
                                .param("labelType", "SHOWPERCDISCOUNT")

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['productId']", new Object[0]).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']", new Object[0]).exists())
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']", Matchers.contains("£*")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['nowPrice']",  AllOf.allOf(
                        StringContains.containsString("£")

                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]['priceLabel']",  AllOf.allOf(
                        StringContains.containsString("% off")

                )))
                .andReturn().getResponse();
    }
}
