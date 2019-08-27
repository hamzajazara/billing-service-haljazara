package com.billing.model;

import java.time.LocalDate;

import com.billing.model.constant.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingRequest {

	private UserType userType;

	private boolean groceries;

	private double amount;

	private LocalDate userCreatedDate;
}
