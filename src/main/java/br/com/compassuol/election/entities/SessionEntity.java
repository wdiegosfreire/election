package br.com.compassuol.election.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.compassuol.common.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "ses_session")
@EqualsAndHashCode(callSuper = false, of = {"identity"})
public class SessionEntity extends BaseEntity {
	@Id
	@Column(name = "ses_identity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;

	@Column(name = "ses_start_date", nullable = false)
	private String startDate;

	@Column(name = "ses_end_date")
	private String endDate;

	@Column(name = "ses_duration")
	private String duration;

	@ManyToOne
	@JoinColumn(name = "sub_identity")
	private SubjectEntity subject;

	@Transient
	private List<VoteEntity> voteList;
}