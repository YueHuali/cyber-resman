package com.cyber.pool.controller;

import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;
import com.cyber.pool.service.NodeGroupService;
import com.cyber.pool.service.OrganizationService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nodeGroup")
public class NodeGroupController {

    @Autowired
    private NodeGroupService nodeGroupService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/getNodeGroupById/{id}")
    public NodeGroup getNodeGroupById(@PathVariable Long id) {
        return nodeGroupService.getNodeGroupById(id);
    }

    @GetMapping(value = "/getAllNodeGroup")
    public List<NodeGroup> getAllNodeGroup() {
        return nodeGroupService.retrieveNodeGroup();
    }

    @PostMapping
    public void addNodeGroup(@RequestBody ObjectNode param) {
        NodeGroup nodeGroup = new NodeGroup();
        nodeGroup.setName(param.path("name").asText());
        nodeGroupService.createNodeGroup(nodeGroup);
    }

    @PostMapping(value = "/updateNodeGroup")
    public void updateNodeGroup(@RequestBody ObjectNode param) {
        NodeGroup nodeGroup = nodeGroupService.getNodeGroupById(Long.valueOf(param.path("id").asText()));
        nodeGroup.setName(param.path("name").asText());
        nodeGroupService.updateNodeGroup(nodeGroup);
    }

    @DeleteMapping("/deleteNodeGroup/{id}")
    public void removeNodeGroup(@PathVariable("id") Long id){
        nodeGroupService.deleteNodeGroup(id);
    }

    @GetMapping(value = "/assignNodeGroupToOrg/{nodeGroupId}/{orgId}")
    public void assignNodeGroupToOrg(@PathVariable Long nodeGroupId, @PathVariable Long orgId) {
        NodeGroup nodeGroup = nodeGroupService.getNodeGroupById(nodeGroupId);
        Organization org = organizationService.getOrganizationById(orgId);

        nodeGroupService.assignNodeGroupToOrg(nodeGroup, org);
    }

    @DeleteMapping(value = "/removeNodeGroupFromOrg/{groupId}/{orgId}")
    public void removeNodeGroupFromOrg(@PathVariable Long groupId, @PathVariable Long orgId) {
        NodeGroup nodeGroup = nodeGroupService.getNodeGroupById(groupId);
        Organization org = organizationService.getOrganizationById(orgId);

        nodeGroupService.reclaimGroupFromOrg(nodeGroup, org);
    }

}