package com.example.project.untils;

import com.example.project.models.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;


@Getter
@Setter
@RequiredArgsConstructor
public class ProductFilter {

    public static Specification<Product> filterByName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> filterByCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isEmpty()) {
                return null;
            }

            return cb.like(root.join("categories").get("name"), "%" + category + "%");
        };
    }

    public static Specification<Product> filterByStatus(String status) {
        return (root, query, cb) -> {
            if (status == null || status.isEmpty()) {
                return null;
            }

            return switch (status) {
                case "Sale" -> cb.greaterThan(cb.size(root.get("options")), 0);
                case "Stocking" -> cb.greaterThan(cb.sum(root.join("options").get("quantity")), 0);
                case "Inventory" -> {
                    LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
                    yield cb.lessThanOrEqualTo(root.get("createdAt"), sixMonthsAgo);
                }
                case "SoldOut" -> cb.equal(cb.sum(root.join("options").get("quantity")), 0);
                default -> null;
            };
        };
    }

    public static Sort getSortFromFilter(String filter) {
        return switch (filter) {
            case "Alphabet" -> Sort.by("name").ascending();
            case "ReverseAlphabet" -> Sort.by("name").descending();
            case "HighToLow" -> Sort.by("options.price").descending();
            case "LowToHigh" -> Sort.by("options.price").ascending();
            case "Lasted" -> Sort.by("createdAt").descending();
            case "Oldest" -> Sort.by("createdAt").ascending();
            default -> Sort.unsorted();
        };
    }


    public static Specification<Product> filterByPrice(String filter) {
        return (root, query, cb) -> {

            Map<String, PriceRange> priceLevels = Map.of(
                    "MAX", new PriceRange(400000, null),
                    "HIGHEST", new PriceRange(300000, 400000),
                    "HIGH", new PriceRange(200000, 300000),
                    "MEDIUM", new PriceRange(100000, 200000),
                    "LOW", new PriceRange(0, 100000)
            );

            if (filter == null || filter.isEmpty() || !priceLevels.containsKey(filter.toUpperCase())) {
                return null;
            }

            PriceRange priceRange = priceLevels.get(filter.toUpperCase());
            int minPrice = priceRange.min();
            Integer maxPrice = priceRange.max();

            if (minPrice < 0 || (maxPrice != null && maxPrice < minPrice)) {
                return null;
            }

            if (maxPrice != null) {
                return cb.between(root.join("options").get("price"), minPrice, maxPrice);
            } else {
                return cb.greaterThanOrEqualTo(root.join("options").get("price"), minPrice);
            }
        };
    }

}



