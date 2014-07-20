package com.innovez.rest.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="innvz_project")
@SuppressWarnings("serial")
public class Project implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="currency", column=@Column(name="budget_currency")),
		@AttributeOverride(name="amount", column=@Column(name="budget_amount"))
	})
	private Money budget;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="manager_id", referencedColumnName="id")
	private Employee manager;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="innvz_project_member", 
		joinColumns={
			@JoinColumn(name="project_id", referencedColumnName="id")
		}, 
		inverseJoinColumns={
			@JoinColumn(name="employee_id", referencedColumnName="id")
		})
	private List<Employee> members = new ArrayList<Employee>();

	@Version
	@JsonIgnore
	@Column(name="version")
	private Integer version;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Money getBudget() {
		return budget;
	}
	public void setBudget(Money budget) {
		this.budget = budget;
	}
	
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getMembers() {
		return members;
	}
	public void setMembers(List<Employee> members) {
		this.members = members;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description="
				+ description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", budget=" + budget + ", manager=" + manager
				+ ", members=" + members + ", version=" + version + "]";
	}
}
