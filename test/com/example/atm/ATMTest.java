package com.example.atm;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ATMTest {

    private AmountSelector amountSelector = Mockito.mock(AmountSelector.class);
    private CashManager cashManager = Mockito.mock(CashManager.class);
    private PaymentProcessor paymentProcessor = Mockito.mock(PaymentProcessor.class);
    private ATM tested = new ATM(amountSelector,cashManager,paymentProcessor);
    private static final int positiveSelectedValue = 20;
    private static final int negativeSelectedValue = -20;

    @Before
    public void beforeEach() {

        Mockito.reset(amountSelector);
        Mockito.reset(cashManager);
        Mockito.reset(paymentProcessor);
    }

    @Test( expected = ATMTechnicalException.class)
    public void whenLockAmountIsNegative() throws ATMTechnicalException {

        Mockito.when(amountSelector.selectAmount()).thenReturn(negativeSelectedValue);
        tested.runCashWithdrawal();
    }

    @Test
    public void whenSelectedAmountIsBiggerThanAvailableCash() throws ATMTechnicalException {

        Mockito.when(amountSelector.selectAmount()).thenReturn(positiveSelectedValue);
        Mockito.when(cashManager.canDeliver(amountSelector.selectAmount())).thenReturn(false);
        final ATMStatus testedStatus = tested.runCashWithdrawal();
        Assertions.assertThat(testedStatus).isEqualTo(ATMStatus.CASH_NOT_AVAILABLE);
    }

    @Test
    public void whenSelectedAmountIsSmallerThanAvailableCash() throws ATMTechnicalException {

        Mockito.when(amountSelector.selectAmount()).thenReturn(positiveSelectedValue);
        Mockito.when(cashManager.canDeliver(amountSelector.selectAmount())).thenReturn(true);
        final ATMStatus testedStatus = tested.runCashWithdrawal();
        Assertions.assertThat(testedStatus).isEqualTo(ATMStatus.DONE);
    }

    @Test
    public void whenPaymentProssessIsSuccess() throws ATMTechnicalException {

        Mockito.when(amountSelector.selectAmount()).thenReturn(positiveSelectedValue);
        Mockito.when(cashManager.canDeliver(amountSelector.selectAmount())).thenReturn(true);
        Mockito.when(paymentProcessor.pay(amountSelector.selectAmount())).thenReturn(PaymentStatus.SUCCESS);
        final ATMStatus testedStatus = tested.runCashWithdrawal();
        Assertions.assertThat(testedStatus).isEqualTo(ATMStatus.DONE);
    }

    @Test
    public void whenPaymentProssessIsFailure() throws ATMTechnicalException {

        Mockito.when(amountSelector.selectAmount()).thenReturn(positiveSelectedValue);
        Mockito.when(cashManager.canDeliver(amountSelector.selectAmount())).thenReturn(true);
        Mockito.when(paymentProcessor.pay(amountSelector.selectAmount())).thenReturn(PaymentStatus.FAILURE);
        final ATMStatus testedStatus = tested.runCashWithdrawal();
        Assertions.assertThat(testedStatus).isEqualTo(ATMStatus.PAYMENT_REJECTED);
    }
}
