package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.RatingDTO;
import com.zpi.fryzland.model.RatingModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.EmployeeService;
import com.zpi.fryzland.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RatingMapper implements Mapper<RatingModel, RatingDTO> {
    VisitService visitService;
    EmployeeService employeeService;
    @Override
    public RatingModel toModel(RatingDTO dto, boolean withId) {
        return new RatingModel(
                withId ? dto.getRatingID() : null,
                dto.getRatingValue(),
                dto.getRatingOpinion(),
                employeeService.getEmployeeById(dto.getEmployeeID()).orElse(null),
                visitService.getVisitById(dto.getVisitID()).orElse(null)
        );
    }

    @Override
    public RatingDTO toDTO(RatingModel model) {
        return new RatingDTO(
                model.getRatingID(),
                model.getRatingValue(),
                model.getRatingOpinion(),
                model.getEmployeeModel() != null ? model.getEmployeeModel().getEmployeeID() : null,
                model.getVisitModel() != null ? model.getVisitModel().getVisitID() : null
        );
    }
}
