package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.RatingDTO;
import com.zpi.fryzland.model.RatingModel;
import com.zpi.fryzland.model.VisitModel;

public class RatingMapper implements Mapper<RatingModel, RatingDTO> {

    @Override
    public RatingModel toModel(RatingDTO dto, boolean withId) {
//        return new RatingModel(
//                dto.getRatingID(),
//                dto.getRatingValue(),
//                dto.getRatingOpinion(),
//                dto.getEmployeeID(),
//                dto.getVisitID()
//        )
        throw new UnsupportedOperationException();
    }

    @Override
    public RatingDTO toDTO(RatingModel model) {
        return new RatingDTO(
                model.getRatingID(),
                model.getRatingValue(),
                model.getRatingOpinion(),
                model.getEmployeeModel().getEmployeeID(),
                model.getVisitModel().getVisitID()
        );
    }
}
