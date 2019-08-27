package com.billing.service.impl;

import java.time.LocalDate;

import com.billing.model.BillingRequest;
import com.billing.service.BillingService;

public class BillingServiceImpl implements BillingService {

	@Override
	public double payableAmount(BillingRequest billingRequest) {
		double amount = billingRequest.getAmount();
		if (amount <= 0 || billingRequest.isGroceries())
			return amount;

		double discount = 0;
		LocalDate userCreatedDate = billingRequest.getUserCreatedDate();
		if (billingRequest.getUserType() == null
				|| userCreatedDate != null && userCreatedDate.isAfter(LocalDate.now().minusYears(2))) {
			return amount - Math.floor(amount / 100) * 5;
		}

		switch (billingRequest.getUserType()) {
		case EMPLOYEE:
			discount = amount * 0.3;
			break;
		case AFFILIATE:
			discount = amount * 0.1;
			break;
		case CUSTOMER:
			discount = amount * 0.05;
		}
		return amount - discount;
	}

}
