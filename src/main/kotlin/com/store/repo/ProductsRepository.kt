package com.store.repo

import com.store.model.Product
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap


@Repository
class ProductsRepository {
    private val products: ConcurrentHashMap<Int, Product> = ConcurrentHashMap()
    private var count = 1

    fun saveOrUpdate(product: Product): Product {
        product.id = count
        products[count] = product
        count++
        return product
    }

    fun findAll(): MutableCollection<Product> {
        return products.values
    }

    fun findByType(type: String): List<Product> {
        return products.values.filter { it.type == type }
    }
}