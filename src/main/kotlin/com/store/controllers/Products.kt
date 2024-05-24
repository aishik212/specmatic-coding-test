package com.store.controllers

import com.store.model.Product
import com.store.services.ProductsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class Products(private val productService: ProductsService) {

    @GetMapping
    fun getAllProducts(@RequestParam(required = false) type: String?): ResponseEntity<Any> {
        val regex = Regex("^(gadget|book|food|other)$")
        println(type+" "+type?.matches(regex))
        return if (type != null) {
            if(type.matches(regex)) {
                ResponseEntity(productService.findByType(type), HttpStatus.OK)
            }else {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyMap<String, Any>())
            }
        } else {
            ResponseEntity(productService.getAllProducts(), HttpStatus.OK)
        }
    }

    @PostMapping
    fun setProducts(@RequestBody product: Product): ResponseEntity<Map<String, Any>> {
        val savedProduct = productService.createProduct(product)
        if(product.name != null && product.type != null && product.inventory != null && product.cost != null)
        {
            if(
                product.name.matches(Regex("^[a-zA-Z\\s]+$")) && !(product.name == "true" || product.name == "false")
                &&
                product.type.matches(Regex("^[a-zA-Z\\s]+$")) && !(product.type == "true" || product.type == "false")
                )
            {
                val responseBody = mapOf(
                    "id" to savedProduct.id
                )
                return ResponseEntity.status(HttpStatus.CREATED).body(responseBody)
            }else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf())
            }
        }else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf())
        }
    }
}
