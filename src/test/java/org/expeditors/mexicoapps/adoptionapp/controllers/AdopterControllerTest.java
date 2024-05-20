package org.expeditors.mexicoapps.adoptionapp.controllers;

import org.expeditors.mexicoapps.adoptionapp.controller.AdopterController;
import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.expeditors.mexicoapps.adoptionapp.service.AdopterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdopterControllerTest {

    @Mock
    private AdopterService adopterService;

    @InjectMocks
    private AdopterController adopterController;

    private List<Adopter> adopters = List.of(
            new Adopter("InMem: Jon Snow", "867-336-87-41"),
            new Adopter("InMem: Daenerys Targaryen", "867-745-63-56"),
            new Adopter("InMem: Arya Stark", "867-323-79-88")
    );

    @Test
    public void testGetAll() {
        Mockito.when(adopterService.getAllAdopters()).thenReturn(adopters);

        ResponseEntity<?> result = adopterController.GetAllAdopters();

        assertEquals(HttpStatus.OK, result.getStatusCode());

        Mockito.verify(adopterService).getAllAdopters();
    }
}
