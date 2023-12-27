package com.algartech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.algartech.entity.Matriz;
import com.algartech.utils.MatrizMultiplicationUtil;

@RestController
public class MatrizController {

    @PostMapping("/multiplyMatrices")
    public ResponseEntity<int[][]> multiplyMatrices(@RequestBody Matriz matricesRequest) {
        int[][] result = null;
        MatrizMultiplicationUtil matrizMultiplicationUtil = new MatrizMultiplicationUtil();
		try {
			result = matrizMultiplicationUtil.multiplyMatricesConcurrently(matricesRequest.getMatrix1(), matricesRequest.getMatrix2());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.ok(result);
    }
}