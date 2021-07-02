package dev.demo.landmarks.repository;

import dev.demo.landmarks.entity.Landmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LandmarkRepository extends PagingAndSortingRepository<Landmark, UUID>, JpaSpecificationExecutor<Landmark> {

    List<Landmark> findAll(Specification<Landmark> specification);

    Page<Landmark> findAll(Specification<Landmark> specification, Pageable pageable);
}
