package com.cyber.pool.repositories;

import com.cyber.pool.entities.OrgGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrgGroupRepository extends CrudRepository<OrgGroup, Long>
{
    OrgGroup findOrgGroupById(@Param("id") long id);
}