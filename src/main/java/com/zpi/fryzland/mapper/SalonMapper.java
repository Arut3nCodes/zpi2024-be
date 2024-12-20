package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.SalonDTO;
import com.zpi.fryzland.model.SalonModel;
import org.springframework.stereotype.Component;

@Component
public class SalonMapper implements Mapper<SalonModel, SalonDTO>{

    @Override
    public SalonModel toModel(SalonDTO dto, boolean withId) {
        return new SalonModel(
                withId ? dto.getSalonID() : null,
                dto.getSalonName(),
                dto.getSalonDialNumber(),
                dto.getSalonCity(),
                dto.getSalonStreet(),
                dto.getSalonBuildingNumber(),
                dto.getSalonPostalCode(),
                dto.getLatitude(),
                dto.getLongitude()
        );
    }

    @Override
    public SalonDTO toDTO(SalonModel model) {
        return new SalonDTO(
                model.getSalonID(),
                model.getSalonName(),
                model.getSalonDialNumber(),
                model.getSalonCity(),
                model.getSalonStreet(),
                model.getSalonBuildingNumber(),
                model.getSalonPostalCode(),
                model.getLatitude(),
                model.getLongitude()
        );
    }
}
