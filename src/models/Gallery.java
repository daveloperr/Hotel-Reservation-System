package models;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    public static class GalleryItem {
        private final String imagePath;
        private final String description;

        public GalleryItem(String imagePath, String description) {
            this.imagePath = imagePath;
            this.description = description;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getDescription() {
            return description;
        }
    }

    // Static list of all gallery items
    public static List<GalleryItem> items = new ArrayList<>();

    static {
        // Only first 9 static images
        items.add(new GalleryItem("images/mainlobby.png", "Main Lobby"));
        items.add(new GalleryItem("images/horizonclublounge.jpg", "Horizon Club Lounge"));
        items.add(new GalleryItem("images/deluxsuiteante.jpg", "Deluxe Suite Ante Room"));
        items.add(new GalleryItem("images/deluxesuite.jpg", "Deluxe Suite"));
        items.add(new GalleryItem("images/deluxeroom.png", "Deluxe Room"));
        items.add(new GalleryItem("images/presidential.jpg", "Presidential Suite Living Room"));
        items.add(new GalleryItem("images/sagegrill.jpg", "Sage Grill"));
        items.add(new GalleryItem("images/shangpalace.jpeg", "Shang Palace"));
        items.add(new GalleryItem("images/lobbylounge.jpg", "Lobby Lounge"));
    }
}
