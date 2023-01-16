package br.com.compassuol.election.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.election.entities.VoteEntity;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
	public Optional<VoteEntity> findByIdentity(Long identity);
	public List<VoteEntity> findBySessionIdentity(Long sessionIdentity);
}