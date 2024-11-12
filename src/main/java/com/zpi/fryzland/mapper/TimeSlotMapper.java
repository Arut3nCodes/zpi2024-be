package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.TimeSlotDTO;
import com.zpi.fryzland.model.TimeSlotModel;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper implements Mapper<TimeSlotModel, TimeSlotDTO>{

    @Override
    public TimeSlotModel toModel(TimeSlotDTO dto, boolean withId) {
        throw new UnsupportedOperationException("Currently not needed");
    }

    @Override
    public TimeSlotDTO toDTO(TimeSlotModel model) {
        return new TimeSlotDTO(
                model.getTimeSlotDate(),
                model.getTimeSlotTime(),
                model.getEmployeeModel() != null ? model.getEmployeeModel().getEmployeeID() : null
        );
    }
}
