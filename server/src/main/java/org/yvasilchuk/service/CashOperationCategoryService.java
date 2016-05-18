package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.CashOperationCategory;

public interface CashOperationCategoryService {
    CashOperationCategory getById(Integer categoryId);
}
