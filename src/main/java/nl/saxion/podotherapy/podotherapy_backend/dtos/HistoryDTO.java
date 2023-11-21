package nl.saxion.podotherapy.podotherapy_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import nl.saxion.podotherapy.podotherapy_backend.entities.History;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDTO {
    @Schema(description = "The id of the history.", example = "43")
    private Long id;

    @Schema(description = "List of day IDs (id's are random because all days for all users are stored in one table)", example = "[\"435\", \"657\"]")
    private List<String> dayIds;


    /**
     * Constructs a new HistoryDTO object based on the provided History object.
     *
     * @param history the History object to construct the HistoryDTO from
     */
    public HistoryDTO(History history) {
        super();
        this.id = history.getId();
        this.dayIds = history.getDays().stream().map(Day::getUuid).toList();
    }
}

