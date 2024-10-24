package com.zpi.fryzland.mapper;

public interface Mapper<T, U> {

    T toModel(U dto);
    U toDTO(T entity);
}
