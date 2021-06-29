package dev.demo.landmarks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private Integer totalScore = 0;

    private Integer scoreCount = 0;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "landmark_id"))
    @Column(name = "name")
    private Set<String> fileNames;

    @ManyToOne(optional = false)
    @JoinColumn(name="city_id")
    private City city;

    @Transient
    public Float getScore() {
        if (scoreCount != 0) {
            return  (float) (totalScore / scoreCount);
        } else {
            return 0f;
        }
    }

}
