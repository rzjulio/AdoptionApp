package org.expeditors.mexicoapps.adoptionapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TypePet type;
    private String breed;

    @ManyToOne
    @JsonIgnore
    private Adopter adopter;
    private Pet(int petId, String name, TypePet type, String breed) {
        this.id = petId;
        this.name = name;
        this.type = type;
        this.breed = breed;
    }

    public Pet(String name, TypePet type, String breed){
        this(0,name,type,breed);
    }

    public Pet() {
    }

    @Override
    public String toString() {
        return STR."Pet{id=\{id}, name='\{name}\{'\''}, type=\{type}, breed='\{breed}\{'\''}, adopter=\{adopter}\{'}'}";
    }
}
