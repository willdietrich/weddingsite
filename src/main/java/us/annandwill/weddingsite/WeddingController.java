package us.annandwill.weddingsite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeddingController {

    private RsvpInviteRepository rsvpInviteRepo;
    private Logger logger = LoggerFactory.getLogger("controller");

    WeddingController(RsvpInviteRepository rsvpInviteRepo) {
        this.rsvpInviteRepo =rsvpInviteRepo;

    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/party")
    public String partyPage() {
        return "party";
    }

    @GetMapping("/info")
    public String infoPage() {
        return "info";
    }

    @GetMapping("/rsvp")
    public String rsvpEntry() {
        for (RsvpInvite invite: this.rsvpInviteRepo.findAll()) {
            this.logger.debug(invite.getCode().toString());
        }

        return "rsvp";
    }
}
