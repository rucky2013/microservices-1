package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.model.cash.operation.CategoryModel;

public interface CategoryService {

    Category create(CategoryModel request);

    Category patch(CategoryModel request);

    void delete(Integer id);

    Category get(Integer id);
}
