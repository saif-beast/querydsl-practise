package com.influido.poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.influido.poc.domains.Product;
import com.influido.poc.dto.ProductDTO;
import com.influido.poc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController implements CrudController<ProductCO,Long>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity create(@RequestBody ProductCO productCO) {

        ProductDTO body = saveAndGetProductDTO(productCO);

        return ResponseEntity.ok(body);
    }

    private ProductDTO saveAndGetProductDTO(@RequestBody ProductCO productCO) {
        Product product = productRepository.save(
                ProductCO.toDomain(productCO)
        );

        return Product.toDTO(product);
    }

    @Override
    public ResponseEntity update(ProductCO productCO) {
        return ResponseEntity.ok(productCO);
    }

    @Override
    public ResponseEntity get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipart) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getName());
        multipart.transferTo(convFile);
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();

        MappingIterator<ProductCO> objectMappingIterator = mapper.reader(ProductCO.class).with(bootstrapSchema).readValues(convFile);

        List<ProductDTO> productDTOList = objectMappingIterator.readAll().stream().map(this::saveAndGetProductDTO).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }
}
