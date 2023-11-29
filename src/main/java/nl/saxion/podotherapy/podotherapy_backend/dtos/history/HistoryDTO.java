package nl.saxion.podotherapy.podotherapy_backend.dtos.history;

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
    @Schema(description = "The uuid of the history.", example = "90df4570-08fb-4d0c-b2e9-7e523a1a01b3")
    private String uuid;

    @Schema(description = "List of days (id's are random because all days for all users are stored in one table)", example = "[\"435\", \"657\"]")
    private List<DayDTO> days;


    /**
     * Constructs a new HistoryDTO object based on the provided History object.
     *
     * @param history the History object to construct the HistoryDTO from
     */
    public HistoryDTO(History history, List<DayDTO> days) {
        super();
        this.uuid = history.getUuid();
        this.days = days;
    }
}

