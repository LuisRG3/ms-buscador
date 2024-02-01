package com.unir.products.data;

import com.unir.products.data.utils.SearchCriteria;
import com.unir.products.data.utils.SearchOperation;
import com.unir.products.data.utils.SearchStatement;
import com.unir.products.model.pojo.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public List<Product> search(String nombre, String categoria, String descripcioncorta, String descripcionlarga, Double valorunitario, Integer indValorUnitario,  Boolean indEliminado) {
        SearchCriteria<Product> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(nombre)) {
            spec.add(new SearchStatement("nombre", nombre, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(categoria)) {
            spec.add(new SearchStatement("categoria", categoria, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(descripcioncorta)) {
            spec.add(new SearchStatement("descripcioncorta", descripcioncorta, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(descripcionlarga)) {
            spec.add(new SearchStatement("descripcionlarga", descripcionlarga, SearchOperation.MATCH));
        }

        if (indEliminado != null) {
            spec.add(new SearchStatement("indEliminado", indEliminado, SearchOperation.EQUAL));
        }

        if (valorunitario != null && valorunitario>0 && indValorUnitario!=null) {
            switch (indValorUnitario) {
                case 1:
                    spec.add(new SearchStatement("indEliminado", indEliminado, SearchOperation.LESS_THAN_EQUAL));
                case 2:
                    spec.add(new SearchStatement("indEliminado", indEliminado, SearchOperation.GREATER_THAN_EQUAL));
                default:
                    spec.add(new SearchStatement("indEliminado", indEliminado, SearchOperation.EQUAL));
            }

        }

        return repository.findAll(spec);
    }

}
