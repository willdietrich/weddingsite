package us.annandwill.weddingsite;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RsvpInviteService {

    RsvpInviteRepository inviteRepo;
    RsvpGuestService guestService;

    public RsvpInviteService(
            RsvpInviteRepository inviteRepo,
            RsvpGuestService guestService
    ) {
        this.inviteRepo = inviteRepo;
        this.guestService = guestService;
    }

    public Optional<RsvpInvite> findByCode(Integer code) {
        return this.inviteRepo.findByCode(code);
    }

    public void saveInvite(RsvpInvite invite) {
        LocalDateTime now = LocalDateTime.now();

        if(invite.getRedeemedOn() == null) {
            invite.setRedeemedOn(now);
        }

        invite.setUpdatedOn(now);

        this.inviteRepo.save(invite);
    }

    public RsvpInvite fillGuests(RsvpInvite invite) {
        List<RsvpGuest> inviteGuests = invite.getGuests();
        int neededGuests = invite.getInviteCount() - inviteGuests.size();

        List<RsvpGuest> emptyGuests = this.guestService.createEmptyGuests(neededGuests);
        inviteGuests.addAll(emptyGuests);
        emptyGuests.forEach((RsvpGuest guest) -> guest.setRsvpInvite(invite));

        this.saveInvite(invite);

        return invite;
    }
}
