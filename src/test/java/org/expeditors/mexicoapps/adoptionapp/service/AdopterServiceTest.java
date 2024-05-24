package org.expeditors.mexicoapps.adoptionapp.service;

import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class AdopterServiceTest {

    @Autowired
    private AdopterService adopterService;

    @Test
    public void testAdopterServiceGetAllAdopters(){
        List<Adopter> adopters = adopterService.getAllAdopters();

        assertNotNull(adopters);
    }

    @Test
    public void testAdopterServiceGetAdopter(){
        Adopter adopter = adopterService.getAdopter(1);

        assertNotNull(adopter);
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
    public void testAdopterServiceDeleteNonExistAdopter(){
        boolean deleted = adopterService.deleteAdopter(0);
        assertFalse(deleted);
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

}
