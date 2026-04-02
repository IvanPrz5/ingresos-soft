package com.ingresos_soft.Universal.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Models.RolesModel;
import com.ingresos_soft.Universal.Repositories.RolesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    public RolesModel get(Long id) {
        return rolesRepository.findById(id).get();
    }

    public RolesModel findFirst() {
        try {
            return rolesRepository.findFirstByOrderByIdAsc().get();
        } catch (Exception e) {
            log.error("Service: RolesService", e);
            throw new NoSuchElementException(e);
        }
    }

    public RolesModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: RolesService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<RolesModel> findAll() {
        try {
            return rolesRepository.findByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: MenusService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public RolesModel saveOrUpdate(RolesModel request) {
        try {
            RolesModel rolesInstance;
            if (request.getId() != null) {
                rolesInstance = this.get(request.getId());
                rolesInstance.setStatus(request.getStatus());
            } else {
                rolesInstance = new RolesModel();
                rolesInstance.setStatus(true);
            }
            rolesInstance.setDescripcion(request.getDescripcion());
            return rolesRepository.save(rolesInstance);
        } catch (Exception e) {
            log.error("Service: RolesService, Method: save, Error : ", e);
            throw new IllegalArgumentException(e);
        }
    }
}