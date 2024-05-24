package org.expeditors.mexicoapps.adoptionapp.service;

import org.expeditors.mexicoapps.adoptionapp.dao.AdopterDAO;
import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class AdopterService {

    private final AdopterDAO adopterDAO;

    public AdopterService(AdopterDAO adopterDAO) {
        this.adopterDAO = adopterDAO;
    }

    public Adopter createAdopter(Adopter adopter){
        return this.adopterDAO.save(adopter);
    }


    public Adopter updateAdopter(Adopter adopter){
        return adopterDAO.save(adopter);
    }

    public boolean deleteAdopter(int id){
        if(adopterDAO.existsById(id)){
            adopterDAO.deleteById(id);
            return !adopterDAO.existsById(id);
        }
        return false;
    }

    public Adopter getAdopter(int id){
        Optional<Adopter> adopter = adopterDAO.findById(id);
        return adopter.orElse(null);
    }

    public List<Adopter> getAllAdopters(){
        return adopterDAO.findAll();
    }

    public List<String> getAllAdoptersName(){
        return adopterDAO.findAll().stream().map(Adopter::getName).toList();
    }

    public List<Adopter> findBy(String value){
        return adopterDAO.findAll().stream()
                .filter(f -> predicates(f,value))
                .toList();
    }

    public boolean adoptAPet(int idAdopter, int idPet){
        return adopterDAO.adoptPet(idAdopter,idPet)>0;
    }

    private static boolean predicates(Adopter adopter, String value) {
        Predicate<Adopter> p1 = (Adopter a) -> String.valueOf(adopter.getId()).toLowerCase().equals(value);
        Predicate<Adopter> p2 = (Adopter a) -> adopter.getName().toLowerCase().contains(value);
        Predicate<Adopter> p3 = (Adopter a) -> adopter.getDate().toString().toLowerCase().equals(value);
        Predicate<Adopter> p4 = (Adopter a) -> adopter.getPhone().toLowerCase().contains(value);
        Predicate<Adopter> p5 = (Adopter a) -> String.valueOf(adopter.getId()).toLowerCase().equals(value);

        Predicate<Adopter> ptotal = (p1).or(p2).or(p3).or(p4).or(p5);
        return ptotal.test(adopter);
    }
}
