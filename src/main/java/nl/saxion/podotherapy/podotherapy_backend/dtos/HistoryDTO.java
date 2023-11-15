package nl.saxion.podotherapy.podotherapy_backend.dtos;

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
    private Long id;
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

