package io.turntabl.marketservice.services;

import java.util.List;
import java.util.Optional;

public interface ServiceContract<T, E> {

     List<T> getResources();

     T storeResource(T model);

     Optional<T> findResource(Long id);

     boolean deleteResource(Long id);

     T updateResource(Long id);

     E toModel(T dto);

     T toDto(E model);


}
