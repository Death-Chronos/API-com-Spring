package learn.spring.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//Por algum motivo a IDE não aceitou esses metodos, então tive que forçar o import
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @PostMapping("/products") // URL bem definida
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var productModel = new ProductModel();
        // Cria um objeto produto, var é uma nova função do Java 10, que permite que a definição de tipo seja feita apenas no lado depois do =, quase como o JS
        BeanUtils.copyProperties(productRecordDTO, productModel); // Faz a transformação ProductDTO para ProductModel
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
        // Utilizando o protocolo Http. Basicamente retorna um codigo http falando que foi enviado, e manda o objeto que foi salvo também
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();

        if (!productsList.isEmpty()){
            for (ProductModel product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
        // retorna o estado HTTP, junto com todos os produtos cadastrados
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id); 
        // Procura o produto no repositorio usando o ID 

        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            // verifica se recebeu alguma coisa, caso não, retorna que não achou.
        }
        product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(product0.get()); // Retorna o produto caso o tenha achado.
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductRecordDTO productRecordDTO) {

        Optional<ProductModel> product0 = productRepository.findById(id); //Puxa o produto a ser atualizado
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            // verifica se recebeu alguma coisa, caso não, retorna que não achou.
        }

        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDTO, productModel); // Copia as informações do novo produto no velho

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel)); //Salva a manda a resposta

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id); // Procura o produto no repositorio usando o ID 

        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            // verifica se recebeu alguma coisa, caso não, retorna que não achou.

        }
        productRepository.delete(product0.get()); //Apaga o produto
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfuly"); 
        // Retorna uma mensagem informando que apagou o produto
    }
    }