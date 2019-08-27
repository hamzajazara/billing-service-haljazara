package com.billing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.billing.model.BillingRequest;
import com.billing.model.constant.UserType;
import com.billing.service.impl.BillingServiceImpl;

@RunWith(SpringRunner.class)
public class BillingAppTests {

	@InjectMocks
	private BillingServiceImpl billingService;

	@Test
	public void billingDiscount_Customer_greaterThanTwoYears() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().userType(UserType.CUSTOMER)
				.userCreatedDate(LocalDate.now().minusYears(3)).amount(800).groceries(false).build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(760.0, payableAmount, 0);
	}

	@Test
	public void billingDiscount_Customer_lessThanTwoYears() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().userType(UserType.CUSTOMER)
				.userCreatedDate(LocalDate.now().minusYears(1)).amount(500).groceries(false).build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(475.0, payableAmount, 0);
	}

	@Test
	public void billingDiscount_Employee() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().userType(UserType.EMPLOYEE).amount(200)
				.groceries(false).build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(140.0, payableAmount, 0);
	}

	@Test
	public void billingDiscount_Affiliate() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().userType(UserType.AFFILIATE).amount(150.0)
				.groceries(false).build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(135.0, payableAmount, 0);
	}

	@Test
	public void billingDiscount_Groceries() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().userType(UserType.CUSTOMER).amount(200).groceries(true)
				.build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(200.0, payableAmount, 0);
	}

	@Test
	public void billingDiscount_forEveryUser() {
		// Arrange
		BillingRequest billingRequest = BillingRequest.builder().amount(990.0).groceries(false).build();

		// Act
		double payableAmount = billingService.payableAmount(billingRequest);

		// Assert
		assertEquals(945.0, payableAmount, 0);
	}
}