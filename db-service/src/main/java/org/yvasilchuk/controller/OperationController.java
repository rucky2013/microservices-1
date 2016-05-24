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
import org.yvasilchuk.domain.entity.Operation;
import org.yvasilchuk.domain.model.cash.operation.OperationModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.domain.response.StatusResponse;
import org.yvasilchuk.service.OperationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/cash-operation")
public class OperationController {
    @Autowired
    @Qualifier("DB_OPERATION_SERVICE")
    OperationService operationService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Operation>> createOperation(@RequestBody @Valid OperationModel request) {
        Operation operation = operationService.create(request);
        return new ResponseEntity<>(new BaseResponse<>(operation), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<BaseResponse<Operation>> patchOperation(@RequestBody @Valid OperationModel request) {
        Operation operation = operationService.patch(request);
        return new ResponseEntity<>(new BaseResponse<>(operation), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResponse<StatusResponse>> createOperation(@PathVariable("id") Integer id) {
        operationService.delete(id);
        StatusResponse response = new StatusResponse();
        response.setStatus("SUCCESS");
        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Operation>> getOperation(@PathVariable("id") Integer id) {
        Operation operation = operationService.get(id);
        return new ResponseEntity<>(new BaseResponse<>(operation), HttpStatus.OK);
    }
}
