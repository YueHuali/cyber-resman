package com.cyber.pool.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Organization  implements java.io.Serializable {

    private Long id;
    private String name;
    private String remark;
    private Set<Node> nodes = new HashSet<Node>();
    private Set<NodeGroup> nodeGroups = new HashSet<NodeGroup>();
    private Set<OrgGroup> orgGroups = new HashSet<OrgGroup>(0);

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name", length=200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="remark", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organizations")
    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organizations")
    public Set<NodeGroup> getNodeGroups() {
        return nodeGroups;
    }

    public void setNodeGroups(Set<NodeGroup> nodeGroups) {
        this.nodeGroups = nodeGroups;
    }

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<OrgGroup> getOrgGroups() {
        return orgGroups;
    }

    public void setOrgGroups(Set<OrgGroup> orgGroups) {
        this.orgGroups = orgGroups;
    }
}