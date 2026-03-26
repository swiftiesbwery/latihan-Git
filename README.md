# Sistem Manajemen Aquarium Singapura

## Deskripsi Kasus

Sistem Manajemen Aquarium Singapura adalah program berbasis Object-Oriented Programming (OOP) yang digunakan untuk mengelola kunjungan wisata di aquarium.

Pengunjung dapat:
- memasukkan data diri,
- memilih jenis tiket,
- menentukan zona favorit,
- menambahkan aktivitas tambahan,
- melihat total pembayaran.

Zona yang tersedia antara lain:
- Ocean Dome
- Shark Tunnel
- Coral Habitat
- Penguin Bay

Jenis tiket:
- Regular
- VIP
- Student

Aktivitas tambahan:
- Feeding Session
- Glass Bottom Tour
- Behind The Scene Tour

---

## Keunikan Program

- Program berbasis input user (tidak hardcoded)
- Pengunjung dapat menentukan zona favorit sendiri
- Aktivitas tambahan dapat disesuaikan jumlah dan harganya
- Menggunakan konsep OOP secara lengkap
- Fleksibel untuk dikembangkan

---

## Class Diagram

```mermaid
classDiagram
    class Person {
        -String name
        -int age
        +getName() String
        +getAge() int
    }

    class Visitor {
        -String visitorId
        -List~String~ favoriteZones
        +addFavoriteZone(String zone) void
        +getVisitorId() String
        +getFavoriteZones() List~String~
    }

    class Ticket {
        <<abstract>>
        -String ticketCode
        -double basePrice
        +calculatePrice() double
        +getTicketType() String
    }

    class RegularTicket
    class VIPTicket
    class StudentTicket

    class Activity {
        -String activityName
        -double activityPrice
        +getActivityName() String
        +getActivityPrice() double
    }

    class Booking {
        -String bookingId
        -Visitor visitor
        -Ticket ticket
        -List~Activity~ activities
        +addActivity(Activity activity) void
        +calculateTotal() double
        +printSummary() void
    }

    Person <|-- Visitor
    Ticket <|-- RegularTicket
    Ticket <|-- VIPTicket
    Ticket <|-- StudentTicket
    Booking --> Visitor
    Booking --> Ticket
    Booking --> Activity

# Screenshot output
<img width="1399" height="892" alt="image" src="https://github.com/user-attachments/assets/20ffce68-432d-4b0e-9732-19ff8ade2856" />
<img width="1393" height="961" alt="image" src="https://github.com/user-attachments/assets/85654e44-b13c-45dd-8f69-f78a99f2c156" />
