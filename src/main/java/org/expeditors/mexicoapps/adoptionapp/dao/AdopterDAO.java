package org.expeditors.mexicoapps.adoptionapp.dao;


import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdopterDAO extends JpaRepository<Adopter,Integer> {
    @Modifying
    @Query("update Pet set adopter.id=?1 where id = ?2")
    public int adoptPet(int id, int idPet);
}
