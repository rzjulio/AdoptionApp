package org.expeditors.mexicoapps.adoptionapp.dao;

import org.expeditors.mexicoapps.adoptionapp.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDAO extends JpaRepository<Pet,Integer> {

}
