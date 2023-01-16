package br.com.compassuol.election.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.compassuol.common.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sub_subject")
@EqualsAndHashCode(callSuper = false, of = {"identity"})
public class SubjectEntity extends BaseEntity {
	@Id
	@Column(name = "sub_identity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;

	@Column(name = "sub_title", nullable = false, length = 500)
	private String title;

	@Column(name = "sub_description", length = 500)
	private String description;

	@Column(name = "sub_end_date", length = 200)
	private Date endDate;
}