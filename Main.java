import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Visitor extends Person {
    private String visitorId;
    private List<String> favoriteZones;

    public Visitor(String name, int age, String visitorId) {
        super(name, age);
        this.visitorId = visitorId;
        this.favoriteZones = new ArrayList<>();
    }

    public void addFavoriteZone(String zone) {
        favoriteZones.add(zone);
    }

    public String getVisitorId() {
        return visitorId;
    }

    public List<String> getFavoriteZones() {
        return favoriteZones;
    }
}

abstract class Ticket {
    private String ticketCode;
    protected double basePrice;

    public Ticket(String ticketCode, double basePrice) {
        this.ticketCode = ticketCode;
        this.basePrice = basePrice;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public abstract double calculatePrice();
    public abstract String getTicketType();
}

class RegularTicket extends Ticket {
    public RegularTicket(String ticketCode) {
        super(ticketCode, 40.0);
    }

    public double calculatePrice() {
        return basePrice;
    }

    public String getTicketType() {
        return "Regular";
    }
}

class VIPTicket extends Ticket {
    public VIPTicket(String ticketCode) {
        super(ticketCode, 70.0);
    }

    public double calculatePrice() {
        return basePrice + 20.0;
    }

    public String getTicketType() {
        return "VIP";
    }
}

class StudentTicket extends Ticket {
    public StudentTicket(String ticketCode) {
        super(ticketCode, 40.0);
    }

    public double calculatePrice() {
        return basePrice * 0.8;
    }

    public String getTicketType() {
        return "Student";
    }
}

class Activity {
    private String activityName;
    private double activityPrice;

    public Activity(String activityName, double activityPrice) {
        this.activityName = activityName;
        this.activityPrice = activityPrice;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getActivityPrice() {
        return activityPrice;
    }
}

class Booking {
    private String bookingId;
    private Visitor visitor;
    private Ticket ticket;
    private List<Activity> activities;

    public Booking(String bookingId, Visitor visitor, Ticket ticket) {
        this.bookingId = bookingId;
        this.visitor = visitor;
        this.ticket = ticket;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public double calculateTotal() {
        double total = ticket.calculatePrice();
        for (Activity activity : activities) {
            total += activity.getActivityPrice();
        }
        return total;
    }

    public void printSummary() {
        System.out.println("\n===== AQUARIUM SINGAPURA BOOKING SUMMARY =====");
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Visitor ID     : " + visitor.getVisitorId());
        System.out.println("Visitor Name   : " + visitor.getName());
        System.out.println("Age            : " + visitor.getAge());
        System.out.println("Ticket Code    : " + ticket.getTicketCode());
        System.out.println("Ticket Type    : " + ticket.getTicketType());
        System.out.println("Ticket Price   : SGD " + ticket.calculatePrice());

        System.out.println("Favorite Zones :");
        if (visitor.getFavoriteZones().isEmpty()) {
            System.out.println("- Tidak ada zona favorit");
        } else {
            for (String zone : visitor.getFavoriteZones()) {
                System.out.println("- " + zone);
            }
        }

        System.out.println("Activities     :");
        if (activities.isEmpty()) {
            System.out.println("- No additional activities");
        } else {
            for (Activity activity : activities) {
                System.out.println("- " + activity.getActivityName() + " (SGD " + activity.getActivityPrice() + ")");
            }
        }

        System.out.println("Total Payment  : SGD " + calculateTotal());
        System.out.println("==============================================");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM MANAJEMEN AQUARIUM SINGAPURA ===");

        System.out.print("Masukkan Booking ID        : ");
        String bookingId = input.nextLine();

        System.out.print("Masukkan Nama Visitor      : ");
        String name = input.nextLine();

        System.out.print("Masukkan Umur Visitor      : ");
        int age = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan Visitor ID        : ");
        String visitorId = input.nextLine();

        Visitor visitor = new Visitor(name, age, visitorId);

        System.out.print("Berapa zona favorit yang ingin dimasukkan? ");
        int totalZones = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= totalZones; i++) {
            System.out.print("Masukkan zona favorit ke-" + i + " : ");
            String zone = input.nextLine();
            visitor.addFavoriteZone(zone);
        }

        System.out.println("\nPilih Jenis Tiket:");
        System.out.println("1. Regular");
        System.out.println("2. VIP");
        System.out.println("3. Student");
        System.out.print("Masukkan pilihan tiket (1/2/3): ");
        int ticketChoice = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan Kode Tiket        : ");
        String ticketCode = input.nextLine();

        Ticket ticket;
        switch (ticketChoice) {
            case 1:
                ticket = new RegularTicket(ticketCode);
                break;
            case 2:
                ticket = new VIPTicket(ticketCode);
                break;
            case 3:
                ticket = new StudentTicket(ticketCode);
                break;
            default:
                ticket = new RegularTicket(ticketCode);
        }

        Booking booking = new Booking(bookingId, visitor, ticket);

        System.out.print("\nBerapa aktivitas tambahan yang ingin dimasukkan? ");
        int totalActivities = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= totalActivities; i++) {
            System.out.print("Masukkan nama aktivitas ke-" + i + "  : ");
            String activityName = input.nextLine();

            System.out.print("Masukkan harga aktivitas ke-" + i + " : ");
            double activityPrice = input.nextDouble();
            input.nextLine();

            booking.addActivity(new Activity(activityName, activityPrice));
        }

        booking.printSummary();
        input.close();
    }
}
