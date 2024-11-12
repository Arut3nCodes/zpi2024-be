package com.zpi.fryzland.mapper;

/**
 * This interface is made to unify and simplify Model - DTO mapper implementation <br>
 * The interface usage is as it goes
 * <p>
 * ... implements Mapper< T, U> <br>
 * Where: <br>
 * T - Model Type of certain entity (e.g. CustomerModel) <br>
 * U - DTO Type of the same entity  (e.g. CustomerDTO) <br>
 *
 * <p/>
 */

public interface Mapper<T, U> {

    T toModel(U dto, boolean withId);
    default U toDTO(T model){
        throw new UnsupportedOperationException();
    }
}
