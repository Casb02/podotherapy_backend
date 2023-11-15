package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.enums.DayStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DayDTO {
    private Long id;
    private String uuid;
    private Date date;
    private DayStatus status;
    private List<String> exerciseIds;
}