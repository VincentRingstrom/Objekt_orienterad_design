/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.seminar3.model;

import se.kth.iv1350.seminar3.model.DTO.PaymentDTO;

/**
 *
 * @author SVT
 */
public interface AmountPaidObserver {
    /**
     * Invoked when a sale has been paid.
     * @param paymentDTO The payment details. // THE INTERFACE. 
     */
    void newPayment(PaymentDTO paymentDTO);
}
