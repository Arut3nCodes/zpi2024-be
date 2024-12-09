package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.ServicesIncludedInTheVisitDTO;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.model.ServicesIncludedInTheVisitModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.ServiceService;
import com.zpi.fryzland.service.VisitService;
import lombok.AllArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServicesIncludedInTheVisitMapper implements Mapper<ServicesIncludedInTheVisitModel, ServicesIncludedInTheVisitDTO> {
    private final ServiceService serviceService;
    private final VisitService visitService;

    @Override
    public ServicesIncludedInTheVisitModel toModel(ServicesIncludedInTheVisitDTO dto, boolean withId) {
        Optional<ServiceModel> serviceModel = serviceService.getServiceById(dto.getServiceID());
        Optional<VisitModel> visitModel = visitService.getVisitById(dto.getVisitID());

        return new ServicesIncludedInTheVisitModel(
                withId ? dto.getServiceInVisitId() : null,
                serviceModel.orElse(null),
                visitModel.orElse(null)
        );
    }

    @Override
    public ServicesIncludedInTheVisitDTO toDTO(ServicesIncludedInTheVisitModel model) {
        return new ServicesIncludedInTheVisitDTO(
          model.getServiceInVisitId(),
          model.getServiceModel() != null ? model.getServiceModel().getServiceID() : null,
          model.getVisitModel() != null ? model.getVisitModel().getVisitID() : null
        );
    }
}
