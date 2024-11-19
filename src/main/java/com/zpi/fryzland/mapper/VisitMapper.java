package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.VisitDTO;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.AssignmentToSalonService;
import com.zpi.fryzland.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VisitMapper implements Mapper<VisitModel, VisitDTO> {
    private final AssignmentToSalonService assignmentService;
    private final CustomerService customerService;
    @Override
    public VisitModel toModel(VisitDTO dto, boolean withId) {
        return new VisitModel(
                withId ? dto.getVisitID() : null,
                dto.getVisitDate(),
                dto.getVisitStartDate(),
                dto.getAssigmentID() != null ? assignmentService.findAssignmentById(dto.getAssigmentID()).orElse(null) : null,
                dto.getCustomerID() != null ? customerService.findCustomerById(dto.getCustomerID()).orElse(null) : null
        );
    }

    @Override
    public VisitDTO toDTO(VisitModel model) {
        return new VisitDTO(
                model.getVisitID(),
                model.getVisitDate(),
                model.getVisitStartDate(),
                model.getAssigmentModel() != null ? model.getAssigmentModel().getAssignmentID() : null,
                model.getCustomerModel() != null ? model.getCustomerModel().getCustomerID() : null
        );
    }
}