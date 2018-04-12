package com.cyber.pool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class Node {

	private Long id;
	private String name;
	private String ip;
	private String cpu;
	private String memory;
	private String disk;
	private String label;
	private Timestamp createTime;
	private String status;
	private String remark;
	private Set<NodeGroup> nodeGroups = new HashSet<NodeGroup>();
	private Set<Organization> organizations = new HashSet<Organization>();
	private Set<OrgGroup> orgGroups = new HashSet<OrgGroup>();

	public Node() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ip", length = 200)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "cpu", length = 200)
	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	@Column(name = "memory", length = 200)
	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	@Column(name = "disk", length = 200)
	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	@Column(name = "label", length = 200)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", length = 100)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "node_nodegroup",
			joinColumns = { @JoinColumn(name = "node_id") },
			inverseJoinColumns = { @JoinColumn(name = "nodegroup_id")}
	)
	public Set<NodeGroup> getNodeGroups() {
		return nodeGroups;
	}

	public void setNodeGroups(Set<NodeGroup> nodeGroups) {
		this.nodeGroups = nodeGroups;
	}

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "node_organization",
			joinColumns = { @JoinColumn(name = "node_id") },
			inverseJoinColumns = { @JoinColumn(name = "organization_id")}
	)
	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "node_orggroup",
			joinColumns = { @JoinColumn(name = "node_id") },
			inverseJoinColumns = { @JoinColumn(name = "orggroup_id")}
	)
	public Set<OrgGroup> getOrgGroups() {
		return orgGroups;
	}

	public void setOrgGroups(Set<OrgGroup> orgGroups) {
		this.orgGroups = orgGroups;
	}
}
