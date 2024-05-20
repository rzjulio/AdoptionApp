package org.expeditors.mexicoapps.adoptionapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;

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

    public Pet(TypePet type) {
        this(null, type, null);
    }

    public static Pet getFakePet(){
        Faker faker = new Faker();
        TypePet typePet = rdmType();
        String breed;

        if (typePet == TypePet.Cat) {
            breed = faker.cat().breed();
        }
        else {
            breed = typePet == TypePet.Dog ? faker.dog().breed() : faker.funnyName().name();
        }

        return new Pet(faker.funnyName().name(),typePet,breed);
    }

    private static TypePet rdmType(){
        int rdm = ThreadLocalRandom.current().nextInt(1,5);
        switch (rdm){
            case 1: return TypePet.Cat;
            case 2: return TypePet.Dog;
            case 3: return TypePet.Turtle;
            case 4: return TypePet.Wolf;
            case 5: return TypePet.Dragon;
            default: return TypePet.Dog;
        }
    }
    public Pet() {
    }

    @Override
    public String toString() {
        return STR."Pet{id=\{id}, name='\{name}\{'\''}, type=\{type}, breed='\{breed}\{'\''}, adopter=\{adopter}\{'}'}";
    }
}
