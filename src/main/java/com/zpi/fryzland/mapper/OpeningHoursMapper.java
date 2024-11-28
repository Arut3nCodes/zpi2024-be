package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.OpeningHoursDTO;
import com.zpi.fryzland.model.OpeningHoursModel;
import com.zpi.fryzland.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OpeningHoursMapper implements Mapper<OpeningHoursModel, OpeningHoursDTO> {

    private final SalonService salonService;

    @Override
    public OpeningHoursModel toModel(OpeningHoursDTO dto, boolean withId){
        return new OpeningHoursModel(
                dto.getOpeningHoursID(),
                dto.getWeekday(),
                dto.getOpeningHour(),
                dto.getClosingHour(),
                dto.getSalonID() != null ? salonService.getSalonById(dto.getSalonID()).orElse(null) : null
        );
    }

    @Override
    public OpeningHoursDTO toDTO(OpeningHoursModel model){
        return new OpeningHoursDTO(
                model.getOpeningHoursID(),
                model.getWeekday(),
                model.getOpeningHour(),
                model.getClosingHour(),
                model.getSalonModel() != null ? model.getSalonModel().getSalonID() : null
        );
    }
}
