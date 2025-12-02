package models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    // 1. ATTRIBUTES
    private String name;
    private String type;
    private String Btype;
    private String description;
    private String imagePath;
    private String size;
    private String[] features;
    private String[] amenities;
    private double pricePerNight;

    // 2. CONSTRUCTOR
    public Room(String name, String type, String Btype, String description, String imagePath, String size, String[] features, String[] amenities, double pricePerNight) {
        this.name = name;
        this.type = type;
        this.Btype = Btype;
        this.description = description;
        this.imagePath = imagePath;
        this.size = size;
        this.features = features;
        this.amenities = amenities;
        this.pricePerNight = pricePerNight;
    }

    // 3. GETTERS
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getBtype() { return this.Btype; }

    public String getImagePath() { return imagePath; }
    public String getSize() { return size; }
    public String[] getFeatures() { return features; }
    public String[] getAmenities() { return amenities; }
    public double getPricePerNight() { return pricePerNight; }
    public String getType() { return type; } 
    public void setBtype(String Btype) {
        this.Btype = Btype;
    }

    // 4. STATIC DATA STORAGE
    public static List<Room> roomList = new ArrayList<>();
    
    public double calculatePrice() {

        // SUPERIOR ROOM
        if (name.equals("Superior Room")) {
            if (Btype.equals("Twin")) return 8500;
            if (Btype.equals("King")) return 9500;
        }

        // DELUXE ROOM
        if (name.equals("Deluxe Room")) {
            if (Btype.equals("Twin")) return 10500;
            if (Btype.equals("King")) return 12000;
        }

        // EXECUTIVE ROOM
        if (name.equals("Executive Room")) {
            if (Btype.equals("Twin")) return 12500;
            if (Btype.equals("King")) return 14000;
        }
        
        if (name.equals("Horizon Club Deluxe Room")) {
            if (Btype.equals("Twin")) return 14000;
            if (Btype.equals("King")) return 16000;
        }

        
        if (name.equals("Horizon Club Business Room")) {
            if (Btype.equals("Twin")) return 15500;
            if (Btype.equals("King")) return 19000;
        }

        
        if (name.equals("Deluxe Suite")) {
            if (Btype.equals("Twin")) return 20500;
            if (Btype.equals("King")) return 22500;
        }
        
        if (name.equals("Specialty Suite")) {
            if (Btype.equals("Twin")) return 30500;
            if (Btype.equals("King")) return 34500;
        }

        
        if (name.equals("Presidential Suite")) {
            if (Btype.equals("Twin")) return 34500;
            if (Btype.equals("King")) return 35000;
        }

        
        if (name.equals("Two Deluxe Rooms Connecting")) {
        	return 22500;
        }
        
        return pricePerNight;
    }

    static {
        // --- Shared Common Amenities for Standard Rooms ---
        String[] standardAmenities = {
            "Hair dryer", "Shangri-La toiletries", "Separate shower & bath", "300-count cotton linen",
            "Wi-Fi and broadband Internet access", "Cable LED Television",
            "Full-size executive writing desk", "International Direct Dial telephone", "Voice mail", "Electronic safe",
            "Coffee / tea", "Mini-bar"
        };

        // 1. Superior Room
        roomList.add(new Room(
            "Superior Room",
            "Room",
            "",
            "Elegant comfort with magnificent views of Makati City.\n" +
            "Extra space, exquisite comfort, and a glamorous view of the city define stays in our Deluxe Rooms. Each room is fully supplied with premium Shangri-La Hotel amenities.",
            "images/room1.jpg", 
            "33 - 42 sqm / 355 - 452 sqf",
            new String[] { 
                "Magnificent views of Makati and Ayala Avenues and the Glorietta Commercial Centre.",
                "Indulgent bed with 300-count cotton linen.",
                "A spacious bathroom with separate shower.",
                "Wi-Fi"
            },
            standardAmenities, 
            8500.00
        ));

        // 2. Deluxe Room
        roomList.add(new Room(
            "Deluxe Room",
            "Room",
            "",
            "Exquisite comfort with stellar city views.\n" + 
            "Extra space and exquisite comfort define Deluxe Rooms, each fully supplied with premium Shangri-La Hotel amenities.",
            "images/room2.jpg", 
            "33 - 42 sqm / 355 - 452 sqf",
            new String[] { 
                "Magnificent views of Makati and Ayala Avenues and the Glorietta Commercial Centre",
                "Located in higher floors",
                "Indulgent bed with 300-count cotton linen",
                "A spacious bathroom with separate shower"
            },
            standardAmenities,
            10500.00
        ));
        
        // 3. Executive Room
        roomList.add(new Room(
            "Executive Room",
            "Room",
            "",
            "Indulgent rooms with panoramic city views.\n" +
            "Makati Shangri-La's Executive Rooms are spacious corner rooms that offer a panoramic skyline of the central financial district.",
            "images/room3.jpg", 
            "53 - 54 sqm / 570 - 581 sqf",
            new String[] { 
                "Panoramic views of Financial District",
                "A spacious bathroom with separate shower and bath tub areas."
            },
            standardAmenities, 
            12500.00
        ));

        // 4. Horizon Club Deluxe Room
        roomList.add(new Room(
            "Horizon Club Deluxe Room",
            "Room",
            "",
            "Modern luxury with resplendent city views.\n" +
            "With the comfort and amenities of Deluxe Rooms, plus exclusive Horizon Club privileges, "
            + "Horizon Club Deluxe Rooms are an excellent choice for the traveller who requires added personalised attention and services.",
            "images/room4.jpg", 
            "33 - 42 sqm / 355 - 452 sqf",
            new String[] { 
                "Magnificent views of Manila cityscape",
                "Large, modern bathroom with bathrobes",
                "Access to the Horizon Club Lounge with benefits including: daily breakfast, evening cocktails and use of meeting room facilities."
            },
            standardAmenities, 
            14500.00
        ));

        // 5. Horizon Club Business Room (Special Tech Amenities)
        roomList.add(new Room(
            "Horizon Club Business Room",
            "Room",
            "",
            "Take productivity to new heights.\n" +
            "Our new Horizon Club Business Room is the perfect solution for an unparalleled business experience. "
            + "Equipped with a range of business-friendly amenities and features, you'll have everything you need to stay connected and productive.",
            "images/room5.jpg", 
            "53 - 54 sqm / 570 - 581 sqf",
            new String[] {
            	    "Panoramic views of Ayala Avenue",
            	    "Luxury one-bedroom suite with separate sitting room",
            	    "Separate walk-in closet",
            	    "Large, modern bathrooms with bathrobes",
            	    "Access to the Horizon Club Lounge with benefits including: daily breakfast, " +
            	    "evening cocktails and use of meeting room facilities"
            	},
            new String[] {
                "Omnidesk ergonomic chair", "Omnidesk Ascent: Wildwood adjustable standing desk",
                "Dell 27-inch 4K USB-C hub monitor (dual screen)", "Logitech Brio 4K Webcam",
                "Logitech Master MX3S wireless mouse (graphite)", "Logitech MX mechanical wireless illuminated keyboard",
                "Poly Sync 40 speakerphone", "Mobile stand, fast-charging cable adaptor",
                "International Direct Dial telephone", "Voice mail", "Electronic safe", "In-room fax",
                "Coffee / tea-making facilities", "Mini-bar"
            }, 
            16500.00
        ));

        // 6. Deluxe Suite (Upgraded Amenities)
        roomList.add(new Room(
            "Deluxe Suite",
            "Suite",
            "",
            "Extra space providing luxurious comfort.\n" +
            "For those requiring extra space to entertain guests of their own, our Deluxe Suites offer a separate living area and amenities to accommodate the discerning traveller.",
            "images/room6.jpg", 
            "74 sqm / 797 sqf",
            new String[] { 
                "Panoramic views of Ayala Avenue",
                "Luxury one-bedroom suite with separate sitting room",
                "Separate walk-in closet",
                "Large, modern bathrooms with bathrobes",
                "Access to the Horizon Club Lounge with benefits including: daily breakfast, evening cocktails and use of meeting room facilities"
            },
            new String[] {
                "Hair dryer", "Upgraded Shangri-La toiletries", "Iron & ironing board", "Separate shower & bathtub",
                "Wi-Fi and broadband Internet access", "Cable LED Television",
                "Full-size executive writing desk", "International Direct Dial telephone", "Voice mail", "Electronic safe",
                "Coffee / tea", "Mini-bar"
            }, 
            25000.00
        ));
        
        // 7. Specialty Suite (Upgraded Amenities)
        roomList.add(new Room(
            "Specialty Suite",
            "Suite",
            "",
            "Luxurious suite with views of Ayala Avenue.\n" +
            "Specialty Suites are equipped with expansive facilities and premium amenities, an ideal choice for families or business travellers needing to conduct private meetings.",
            "images/room7.jpg", 
            "183 sqm / 1,969 sqf",
            new String[] { 
                "Panoramic views of Ayala Avenue",
                "Luxury two-bedroom suite with separate parlour / sitting room, kitchenette and dining area",
                "Separate walk-in closet",
                "In-suite butler service upon request",
                "Access to the Horizon Club Lounge with benefits including: daily breakfast, evening cocktails and use of meeting room facilities"
            },
            new String[] {
                "Hair dryer", "Upgraded Shangri-La toiletries", "Iron & ironing board", "Separate shower & bathtub",
                "Wi-Fi and broadband Internet access", "Cable LED Television", "Full-size executive writing desk",
                "5-in-one Office equipment", "International Direct Dial telephone", "Voice mail", "Electronic safe",
                "Coffee / tea", "Mini-bar"
            }, 
            30000.00
        ));
        
        // 8. Presidential Suite
        roomList.add(new Room(
            "Presidential Suite",
            "Suite",
            "",
            "Opulent living in an elegant suite.\n" +
            "The Presidential Suite perfectly accommodates the celebrity, diplomat or a high-profile business meeting, delivering impeccable elegance, luxury and personal service.",
            "images/room8.jpg", 
            "312 sqm / 3,358 sqf",
            new String[] { 
                "Opulent living in an elegant suite.",
                "Luxury two-bedroom suite with separate sitting room, kitchenette and dining area.",
                "Separate walk-in closet",
                "In-suite butler service upon request",
                "Access to the Horizon Club Lounge with benefits including: daily breakfast, evening cocktails and use of meeting room facilities"
            },
            new String[] {
            		"Hair dryer", "Upgraded Shangri-La toiletries", "Iron & ironing board", "Separate shower & bathtub",
                    "Wi-Fi and broadband Internet access", "Cable LED Television", "Full-size executive writing desk",
                    "5-in-one Office equipment", "International Direct Dial telephone", "Voice mail", "Electronic safe",
                    "In-room fax", "Coffee / tea", "Mini-bar"
            }, 
            34500.00
        ));
        
        // 9. Two Deluxe Rooms Connecting
        roomList.add(new Room(
            "Two Deluxe Rooms Connecting",
            "Connecting Room",
            "",
            "Exquisite comfort with stellar city views.\n" +
            "Extra space and exquisite comfort define Deluxe Rooms, each fully supplied with premium Shangri-La Hotel amenities.",
            "images/room9.jpg", 
            "66-84 sqm / 710-904 sqf",
            new String[] { 
                "Exquisite comfort with stellar city views.",
                "Magnificent views of Makati and Ayala Avenues and the Glorietta Commercial Centre",
                "Located in higher floors",
                "Indulgent bed with 300-count cotton linen",
                "A spacious bathroom with separate shower"
            },
            new String[] {
                "Hair dryer", "Shangri-La toiletries", "Separate shower & bath", "300-count cotton linen",
                "Wi-Fi and broadband Internet access", "Cable LED Television", "Full-size executive writing desk", 
                "International Direct Dial telephone", "Voice mail", "Electronic safe", "Coffee / tea", "Mini-bar"
            }, 
            22500.00
        ));
    }
}