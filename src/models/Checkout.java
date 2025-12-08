package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy");

    private Room selectedRoom;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfRooms;
    private int numberOfAdults;
    private int numberOfChildren;
    private boolean isMember;

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String iddCode;
    private String mobileNumber;
    private boolean smsConfirmation;

    private Map<String, Integer> selectedServices;

    private int nights;
    private double roomCharges;
    private double serviceTax;
    private double memberDiscount;
    private double servicesTotal;
    private double totalCharges;

    public static List<Checkout> checkoutList = new ArrayList<>();

    public Checkout(Room room, String checkIn, String checkOut, int rooms, int adults, int children, boolean member) {
        this.selectedRoom = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.numberOfRooms = rooms;
        this.numberOfAdults = adults;
        this.numberOfChildren = children;
        this.isMember = member;
        this.selectedServices = new HashMap<>();
        calculateAllCharges();
    }

    // ================= CALCULATION METHODS =================

    public static int calculateNights(String checkIn, String checkOut) {
        if (checkIn == null || checkOut == null) return 1;

        LocalDate in = LocalDate.parse(checkIn, DATE_FORMATTER);
        LocalDate out = LocalDate.parse(checkOut, DATE_FORMATTER);

        int nights = (int) ChronoUnit.DAYS.between(in, out);
        return Math.max(nights, 1);
    }



    private void calculateAllCharges() {
        this.nights = calculateNights(checkInDate, checkOutDate);
        double roomPricePerNight = selectedRoom.calculatePrice();
        this.roomCharges = roomPricePerNight * numberOfRooms * nights;
        this.serviceTax = roomCharges * 0.12;
        this.memberDiscount = isMember ? roomCharges * 0.10 : 0;
        this.servicesTotal = calculateServicesTotal();
        this.totalCharges = roomCharges + serviceTax - memberDiscount + servicesTotal;
    }

    private double calculateServicesTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : selectedServices.entrySet()) {
            total += getServicePrice(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    private double getServicePrice(String serviceName) {
        Map<String, Double> servicePrices = new HashMap<>();
        servicePrices.put("Daily Buffet Breakfast", 500.0);
        servicePrices.put("Dining Credit of CNY 150 for your entire stay", 750.0);
        servicePrices.put("Dining Credit of CNY 300 for your entire stay", 1500.0);
        servicePrices.put("Dining Credit of CNY 600 for your entire stay", 3000.0);
        servicePrices.put("Laundry – Wash & Fold (one bag, up to 10 pieces)", 800.0);
        servicePrices.put("Extra Bed (per night)", 1000.0);
        servicePrices.put("Breakfast Package", 600.0);
        servicePrices.put("Airport Pickup (Private Car)", 1800.0);
        servicePrices.put("Late Checkout (until 4:00 PM)", 900.0);
        servicePrices.put("Spa Package (60 min)", 2250.0);
        servicePrices.put("Poolside Cabana (full day)", 1000.0);
        servicePrices.put("Club Lounge Access", 1750.0);
        servicePrices.put("Welcome Package", 500.0);
        return servicePrices.getOrDefault(serviceName, 0.0);
    }

    // ================= GUEST INFO =================

    public void setGuestInfo(String firstName, String lastName, String email, String country, String iddCode, String mobile, boolean sms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.iddCode = iddCode;
        this.mobileNumber = mobile;
        this.smsConfirmation = sms;
    }

    public boolean isGuestInfoComplete() {
        return firstName != null && !firstName.isEmpty() &&
               lastName != null && !lastName.isEmpty() &&
               email != null && email.contains("@") &&
               country != null && !country.equals("Please select") &&
               mobileNumber != null && !mobileNumber.isEmpty();
    }

    // ================= ADD-ON SERVICES =================

    public void addService(String serviceName, int quantity) {
        if (quantity > 0) selectedServices.put(serviceName, quantity);
        else selectedServices.remove(serviceName);
        calculateAllCharges();
    }

    public int getServiceQuantity(String serviceName) {
        return selectedServices.getOrDefault(serviceName, 0);
    }

    public void clearServices() {
        selectedServices.clear();
        calculateAllCharges();
    }

    // ================= BOOKING MANAGEMENT =================

    public boolean completeBooking() {
        if (!isGuestInfoComplete()) return false;
        checkoutList.add(this);
        return true;
    }

    public String getBookingSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== BOOKING CONFIRMATION ===\n\n");
        summary.append("Guest: ").append(firstName).append(" ").append(lastName).append("\n");
        summary.append("Email: ").append(email).append("\n");
        summary.append("Mobile: ").append(iddCode).append(" ").append(mobileNumber).append("\n\n");
        summary.append("Room: ").append(selectedRoom.getName()).append("\n");
        summary.append("Check-in: ").append(checkInDate).append("\n");
        summary.append("Check-out: ").append(checkOutDate).append("\n");
        summary.append("Nights: ").append(nights).append("\n");
        summary.append("Rooms: ").append(numberOfRooms).append("\n");
        summary.append("Guests: ").append(numberOfAdults).append(" Adult(s), ").append(numberOfChildren).append(" Children\n\n");
        summary.append("=== CHARGES ===\n");
        summary.append(String.format("Room Charges: PHP %.2f\n", roomCharges));
        summary.append(String.format("Service & Tax (12%%): PHP %.2f\n", serviceTax));
        if (memberDiscount > 0) summary.append(String.format("Member Discount (10%%): -PHP %.2f\n", memberDiscount));
        if (servicesTotal > 0) summary.append(String.format("Add-on Services: PHP %.2f\n", servicesTotal));
        summary.append(String.format("\nTOTAL: PHP %.2f\n", totalCharges));
        return summary.toString();
    }

    // ================= GETTERS & SETTERS =================

    public Room getSelectedRoom() { return selectedRoom; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public int getNumberOfRooms() { return numberOfRooms; }
    public int getNumberOfAdults() { return numberOfAdults; }
    public int getNumberOfChildren() { return numberOfChildren; }
    public boolean isMember() { return isMember; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getCountry() { return country; }
    public String getIddCode() { return iddCode; }
    public String getMobileNumber() { return mobileNumber; }
    public boolean isSmsConfirmation() { return smsConfirmation; }
    public int getNights() { return nights; }
    public double getRoomCharges() { return roomCharges; }
    public double getServiceTax() { return serviceTax; }
    public double getMemberDiscount() { return memberDiscount; }
    public double getServicesTotal() { return servicesTotal; }
    public double getTotalCharges() { return totalCharges; }
    public Map<String, Integer> getSelectedServices() { return selectedServices; }

    public void setMember(boolean member) { this.isMember = member; calculateAllCharges(); }
    public void setNumberOfRooms(int rooms) { this.numberOfRooms = rooms; calculateAllCharges(); }
    public void setCheckInDate(String checkIn) { this.checkInDate = checkIn; calculateAllCharges(); }
    public void setCheckOutDate(String checkOut) { this.checkOutDate = checkOut; calculateAllCharges(); }

}
