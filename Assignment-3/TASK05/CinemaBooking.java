
class Cinema {

    boolean[] seats = new boolean[5];

    synchronized void bookSeat(int seatNumber, String customer) {

        if (seatNumber < 1 || seatNumber > 5) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (!seats[seatNumber - 1]) {

            seats[seatNumber - 1] = true;

            System.out.println(customer +
                    " successfully booked seat " + seatNumber);

        } else {

            System.out.println(customer +
                    " failed! Seat " + seatNumber + " is already booked.");
        }
    }

    void displaySeats() {

        System.out.println("\nAvailable Seats:");

        for (int i = 0; i < seats.length; i++) {

            if (seats[i]) {
                System.out.println("Seat " + (i + 1) + ": Booked");
            } else {
                System.out.println("Seat " + (i + 1) + ": Available");
            }
        }
    }
}

class Customer extends Thread {

    Cinema cinema;
    int seatNumber;

    Customer(Cinema cinema, int seatNumber, String name) {
        super(name);
        this.cinema = cinema;
        this.seatNumber = seatNumber;
    }

    public void run() {
        cinema.bookSeat(seatNumber, getName());
    }
}

public class CinemaBooking {

    public static void main(String[] args) throws InterruptedException {

        Cinema cinema = new Cinema();

        Customer c1 = new Customer(cinema, 1, "Customer 1");
        Customer c2 = new Customer(cinema, 1, "Customer 2");
        Customer c3 = new Customer(cinema, 2, "Customer 3");
        Customer c4 = new Customer(cinema, 3, "Customer 4");

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        c1.join();
        c2.join();
        c3.join();
        c4.join();

        cinema.displaySeats();
    }
}
