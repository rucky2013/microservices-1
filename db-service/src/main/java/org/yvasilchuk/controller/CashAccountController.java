package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.model.cash.account.CashAccountModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.domain.response.StatusResponse;
import org.yvasilchuk.service.CashAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cash-account")
public class CashAccountController {
    @Autowired
    @Qualifier("DB_CASH_ACCOUNT_SERVICE")
    CashAccountService cashAccountService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<CashAccount>> createCashAccount(@RequestBody @Valid CashAccountModel request) {
        CashAccount cashAccount = cashAccountService.create(request);
        return new ResponseEntity<>(new BaseResponse<>(cashAccount), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<BaseResponse<CashAccount>> patchCashAccount(@RequestBody @Valid CashAccountModel request) {
        CashAccount cashAccount = cashAccountService.patch(request);
        return new ResponseEntity<>(new BaseResponse<>(cashAccount), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResponse<StatusResponse>> createCashAccount(@PathVariable("id") Integer id) {
        cashAccountService.delete(id);
        StatusResponse response = new StatusResponse();
        response.setStatus("SUCCESS");
        return new ResponseEntity<>(new BaseResponse<>(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<CashAccount>> getCashAccount(@PathVariable("id") Integer id) {
        CashAccount cashAccount = cashAccountService.get(id);
        return new ResponseEntity<>(new BaseResponse<>(cashAccount), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<List<CashAccount>>> findCashAccountByOwnerId(@RequestParam(name = "owner_id") Integer ownerId) {
        List<CashAccount> cashAccounts = cashAccountService.find(ownerId);
        return new ResponseEntity<>(new BaseResponse<>(cashAccounts), HttpStatus.OK);
    }
}
