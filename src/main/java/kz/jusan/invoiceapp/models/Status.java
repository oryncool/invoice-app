package kz.jusan.invoiceapp.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    DRAFT("draft"),
    PENDING("pending"),
    PAID("paid");

    private final String status;

    @Override
    public String toString() {
        return status;
    }
}
