package learn.spring.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learn.spring.api.dtos.ProductRecordDTO;
import learn.spring.api.models.ProductModel;
import learn.spring.api.repositorys.ProductRepository;

@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products") //URL bem definida
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel(); // Cria um objeto produto, var é uma nova função do Java 10, que permite que a definição de tipo seja feita apenas no lado depois do =, quase como o JS
        BeanUtils.copyProperties(productRecordDTO, productModel); //Faz a transformação ProductDTO para ProductModel
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); //Utilizando o protocolo Http. Basicamente retorna um codigo http falando que foi enviado, e manda o objeto que foi salvo também
    }




}
