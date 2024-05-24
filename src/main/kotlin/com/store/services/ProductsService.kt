package com.store.services

import com.store.model.Product
import com.store.repo.ProductsRepository
import org.springframework.stereotype.Service

@Service
class ProductsService(private val productRepository: ProductsRepository) {

    fun createProduct(product: Product): Product {
        return productRepository.saveOrUpdate(product)
    }

    fun getAllProducts(): List<Product> = productRepository.findAll().toList()

    fun findByType(type: String): List<Product> {
        return productRepository.findByType(type)
    }
}

