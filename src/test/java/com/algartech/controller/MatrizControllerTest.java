package com.algartech.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.algartech.utils.MatrizMultiplicationUtil;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MatrizControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MatrizMultiplicationUtil matrixMultiplicationUtil;

    @InjectMocks
    private MatrizController matrixController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(matrixController).build();
    }

    @Test
    public void whenPostMultiplyMatrices_thenReturnMultipliedMatrix() throws Exception {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{2, 0}, {1, 2}};
        int[][] expectedResult = {{4, 4}, {10, 8}};

        when(matrixMultiplicationUtil.multiplyMatricesConcurrently(eq(matrix1), eq(matrix2)))
        .thenReturn(expectedResult);

        mockMvc.perform(post("/multiplyMatrices")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"matrix1\": [[1, 2], [3, 4]], \"matrix2\": [[2, 0], [1, 2]]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[[4, 4], [10, 8]]"));
    }
}
