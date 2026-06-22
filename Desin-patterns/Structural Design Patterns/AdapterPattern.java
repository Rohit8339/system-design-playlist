// =====================================================
// PROBLEM CODE (WITHOUT ADAPTER PATTERN)
// =====================================================

/*
Problem:

PaymentService is designed to work only with classes
that implement the Payment interface.

RazorPay and Stripe work because they implement Payment.

But a new payment provider has a different interface.
Because of this, it cannot be used directly inside
PaymentService.
*/

interface Payment {
    void Pay(String paymentId, int amount);
}

// Supported payment method
class RazorPay implements Payment {

    @Override
    public void Pay(String paymentId, int amount) {
        System.out.println("Payment Success");
    }
}

// Supported payment method
class Stripe implements Payment {

    @Override
    public void Pay(String paymentId, int amount) {
        System.out.println("Payment Successful");
    }
}

// New third-party payment provider
// We cannot modify this class
class UnspportedPaymentMethod {

    public void makePayment(String invoiceId, int amount) {
        System.out.println("Payment Successful");
    }
}

// Service works only with Payment interface
class PaymentService {

    private Payment payment;

    PaymentService(Payment payment) {
        this.payment = payment;
    }

    public void checkOut(String paymentId, int amount) {
        payment.Pay(paymentId, amount);
    }
}

/*
 * Works
 * 
 * PaymentService service =
 * new PaymentService(new RazorPay());
 * 
 * Problem
 * 
 * PaymentService service =
 * new PaymentService(new UnspportedPaymentMethod());
 * 
 * Compilation Error
 * 
 * Reason:
 * UnspportedPaymentMethod does not implement Payment.
 */

// =====================================================
// SOLUTION CODE (ADAPTER PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Create an Adapter that converts
 * UnspportedPaymentMethod into Payment.
 * 
 * Now PaymentService can continue working
 * without any changes.
 */

// Adapter Class
class PaymentAdapter implements Payment {

    private UnspportedPaymentMethod unspportedPaymentMethod;

    PaymentAdapter(UnspportedPaymentMethod unspportedPaymentMethod) {
        this.unspportedPaymentMethod = unspportedPaymentMethod;
    }

    @Override
    public void Pay(String paymentId, int amount) {

        // Translate Pay()
        // into makePayment()
        unspportedPaymentMethod.makePayment(paymentId, amount);
    }
}

public class AdapterPattern {

    public static void main(String[] args) {

        // Third-party object
        UnspportedPaymentMethod unspportedPaymentMethod = new UnspportedPaymentMethod();

        // Adapter wraps the third-party object
        Payment payment = new PaymentAdapter(unspportedPaymentMethod);

        // Service continues to work with Payment interface
        PaymentService service = new PaymentService(payment);

        service.checkOut("1111", 11);
    }
}

/*
 * OUTPUT
 * 
 * Payment Successful
 * 
 * 
 * FLOW
 * 
 * Without Adapter
 * 
 * PaymentService
 * |
 * v
 * Payment
 * 
 * UnspportedPaymentMethod
 * 
 * ❌ Cannot connect because interfaces are different
 * 
 * 
 * With Adapter
 * 
 * PaymentService
 * |
 * v
 * Payment
 * |
 * v
 * PaymentAdapter
 * |
 * v
 * UnspportedPaymentMethod
 * 
 * ✅ Adapter acts as a translator
 * 
 * 
 * BENEFITS
 * 
 * 1. No changes to existing code
 * 2. Easy integration of third-party libraries
 * 3. Better flexibility
 * 4. Existing business logic remains untouched
 */