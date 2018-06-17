package us.annandwill.weddingsite;

import org.springframework.data.repository.CrudRepository;

public interface RsvpInviteRepository extends CrudRepository<RsvpInvite, Long> {
    RsvpInvite findByCode(Integer code);
}
