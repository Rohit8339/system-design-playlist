// =====================================================
// PROBLEM CODE (WITHOUT ABSTRACT FACTORY PATTERN)
// =====================================================

/*
Problem:

Suppose we support multiple payment providers such as:

1. RazorPay
2. Stripe

Each provider has related objects:

RazorPay
    -> Payment
    -> Refund

Stripe
    -> Payment
    -> Refund

Without Abstract Factory, developers can accidentally mix providers.
*/

interface Payment {
    void pay();
}

interface Refund {
    void refund();
}

class RazorPayPayment implements Payment {

    @Override
    public void pay() {
        System.out.println("RazorPay Payment");
    }
}

class RazorPayRefund implements Refund {

    @Override
    public void refund() {
        System.out.println("RazorPay Refund");
    }
}

class StripePayment implements Payment {

    @Override
    public void pay() {
        System.out.println("Stripe Payment");
    }
}

class StripeRefund implements Refund {

    @Override
    public void refund() {
        System.out.println("Stripe Refund");
    }
}

/*
 * Problem:
 * 
 * Nothing prevents us from writing:
 * 
 * Payment payment = new RazorPayPayment();
 * Refund refund = new StripeRefund();
 * 
 * Now we are mixing two different providers.
 * 
 * Payment -> RazorPay
 * Refund -> Stripe
 * 
 * This can lead to inconsistent behavior.
 */

// =====================================================
// SOLUTION CODE (ABSTRACT FACTORY PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Use a factory that creates a family of related objects.
 * 
 * RazorPayFactory
 * -> RazorPayPayment
 * -> RazorPayRefund
 * 
 * StripeFactory
 * -> StripePayment
 * -> StripeRefund
 * 
 * This ensures related objects are created together.
 */

// Abstract Factory
// Defines methods for creating related objects
interface PaymentFactory {

    // Create payment object
    Payment createPayment();

    // Create refund object
    Refund createRefund();
}

// Factory responsible for creating RazorPay objects
class RazorPayFactory implements PaymentFactory {

    @Override
    public Payment createPayment() {

        // Create RazorPay payment object
        return new RazorPayPayment();
    }

    @Override
    public Refund createRefund() {

        // Create RazorPay refund object
        return new RazorPayRefund();
    }
}

// Factory responsible for creating Stripe objects
class StripeFactory implements PaymentFactory {

    @Override
    public Payment createPayment() {

        // Create Stripe payment object
        return new StripePayment();
    }

    @Override
    public Refund createRefund() {

        // Create Stripe refund object
        return new StripeRefund();
    }
}

public class AbstractFactory {

    public static void main(String[] args) {

        // Select required provider
        PaymentFactory factory = new RazorPayFactory();

        // Get payment object from factory
        Payment payment = factory.createPayment();

        // Get refund object from factory
        Refund refund = factory.createRefund();

        // Perform payment operation
        payment.pay();

        // Perform refund operation
        refund.refund();
    }
}

/*
 * OUTPUT
 * 
 * RazorPay Payment
 * RazorPay Refund
 * 
 * 
 * CLASS DIAGRAM
 * 
 * 
 * PaymentFactory
 * / \
 * / \
 * / \
 * / \
 * v v
 * 
 * RazorPayFactory StripeFactory
 * | \ | \
 * | \ | \
 * v v v v
 * 
 * RazorPayPayment RazorPayRefund
 * StripePayment StripeRefund
 * 
 * 
 * KEY IDEA
 * 
 * Factory Pattern
 * -> Creates one object
 * 
 * Abstract Factory Pattern
 * -> Creates a family of related objects
 * 
 * Example
 * 
 * RazorPayFactory
 * -> RazorPayPayment
 * -> RazorPayRefund
 * 
 * StripeFactory
 * -> StripePayment
 * -> StripeRefund
 * 
 * Benefit
 * 
 * 1. Related objects stay together
 * 2. Prevents mixing implementations
 * 3. Easy to add new providers
 * 4. Cleaner and scalable design
 */