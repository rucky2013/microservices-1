package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.model.cash.operation.CategoryModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.domain.response.StatusResponse;
import org.yvasilchuk.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/cash-operation-category")
public class CategoryController {
    @Autowired
    @Qualifier("DB_CATEGORY_SERVICE")
    CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Category>> createCategory(@RequestBody @Valid CategoryModel request) {
        Category category = categoryService.create(request);
        return new ResponseEntity<>(new BaseResponse<>(category), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<BaseResponse<Category>> patchCategory(@RequestBody @Valid CategoryModel request) {
        Category category = categoryService.patch(request);
        return new ResponseEntity<>(new BaseResponse<>(category), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResponse<StatusResponse>> createCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        StatusResponse response = new StatusResponse();
        response.setStatus("SUCCESS");
        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Category>> getCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.get(id);
        return new ResponseEntity<>(new BaseResponse<>(category), HttpStatus.OK);
    }
}
