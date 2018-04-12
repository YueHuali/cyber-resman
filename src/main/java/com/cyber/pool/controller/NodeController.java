package com.cyber.pool.controller;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;
import com.cyber.pool.service.NodeGroupService;
import com.cyber.pool.service.NodeService;
import com.cyber.pool.service.OrganizationService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeGroupService nodeGroupService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/getNodeById/{id}")
    public Node getNodeById(@PathVariable Long id) {
        return nodeService.getNodeById(id);
    }

    @GetMapping(value = "/getAllNode")
    public List<Node> getAllNode() {
        return nodeService.retrieveNode();
    }

    @PostMapping
    public void addNode(@RequestBody ObjectNode param) {
        Node node = new Node();
        node.setName(param.path("name").asText());
        node.setIp(param.path("ip").asText());
        node.setStatus("1");
        nodeService.createNode(node);
    }

    @PostMapping(value = "/updateNode")
    public void updateNode(@RequestBody ObjectNode param) {
        Node node = nodeService.getNodeByIp(param.path("ip").asText());
        node.setCpu(param.path("cpu").asText());
        node.setMemory(param.path("memory").asText());
        node.setDisk(param.path("disk").asText());
        nodeService.updateNode(node);
    }

    @DeleteMapping("/deleteNode/{id}")
    public void removeNode(@PathVariable("id") Long id){
        nodeService.deleteNode(id);
    }

    @GetMapping(value = "/deployNode/{nodeId}")
    public void deployNode(@PathVariable Long nodeId) {
        //Invoke big bang's rest service
        Node node = nodeService.getNodeById(nodeId);
        nodeService.deployNode(node);
    }

    @GetMapping(value = "/assignNode/{nodeId}/{orgId}")
    public void assignNodeToOrg(@PathVariable Long nodeId, @PathVariable Long orgId) {
        Node node = nodeService.getNodeById(nodeId);
        Organization org = organizationService.getOrganizationById(orgId);

        nodeService.assignNodeToOrg(node, org);
    }

    @GetMapping(value = "/reclaimNode/{nodeId}/{orgId}")
    public void reclaimNodeFromOrg(@PathVariable Long nodeId, @PathVariable Long orgId) {
        Node node = nodeService.getNodeById(nodeId);
        Organization org = organizationService.getOrganizationById(orgId);

        nodeService.reclaimNodeFromOrg(node, org);
    }

    @GetMapping(value = "/addNodeToGroup/{nodeId}/{groupId}")
    public void addNodeToGroup(@PathVariable Long nodeId, @PathVariable Long groupId) {
        Node node = nodeService.getNodeById(nodeId);
        NodeGroup nodeGroup = nodeGroupService.getNodeGroupById(groupId);
        nodeService.addNodeToGroup(node, nodeGroup);
    }

    @DeleteMapping(value = "/removeNodeFromGroup/{nodeId}/{groupId}")
    public void removeNodeFromGroup(@PathVariable Long nodeId, @PathVariable Long groupId) {
        Node node = nodeService.getNodeById(nodeId);
        NodeGroup nodeGroup = nodeGroupService.getNodeGroupById(groupId);
        nodeService.removeNodeFromGroup(node, nodeGroup);
    }

}