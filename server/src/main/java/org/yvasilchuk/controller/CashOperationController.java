package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.entity.CashOperation;
import org.yvasilchuk.domain.model.cash.operation.CashOperationModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.domain.response.StatusResponse;
import org.yvasilchuk.service.CashOperationService;

@RestController
@RequestMapping("/api/cash-operation")
public class CashOperationController {
    @Autowired
    @Qualifier("SERVER_CASH_OPERATION_SERVICE")
    CashOperationService cashOperationService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<StatusResponse>> makeCashOperation(@RequestBody CashOperationModel request) {
        CashOperation cashOperation = cashOperationService.makeCashOperation(request);

        StatusResponse status = new StatusResponse();
        status.setStatus("SUCCESSFUL");

        return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.OK);
    }
}
