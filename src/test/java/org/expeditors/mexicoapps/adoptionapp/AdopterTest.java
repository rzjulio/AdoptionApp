package org.expeditors.mexicoapps.adoptionapp;

import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.expeditors.mexicoapps.adoptionapp.domain.TypePet;
import org.expeditors.mexicoapps.adoptionapp.service.AdopterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdopterTest {

    @Autowired
    private AdopterService adopterService;
    @Test
    public void testConstructor(){
        Adopter adopter1 = new Adopter();
        Adopter adopter2 = new Adopter("Julio","868-888-696");

        assertAll("Grouped assertions of constructors",
                () -> assertEquals(adopter1.getName(),"Unknown", "Adopter name should be Unknown."),
                () -> assertEquals(adopter2.getName(),"Julio", "Adopter name should be Julio")
        );
    }

    @Test
    public void testAdopterServiceInsert(){
        Adopter adopter = new Adopter("Julio","868-888-696");

        Adopter adopterInserted = adopterService.createAdopter(adopter);

        System.out.println(STR."Adopter: \{adopterInserted.getName()}");

        assertNotNull(adopterInserted);
    }

    @Test
    public void testAdopterServiceUpdateExistintAdopter(){
        Adopter adopter = new Adopter("Julio","868-888-696");

        Adopter adopterInserted = adopterService.createAdopter(adopter);

        System.out.println(STR."Old adopter: \{adopterInserted.getName()}");
        adopter.setName("Cesar Rodriguez");
        boolean isUpdate = adopterService.updateAdopter(adopter) != null;
        System.out.println(STR."New adopter: \{adopterService.getAdopter(adopter.getId()).getName()}");
        assertTrue(isUpdate);
    }

    @Test
    public void testAdopterServiceDeleteExistingAdopter(){
        Adopter adopter = new Adopter("Julio","868-888-696");

        Adopter adopterInserted = adopterService.createAdopter(adopter);

        System.out.println(STR."Adopter: \{adopterInserted.getName()}");

        assertNotNull(adopterInserted);

        boolean deleted = adopterService.deleteAdopter(adopterInserted.getId());
        assertTrue(deleted);
    }

    @Test
    public void testAdopterServiceDeleteNonExistingAdopter(){
        boolean deleted = adopterService.deleteAdopter(88);
        assertFalse(deleted);
    }


}
