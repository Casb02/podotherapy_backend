package nl.saxion.podotherapy.podotherapy_backend.enums;

public enum DayStatus {
    NONE("None"),
    PLANNED("Planned"),
    COMPLETED("Completed"),
    COMPLETED_LATE("Completed Late"),
    MISSED("Missed");

    private final String status;

    DayStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}