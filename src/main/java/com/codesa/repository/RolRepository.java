package com.codesa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codesa.model.dao.Rol;


@Repository
public interface RolRepository extends CrudRepository<Rol,Integer>{

}
