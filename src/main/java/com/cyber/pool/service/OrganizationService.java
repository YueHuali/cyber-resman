package com.cyber.pool.service;

import com.cyber.pool.entities.Organization;

import java.util.List;

public interface OrganizationService
{
    void createOrganization(Organization organization);
    List<Organization> retrieveOrganization();
    void updateOrganization(Organization organization);
    void deleteOrganization(Long id);
    Organization getOrganizationById(Long id);
}