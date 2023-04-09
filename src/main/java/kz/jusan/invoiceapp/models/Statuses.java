package kz.jusan.invoiceapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statuses {
    private Status[] statuses;

    public boolean checkExistence(Status s) {
        for (Status status : this.statuses) {
            if (s.equals(status))
                return true;
        }
        return false;
    }

}
