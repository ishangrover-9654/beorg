package com.ishan.rd.beorg.repository.query;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.testdata.ShoppingCart;

public interface ShoppingCartRepository extends ArangoRepository<ShoppingCart, String> {
}
