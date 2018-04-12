package com.cyber.pool.repositories;

import com.cyber.pool.entities.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<Organization, Long>
{
    Organization findOrganizationById(@Param("id") long id);
}