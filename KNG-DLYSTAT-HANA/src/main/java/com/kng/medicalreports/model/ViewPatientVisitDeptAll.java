package com.kng.medicalreports.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VIEW_PATIENT_VISIT_DEPT_COUNT_ALL",catalog="ECLINIC")
public class ViewPatientVisitDeptAll implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="row_num")
	private int row_num;
	
	@Column(name="DEPARTMENT_NAME")
	private String Name;
	
	
	@Column(name="PATIENT_COUNT")
	private int totalCount;
	
	@Column(name="FLAG_FILTER")
	private String flagFilter;
	


	/**
	 * 
	 */
	public ViewPatientVisitDeptAll() {
		
	}


	public int getRow_num() {
		return row_num;
	}


	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	

	public String getFlagFilter() {
		return flagFilter;
	}


	public void setFlagFilter(String flagFilter) {
		this.flagFilter = flagFilter;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Name, row_num, totalCount);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewPatientVisitDeptAll other = (ViewPatientVisitDeptAll) obj;
		return Objects.equals(Name, other.Name) && row_num == other.row_num && totalCount == other.totalCount;
	}


	@Override
	public String toString() {
		return "ViewPatientVisitDeptAll [row_num=" + row_num + ", Name=" + Name + ", totalCount=" + totalCount + "]";
	}
	
	

}
