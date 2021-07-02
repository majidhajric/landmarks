package dev.demo.landmarks.data;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Country;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.entity.Vote;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.CountryRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class DataLayerTest {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private LandmarkRepository landmarkRepository;

    private City city = null;

    @BeforeEach
    public void setUp() {
        Country country = new Country();
        country.setName("BiH");
        country = countryRepository.save(country);

        city = new City();
        city.setName("Sarajevo");
        city.setCountry(country);
        city = cityRepository.save(city);
    }

    @AfterEach
    public void tearDown() {
        cityRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    void whenNewLandmarkSave_thanCorrectAverageScore(){
        Landmark landmark = new Landmark();
        landmark.setName("Vijećnica");
        landmark.setDescription("Gradska vijećnica");
        landmark.setImportance(Importance.MEDIUM);
        landmark.setCity(city);
        landmark = landmarkRepository.save(landmark);

        assertEquals(0f, landmark.getAverageScore());
    }

    @Test
    void whenExistingLandmarkSave_thanCorrectAverageScore(){
        Landmark landmark = new Landmark();
        landmark.setName("Vijećnica");
        landmark.setDescription("Gradska vijećnica");
        landmark.setImportance(Importance.MEDIUM);
        landmark.setCity(city);
        landmark = landmarkRepository.save(landmark);

        Vote vote1 = new Vote();
        vote1.setScore(2);

        Vote vote2 = new Vote();
        vote2.setScore(4);

        landmark.addVote(vote1);
        landmark.addVote(vote2);

        landmark = landmarkRepository.save(landmark);
        assertEquals(3, landmark.getAverageScore());
    }
}
