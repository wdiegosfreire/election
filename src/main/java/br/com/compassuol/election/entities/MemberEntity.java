package br.com.compassuol.election.entities;

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
@Table(name = "mem_member")
@EqualsAndHashCode(callSuper = false, of = {"identity"})
public class MemberEntity extends BaseEntity {
	@Id
	@Column(name = "mem_identity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;

	@Column(name = "mem_name", nullable = false, length = 100)
	private String name;

	@Column(name = "mem_cpf", nullable = false, length = 11)
	private String cpf;
}