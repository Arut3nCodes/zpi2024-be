package com.zpi.fryzland.mapper;

/**
 * This interface is made to unify and simplify Model - DTO mapper implementation
 * The interface usage is as it goes
 * ... implements Mapper< T, U>
 * Where:
 * T - Model Type of certain entity (e.g. CustomerModel)
 * U - DTO Type of the same entity  (e.g. CustomerDTO)
 */

public interface Mapper<T, U> {

    T toModel(U dto);
    U toDTO(T model);
}
