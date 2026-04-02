package com.ingresos_soft.Universal.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingresos_soft.Universal.Models.ModulesModel;
import com.ingresos_soft.Universal.Repositories.ModulesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModulesService {

    @Autowired
    ModulesRepository modulesRepository;

    public ModulesModel get(Long id) {
        return modulesRepository.findById(id).orElseThrow();
    }

    public ModulesModel findFirstByOrderByIdAsc() {
        try {
            return modulesRepository.findFirstByOrderByIdAsc().orElseThrow();
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: ModuleService, Method: FindFirstByOrderByIdAsc, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public ModulesModel findById(Long id) {
        try {
            return this.get(id);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: ModulesService, Method: FindById, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public List<ModulesModel> findAll() {
        try {
            return modulesRepository.findAllByStatus(true);
        } catch (Exception e) {
            log.error("Plugin: Universal, Service: ModulesService, Method: FindAll, Error: ", e);
            throw new NoSuchElementException();
        }
    }
}