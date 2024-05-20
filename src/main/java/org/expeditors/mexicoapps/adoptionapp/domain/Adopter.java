package org.expeditors.mexicoapps.adoptionapp.domain;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adopter")
    private Set<Pet> pets = new HashSet<>();

    public Adopter(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.date = LocalDate.now();
    }

    public Adopter() {
        this("Unknown","232-332-223");
    }

    @Override
    public String toString() {
        return STR."Adopter{id=\{id}, name='\{name}\{'\''}, phone='\{phone}\{'\''}, date=\{date}\{'}'}";
    }

    public static Adopter getFakeAdopter(){
        Faker faker = new Faker();
        return new Adopter(faker.name().fullName(),faker.phoneNumber().phoneNumber());
    }

}
