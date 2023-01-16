package br.com.compassuol.election.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.compassuol.common.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "vot_vote")
@EqualsAndHashCode(callSuper = false, of = {"identity"})
public class VoteEntity extends BaseEntity {
	@Id
	@Column(name = "vot_identity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;

	@Column(name = "vot_option", nullable = false)
	private Boolean option;

	@ManyToOne
	@JoinColumn(name = "ses_identity")
	private SessionEntity session;

	@ManyToOne
	@JoinColumn(name = "mem_identity")
	private MemberEntity member;
}