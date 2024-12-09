package com.zpi.fryzland.mapper;

import java.util.List;

/**
 *
 * @param <T> Model type
 * @param <U> DTO type
 */
public interface Mapper<T, U> {

    T toModel(U dto, boolean withId);
    default U toDTO(T model){
        throw new UnsupportedOperationException();
    }

    default List<T> allToModel(List<U> dtoList, boolean withId){
        return dtoList.stream()
                .map(dto -> toModel(dto, withId))
                .distinct()
                .toList();
    };

    default List<U> allToDTO(List<T> modelList){
        return modelList.stream().
                map(model -> toDTO(model))
                .distinct()
                .toList();
    }
}
