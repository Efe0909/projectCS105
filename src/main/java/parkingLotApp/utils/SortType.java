package main.java.parkingLotApp.utils;

public enum SortType {
    BySpot(0),
    ByPlate(1),
    ByType(2);

    private final int index;
    SortType(int index) {
        this.index = index;
    }

    public static SortType fromIndex(int selectedIndex) {
        for (SortType type : SortType.values()) {
            if (type.index == selectedIndex) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid sort type: " + selectedIndex);
    }
}
