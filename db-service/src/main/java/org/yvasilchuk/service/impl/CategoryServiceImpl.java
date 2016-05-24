package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.cash.operation.CategoryModel;
import org.yvasilchuk.exceptions.EntityNotFoundException;
import org.yvasilchuk.exceptions.InvalidRequestException;
import org.yvasilchuk.repository.CategoryRepository;
import org.yvasilchuk.repository.UserRepository;
import org.yvasilchuk.service.CategoryService;

import javax.transaction.Transactional;

@Service("DB_CATEGORY_SERVICE")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Category create(CategoryModel request) {
        User owner = userRepository.findOne(request.getOwnerId());
        if (owner == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        Category category = new Category(request);
        category.setOwner(owner);
        category = categoryRepository.save(category);
        return category;
    }

    @Override
    public Category patch(CategoryModel request) {
        if (request.getId() == null) {
            throw new InvalidRequestException(ValidationMessages.CATEGORY_ID_REQUIRED);
        }

        Category category = categoryRepository.findOne(request.getId());
        if (category == null) {
            throw new EntityNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }

        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getType() != null) {
            category.setType(request.getType());
        }
        if (request.getOwnerId() != null) {
            User user = userRepository.findOne(request.getOwnerId());
            if (user == null) {
                throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
            }
            category.setOwner(user);
        }

        category = categoryRepository.save(category);

        return category;
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category get(Integer id) {
        Category category = categoryRepository.findOne(id);
        if (category == null) {
            throw new EntityNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }
        return category;
    }
}
