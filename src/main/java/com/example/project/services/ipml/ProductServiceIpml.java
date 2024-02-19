package com.example.project.services.ipml;

import com.example.project.dtos.request.CreateProductDto;
import com.example.project.dtos.request.OptionDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mappers.OptionMapper;
import com.example.project.mappers.ProductMapper;
import com.example.project.models.Category;
import com.example.project.models.Option;
import com.example.project.models.Product;
import com.example.project.repositories.OptionRepository;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.CategoryService;
import com.example.project.services.ProductCategoryService;
import com.example.project.services.ProductService;
import com.example.project.untils.ProductFilter;
import com.example.project.untils.QueryObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceIpml implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final CategoryService categoryService;
    private final OptionRepository optionRepository;
    private final ProductMapper productMapper;
    private final OptionMapper optionMapper;

    @Override
    public Product save(CreateProductDto createProductDto) {
        Product product = productMapper.mapToCreate(createProductDto);

        Category category = categoryService.findById(createProductDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        productRepository.save(product);

        for (OptionDto optionDto : createProductDto.getOptions()) {
            Option option = optionMapper.mapToDto(optionDto);
            option.setProduct(product);
            optionRepository.save(option);
        }

        productCategoryService.addCategoryToProduct(product.getId(), category.getId());

        return product;
    }

    @Override
    public List<Product> findAll(QueryObject queryObject) {
        Sort sort = ProductFilter.getSortFromFilter(queryObject.getFilter());
        PageRequest pageRequest = PageRequest.of(queryObject.getPage() - 1, queryObject.getLimit(), sort);

        return productRepository.findAll(
                Specification.where(ProductFilter.filterByName(queryObject.getName()))
                        .and(ProductFilter.filterByCategory(queryObject.getCategory()))
                        .and(ProductFilter.filterByStatus(queryObject.getStatus()))
                        .and(ProductFilter.filterByPrice(queryObject.getSortBy())),
                pageRequest
        ).getContent();
    }


    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isExists(UUID id) {
        return productRepository.existsById(id);
    }


    @Override
    public Product update(UUID id, CreateProductDto createProductDto) {
        return productRepository.findById(id).map(
                exittingProduct -> {
                    Optional.ofNullable(createProductDto.getName()).ifPresent(exittingProduct::setName);
                    Optional.ofNullable(createProductDto.getBrand()).ifPresent(exittingProduct::setBrand);
                    Optional.ofNullable(createProductDto.getThumbnail()).ifPresent(exittingProduct::setThumbnail);
                    return productRepository.save(exittingProduct);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
