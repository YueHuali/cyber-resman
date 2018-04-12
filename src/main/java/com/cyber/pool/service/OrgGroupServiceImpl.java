package com.cyber.pool.service;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.OrgGroup;
import com.cyber.pool.repositories.NodeRepository;
import com.cyber.pool.repositories.OrgGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class OrgGroupServiceImpl implements OrgGroupService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${bigbang.url}")
    String deployUrl;

    @Autowired
    OrgGroupRepository orgGroupRepository;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    OpenshiftService openshiftService;

    @Override
    public void createOrgGroup(OrgGroup orgGroup){
        logger.info("Create orgGroup: name=" + orgGroup.getName());
        String label = "qy-org-" + orgGroup.getOrganization().getName() + "-" + orgGroup.getName();
        orgGroup.setOrgGroupLabel(label);
        orgGroup.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orgGroupRepository.save(orgGroup);
    }

    @Override
    public List<OrgGroup> retrieveOrgGroup(){
        List<OrgGroup> list = new ArrayList<>();
        Iterable<OrgGroup> iterable = orgGroupRepository.findAll();
        iterable.forEach(list::add);
        logger.info("List all orgGroup: size=" + list.size());
        return list;
    }

    @Override
    public void updateOrgGroup(OrgGroup orgGroup){
        logger.info("Update orgGroup: name=" + orgGroup.getName());
        orgGroupRepository.save(orgGroup);
    }

    @Override
    public void deleteOrgGroup(OrgGroup orgGroup){
        logger.info("Delete orgGroup: name=" + orgGroup.getName());
        orgGroupRepository.delete(orgGroup);
    }

    @Override
    public void deleteOrgGroup(Long id){
        logger.info("Delete orgGroup: id=" + id);
        orgGroupRepository.deleteById(id);
    }

    @Override
    public OrgGroup getOrgGroupById(Long id){
        return orgGroupRepository.findOrgGroupById(id);
    }

    @Override
    public void addNodeToOrgGroup(Node node, OrgGroup orgGroup){
        node.getOrgGroups().add(orgGroup);
        nodeRepository.save(node);

        //此处需要打标签
        String ocResponse = openshiftService.updateLable(orgGroup.getOrgGroupLabel(), node.getName());
        logger.info("addNodeToOrgGroup ocResponse=" + ocResponse);
    }

    @Override
    public void removeNodeFromOrgGroup(Node node, OrgGroup orgGroup){
        node.getOrgGroups().remove(orgGroup);
        nodeRepository.save(node);

        //此处需要删除标签
        String ocResponse = openshiftService.updateLable(orgGroup.getOrgGroupLabel(), node.getName(), REMOVELABELFLAG);
        logger.info("reclaimNodeFromOrg ocResponse=" + ocResponse);
    }
}
