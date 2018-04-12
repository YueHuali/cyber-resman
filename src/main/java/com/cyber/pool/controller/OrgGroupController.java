package com.cyber.pool.controller;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.OrgGroup;
import com.cyber.pool.service.NodeService;
import com.cyber.pool.service.OrgGroupService;
import com.cyber.pool.service.OrganizationService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orgGroup")
public class OrgGroupController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private OrgGroupService orgGroupService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/getOrgGroupById/{id}")
    public OrgGroup getOrgGroupById(@PathVariable Long id) {
        return orgGroupService.getOrgGroupById(id);
    }

    @GetMapping(value = "/getAllOrgGroup")
    public List<OrgGroup> getAllOrgGroup() {
        return orgGroupService.retrieveOrgGroup();
    }

    @PostMapping
    public void addOrgGroup(@RequestBody ObjectNode param) {
        OrgGroup orgGroup = new OrgGroup();
        orgGroup.setName(param.path("name").asText());
        orgGroupService.createOrgGroup(orgGroup);
    }

    @PostMapping(value = "/updateOrgGroup")
    public void updateOrgGroup(@RequestBody ObjectNode param) {
        OrgGroup orgGroup = orgGroupService.getOrgGroupById(Long.valueOf(param.path("id").asText()));
        orgGroup.setName(param.path("name").asText());
        orgGroupService.updateOrgGroup(orgGroup);
    }

    @DeleteMapping("/deleteOrgGroup/{id}")
    public void removeOrgGroup(@PathVariable("id") Long id){
        orgGroupService.deleteOrgGroup(id);
    }

    @GetMapping(value = "/addNodeToOrgGroup/{orgGroupId}/{nodeId}")
    public void addNodeToOrgGroup(@PathVariable Long orgGroupId, @PathVariable Long nodeId) {
        Node node = nodeService.getNodeById(nodeId);
        OrgGroup orgGroup = orgGroupService.getOrgGroupById(orgGroupId);
        orgGroupService.addNodeToOrgGroup(node, orgGroup);
    }

    @DeleteMapping(value = "/removeNodeFromOrgGroup/{orgGroupId}/{nodeId}")
    public void removeNodeFromOrgGroup(@PathVariable Long orgGroupId, @PathVariable Long nodeId) {
        Node node = nodeService.getNodeById(nodeId);
        OrgGroup orgGroup = orgGroupService.getOrgGroupById(orgGroupId);
        orgGroupService.removeNodeFromOrgGroup(node, orgGroup);
    }


}