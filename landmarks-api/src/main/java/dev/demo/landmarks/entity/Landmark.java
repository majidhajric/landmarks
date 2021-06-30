package dev.demo.landmarks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    private Float latitude;

    private Float longitude;

    private Boolean active = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    private Importance importance;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "landmark_id"))
    @Column(name = "name")
    private Set<String> fileNames;

    @OneToMany(
            mappedBy = "landmark",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name="city_id")
    private City city;

    public Double getAverageScore() {
        return votes.stream().mapToInt(Vote::getScore).average().orElse(0.0);
    }

    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setLandmark(this);
    }

}
