package dev.demo.landmarks.repository;

import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LandmarkRepository extends PagingAndSortingRepository<Landmark, UUID> {

    @Query("select l from Landmark l where l.name like %:name% and l.importance=:importance and l.active=true")
    List<Landmark> findAllActiveByNameAndImportance(@Param("name") String name, @Param("importance") Importance importance);
}
