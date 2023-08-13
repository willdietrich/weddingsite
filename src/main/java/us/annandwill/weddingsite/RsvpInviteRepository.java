package us.annandwill.weddingsite;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RsvpInviteRepository extends CrudRepository<RsvpInvite, Long> {
    Optional<RsvpInvite> findByCode(Integer code);
}
