package nl.saxion.podotherapy.podotherapy_backend.dtos.exercise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO {
    @Schema(description = "The id of the track.", example = "33")
    private Long id;
    // Add more fields as needed
}
