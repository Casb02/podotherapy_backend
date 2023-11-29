package nl.saxion.podotherapy.podotherapy_backend.dtos.history;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.dtos.exercise.ExerciseDTO;
import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import nl.saxion.podotherapy.podotherapy_backend.enums.DayStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DayDetailedDTO {

    @Schema(description = "The UUID of the day.", example = "c910d492-e035-4f55-be6a-f846ffb3ad09")
    private String uuid;

    @Schema(description = "The date of the day. (yyyy-MM-dd)", example = "2012-09-18")
    private Date date;

    @Schema(description = "W.I.P. Open for change!! The status of the day, ENUM (OPEN, CLOSED, DONE)", example = "OPEN")
    private DayStatus status;

    @Schema(description = "List of exercise []", example = "[\"{exercise object}\", \"{exercise object}\"]")
    private List<ExerciseDTO> exerciseIds;

    public DayDetailedDTO(Day day) {
        this.uuid = day.getUuid();
        this.date = day.getDate();
        this.status = day.getStatus();
        if (day.getExerciseIds() != null) {
//            this.exerciseIds = ExerciseDTO.convertToDTOList(day.getExerciseIds());
            this.exerciseIds = List.of(); //TODO fix upper code
        } else {
            this.exerciseIds = List.of();
        }
    }
}