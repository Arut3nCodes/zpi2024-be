package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.AssigmentToSalonDTO;
import com.zpi.fryzland.model.AssignmentToSalonModel;
import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.service.EmployeeService;
import com.zpi.fryzland.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssignmentToSalonMapper implements Mapper<AssignmentToSalonModel, AssigmentToSalonDTO> {

    private final SalonService salonService;
    private final EmployeeService employeeService;

    @Override
    public AssignmentToSalonModel toModel(AssigmentToSalonDTO dto, boolean withId){
        return new AssignmentToSalonModel(
                dto.getAssigmentID(),
                dto.getAssigmentDate(),
                dto.getSalonID() != null ? salonService.getSalonById(dto.getSalonID()).orElse(null) : null,
                dto.getEmployeeID() != null ? employeeService.getEmployeeById(dto.getEmployeeID()).orElse(null) : null
        );
    }

    @Override
    public AssigmentToSalonDTO toDTO(AssignmentToSalonModel model){
        return new AssigmentToSalonDTO(
                model.getAssignmentID(),
                model.getAssignmentDate(),
                model.getSalonModel() != null ? model.getSalonModel().getSalonID() : null,
                model.getEmployeeModel() != null ? model.getEmployeeModel().getEmployeeID() : null
        );
    }
}
