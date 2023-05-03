package com.nus.iss.enquiry.mapper;

import java.util.List;

/**
 * @author yanbintan
 */
public interface AbstractEntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dto);

    List<D> toDto(List<E> entity);
}
