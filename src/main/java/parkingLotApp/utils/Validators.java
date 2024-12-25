package main.java.parkingLotApp.utils;

public class Validators {
    /**
     * Validates a Turkish license plate.
     *
     * @param licensePlate The license plate to validate.
     * @return true if the plate is valid, false otherwise.
     */
    public static boolean isPlateValid(String licensePlate) {
        // Null or empty check
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            return false;
        }

        // Normalize to uppercase to ignore case sensitivity
        licensePlate = licensePlate.trim().toUpperCase();

        // Split the plate into parts
        String[] parts = licensePlate.split(" ");

        // Check if it has exactly 3 parts
        if (parts.length != 3) {
            return false;
        }

        // First part: Province code (01-81)
        String il = parts[0];
        if (!isValidIlCode(il)) {
            return false;
        }

        // Second part: Letters (1-3 uppercase letters)
        String letters = parts[1];
        if (!letters.matches("[A-Z]{1,3}")) {
            return false;
        }

        // Third part: Numbers (1-4 digits, no leading zero unless it's just "0")
        String numbers = parts[2];
        if (!numbers.matches("[1-9][0-9]{0,3}")) {
            return false;
        }

        return true; // Valid plate
    }

    // Validates the il code (01-81)
    private static boolean isValidIlCode(String code) {
        try {
            int il = Integer.parseInt(code);
            return il >= 1 && il <= 81;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
