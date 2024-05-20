package org.expeditors.mexicoapps.adoptionapp.dao;

import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.expeditors.mexicoapps.adoptionapp.domain.Pet;
import org.expeditors.mexicoapps.adoptionapp.domain.TypePet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdopterDAOTest {

    @Autowired
    private AdopterDAO adopterDAO;

    @Autowired
    private PetDAO petDAO;

    @Test
    public void saveAdopter(){
        Adopter adopter = new Adopter("Julio","87923934");
        Adopter newAdopter = adopterDAO.save(adopter);
        assertNotNull(newAdopter);
    }

    @Test
    public void updateAdopter(){
        Adopter adopter = new Adopter("Julio","87923934");
        adopterDAO.save(adopter);
        adopter.setName("Julio Cesar");
        Adopter newAdopter = adopterDAO.save(adopter);
        assertEquals(newAdopter.getName(),"Julio Cesar");
    }

    @Test
    public void getAdopter(){
        Adopter adopter = new Adopter("Julio","87923934");
        adopterDAO.save(adopter);

        Optional<Adopter> newAdopter = adopterDAO.findById(adopter.getId());

        assertTrue(newAdopter.isPresent());
    }

    @Test
    public void deleteAdopter(){
        Adopter adopter = new Adopter("Julio","87923934");
        Adopter newAdopter = adopterDAO.save(adopter);

        adopterDAO.deleteById(newAdopter.getId());

        boolean isDelete = adopterDAO.findById(newAdopter.getId()).isEmpty();
        assertTrue(isDelete);
    }

    @Test
    public void adoptPet(){
        Adopter adopter = new Adopter("Julio","87923934");
        Adopter newAdopter = adopterDAO.save(adopter);
        assertNotNull(newAdopter);

        Pet pet = new Pet("Coco", TypePet.Dog,"Belga");
        Pet newPet = petDAO.save(pet);
        assertNotNull(newPet);

        boolean isAdopt = adopterDAO.adoptPet(newAdopter.getId(), pet.getId())>0;

        assertTrue(isAdopt);


    }
}
