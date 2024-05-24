package org.expeditors.mexicoapps.adoptionapp.service;


import org.expeditors.mexicoapps.adoptionapp.dao.PetDAO;
import org.expeditors.mexicoapps.adoptionapp.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetDAO petDAO;

//    public Pet createPet(Pet pet){
//        return this.petDAO.insertPet(pet);
//    }

    public boolean deletePet(int id){
        petDAO.deleteById(id);
        return petDAO.findById(id).isEmpty();
    }

//    public boolean updatePet(Pet pet){
//        return this.petDAO.updatePet(pet);
//    }

    public List<Pet> getAllPets(){
        return this.petDAO.findAll();
    }

    public Pet getPet(int id){
        Optional<Pet> pet = this.petDAO.findById(id);
        return pet.orElse(null);
    }
}
