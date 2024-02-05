package ru.otus.javapro.homeworks.hw20webflux.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.javapro.homeworks.hw20webflux.reactive.service.dtos.ProductDetailsDto;
import ru.otus.javapro.homeworks.hw20webflux.reactive.service.services.ProductDetailsService;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {

    private final ProductDetailsService productDetailsService;

    @GetMapping("/demo")
    public Flux<ProductDetailsDto> getManySlowProducts() {
        Mono<ProductDetailsDto> p1 = productDetailsService.getProductDetailsById(1L);
        Mono<ProductDetailsDto> p2 = productDetailsService.getProductDetailsById(2L);
        Mono<ProductDetailsDto> p3 = productDetailsService.getProductDetailsById(3L);
        return p1.mergeWith(p2).mergeWith(p3);
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailsDto> getProductDetailsById(@PathVariable("id") Long id) {
        return productDetailsService
                .getProductDetailsById(id);
    }

    @GetMapping("/")
    public Flux<ProductDetailsDto> getProductDetailsById(@RequestBody Flux<Long> ids) {
        return productDetailsService
                .getProductDetailsByIds(ids);
    }

    @GetMapping("/all")
    public Flux<ProductDetailsDto> getAllProductDetails() {
        return productDetailsService.getAllProductDetails();
    }

}
