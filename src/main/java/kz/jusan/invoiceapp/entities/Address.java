package kz.jusan.invoiceapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="street")
    private String street;
    @Column(name="city")
    private String city;
    @Column(name="post_code")
    private String postCode;
    @Column(name="country")
    private String country;
}
