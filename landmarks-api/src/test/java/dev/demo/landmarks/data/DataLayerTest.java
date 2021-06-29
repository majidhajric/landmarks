package dev.demo.landmarks.data;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Country;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.CountryRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class DataLayerTest {

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
    void whenNewLandmarkSave_thanCorrectScore(){
        Landmark landmark = new Landmark();
        landmark.setName("Vijećnica");
        landmark.setDescription("Gradska vijećnica");
        landmark.setImportance(Importance.MEDIUM);
        landmark.setCity(city);
        landmark = landmarkRepository.save(landmark);

        assertEquals(0f, landmark.getScore());
    }

    @Test
    void whenExistingLandmarkSave_thanCorrectScore(){
        Landmark landmark = new Landmark();
        landmark.setName("Vijećnica");
        landmark.setDescription("Gradska vijećnica");
        landmark.setImportance(Importance.MEDIUM);
        landmark.setCity(city);
        landmark.setTotalScore(10);
        landmark.setScoreCount(2);
        landmark = landmarkRepository.save(landmark);

        assertEquals(5f, landmark.getScore());
    }

    @Test
    void whenFindByNameAndImportance_thenFindCorrectLandmark() {
        Landmark landmark1 = new Landmark();
        landmark1.setName("Vijećnica");
        landmark1.setDescription("Gradska vijećnica");
        landmark1.setImportance(Importance.MEDIUM);
        landmark1.setCity(city);
        landmarkRepository.save(landmark1);

        Landmark landmark2 = new Landmark();
        landmark2.setName("Important One");
        landmark2.setDescription("Description");
        landmark2.setImportance(Importance.HIGH);
        landmark2.setCity(city);
        landmarkRepository.save(landmark2);

        Landmark landmark3 = new Landmark();
        landmark3.setName("Important Two");
        landmark3.setDescription("Description");
        landmark3.setImportance(Importance.MEDIUM);
        landmark3.setCity(city);
        landmarkRepository.save(landmark3);

        List<Landmark> landmarkList = landmarkRepository.findAllActiveByNameAndImportance("Important", Importance.HIGH);
        assertEquals(1, landmarkList.size());
        assertEquals("Important One", landmarkList.get(0).getName());
    }

    @Test
    void whenFindByNameAndImportance_thenFindActiveLandmark() {
        Landmark landmark = new Landmark();
        landmark.setName("Inactive");
        landmark.setDescription("Inactive one");
        landmark.setImportance(Importance.MEDIUM);
        landmark.setCity(city);
        landmark.setActive(false);
        landmarkRepository.save(landmark);

        List<Landmark> landmarkList = landmarkRepository.findAllActiveByNameAndImportance("Inactive", Importance.MEDIUM);
        assertEquals(0, landmarkList.size());
    }
}
