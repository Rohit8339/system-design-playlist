// =====================================================
// PROBLEM CODE (WITHOUT FACADE PATTERN)
// =====================================================

/*
Problem:

To book a movie ticket, the client needs to know
and interact with multiple services.

1. Payment Service
2. Seat Reservation Service
3. Ticket Service
4. Notification Service

The client is responsible for calling these services
in the correct order.
*/

class PaymentService {

    public void makePayment(String ticketId, int amount) {
        System.out.println("Payment Success");
    }
}

class SeatReservationService {

    public void reserveSeat(String movieId, String seatNumber) {
        System.out.println("Seat Reserved Successfully");
    }
}

class NotificationService {

    public void sendNotification(String email) {
        System.out.println("Email Sent Successfully");
    }
}

class TicketService {

    public void generateTicket(String movieId, String userId) {
        System.out.println("Ticket Generated Successfully");
    }
}

/*
 * Client Code
 * 
 * PaymentService paymentService = new PaymentService();
 * SeatReservationService seatReservationService =
 * new SeatReservationService();
 * NotificationService notificationService =
 * new NotificationService();
 * TicketService ticketService = new TicketService();
 * 
 * paymentService.makePayment("111",100);
 * seatReservationService.reserveSeat("1","A1");
 * ticketService.generateTicket("1","101");
 * notificationService.sendNotification("abc@gmail.com");
 * 
 * 
 * Problems
 * 
 * 1. Client knows too many classes.
 * 2. Client handles the booking workflow.
 * 3. Any workflow change affects all clients.
 * 4. Code becomes difficult to maintain.
 */

// =====================================================
// SOLUTION CODE (FACADE PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Create a Facade class that hides all the complexity.
 * 
 * Now the client talks only to FacadeService.
 * 
 * FacadeService internally coordinates all services.
 */

// Facade Class
class FacadeService {

    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private TicketService ticketService;

    FacadeService() {

        // Create all required services
        paymentService = new PaymentService();

        seatReservationService = new SeatReservationService();

        notificationService = new NotificationService();

        ticketService = new TicketService();
    }

    // Complete movie booking workflow
    public void bookMovieTicket(String ticketId,
            int amount,
            String movieId,
            String seatNumber,
            String email,
            String userId) {

        paymentService.makePayment(ticketId, amount);

        seatReservationService
                .reserveSeat(movieId, seatNumber);

        ticketService
                .generateTicket(movieId, userId);

        notificationService
                .sendNotification(email);
    }
}

public class FacadePattern {

    public static void main(String[] args) {

        // Client interacts with only one class
        FacadeService facadeService = new FacadeService();

        facadeService.bookMovieTicket(
                "111",
                100,
                "1",
                "A1",
                "abc@gmail.com",
                "101");
    }
}

/*
 * OUTPUT
 * 
 * Payment Success
 * Seat Reserved Successfully
 * Ticket Generated Successfully
 * Email Sent Successfully
 * 
 * 
 * FLOW
 * 
 * WITHOUT FACADE
 * 
 * Client
 * |
 * +--> PaymentService
 * |
 * +--> SeatReservationService
 * |
 * +--> TicketService
 * |
 * +--> NotificationService
 * 
 * 
 * WITH FACADE
 * 
 * Client
 * |
 * v
 * FacadeService
 * |
 * +--> PaymentService
 * |
 * +--> SeatReservationService
 * |
 * +--> TicketService
 * |
 * +--> NotificationService
 * 
 * 
 * BENEFITS
 * 
 * 1. Client code becomes simple.
 * 2. Hides system complexity.
 * 3. Centralizes workflow logic.
 * 4. Easier maintenance.
 * 5. Reduces coupling between client and subsystem.
 */