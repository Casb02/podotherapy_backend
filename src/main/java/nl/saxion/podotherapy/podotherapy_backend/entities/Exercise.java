package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true, length = 36)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @ElementCollection
    @CollectionTable(name = "exercise_image_urls", joinColumns = @JoinColumn(name = "exercise_uuid"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @ManyToMany
    @JoinTable(
        name = "exercise_track",
        joinColumns = @JoinColumn(name = "exercise_uuid"),
        inverseJoinColumns = @JoinColumn(name = "track_uuid")
    )
    @ToString.Exclude
    private List<Track> tracks;

    /**
     * Adds the given image URL to the imageUrls list.
     *
     * @param imageUrl the URL of the image to be added
     * @return the URL of the added image
     */
    public String addImageUrl(String imageUrl) {
        imageUrls.add(imageUrl);
        return imageUrl;
    }

    /**
     * Removes the specified image URL from the list of image URLs.
     *
     * @param imageUrl the URL of the image to be removed
     * @return the removed image URL
     */
    public String removeImageUrl(String imageUrl) {
        imageUrls.remove(imageUrl);
        return imageUrl;
    }
}