package com.example.atm;

/**
 * The possible statuses for a payment operation managed by {@link PaymentProcessor}.
 */
public enum PaymentStatus {
	
	/**
	 * The payment operation succeeded.
	 */
	SUCCESS,
	/**
	 * The payment operation failed for any reason.
	 */
	FAILURE

}
