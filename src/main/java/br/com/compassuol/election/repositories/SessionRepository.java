package br.com.compassuol.election.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.election.entities.SessionEntity;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
	public Optional<SessionEntity> findByIdentity(Long identity);
}