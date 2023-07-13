package com.sang.sangschoolback.entity;


import java.util.Objects;

@MappedSuperclass
public abstract class AbstractCodeDesignationEntity {

	@Column(name = "code", nullable = false, unique = true)
	protected String code;

	@Column(name = "designation", nullable = false)
	protected String designation;

	public AbstractCodeDesignationEntity(){

	}
	protected AbstractCodeDesignationEntity(String code, String designation) {
		this.code = code;
		this.designation = designation;
	}

	protected void mettreAJour(String designation) {
		this.designation = designation;
	}

	public String getCode() {
		return code;
	}

	public String getDesignation() {
		return designation;
	}

	protected boolean equalsOnOtherFields(AbstractEntity other) {
		return Objects.equals(getCode(), ((AbstractCodeDesignationEntity) other).getCode());
	}
}
