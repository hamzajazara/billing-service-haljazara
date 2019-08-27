package com.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.billing.model.BillingRequest;
import com.billing.service.BillingService;

@RestController
public class BillingController {

	private BillingService billingService;

	/**
	 * 
	 * @param billingService
	 */
	@Autowired
	public BillingController(BillingService billingService) {
		this.billingService = billingService;
	}

	/**
	 * 
	 * @param billingRequest
	 * @return
	 */
	public ResponseEntity<Double> calculatePayableAmount(@RequestBody BillingRequest billingRequest) {
		return new ResponseEntity<>(billingService.payableAmount(billingRequest), HttpStatus.OK);
	}
}
