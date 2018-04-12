package com.cyber.pool.service;

import com.cyber.pool.entities.Organization;
import com.cyber.pool.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void createOrganization(Organization organization){
        logger.info("Create organization: name=" + organization.getName());
        organizationRepository.save(organization);
    }

    @Override
    public List<Organization> retrieveOrganization(){
        List<Organization> list = new ArrayList<>();
        Iterable<Organization> iterable = organizationRepository.findAll();
        iterable.forEach(list::add);
        logger.info("List all organization: size=" + list.size());
        return list;
    }

    @Override
    public void updateOrganization(Organization organization){
        logger.info("Update organization: name=" + organization.getName());
        organizationRepository.save(organization);
    }

    @Override
    public void deleteOrganization(Long id){
        logger.info("Delete organization: id=" + id);
        organizationRepository.deleteById(id);
    }

    @Override
    public Organization getOrganizationById(Long id){
        return organizationRepository.findOrganizationById(id);
    }
}