package com.billing.service;

import com.billing.model.BillingRequest;

public interface BillingService {

	/**
	 * 
	 * @param billingRequest
	 * @return
	 */
	double payableAmount(BillingRequest billingRequest);
}
