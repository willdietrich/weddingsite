package us.annandwill.weddingsite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class WeddingController {

    private RsvpInviteRepository rsvpInviteRepo;
    private Logger logger = LoggerFactory.getLogger("controller");

    WeddingController(RsvpInviteRepository rsvpInviteRepo) {
        this.rsvpInviteRepo =rsvpInviteRepo;
    }

    @GetMapping("/")
    public String landingRedirect() { return "redirect:/about"; }

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
    public String rsvpInvite(@RequestParam Optional<Integer> inviteCode, Model model) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);

        if (!inviteCode.isPresent()) {
            return "rsvp";
        } else {
            Optional<RsvpInvite> invite = this.rsvpInviteRepo.findByCode(inviteCode.get());
            if (!invite.isPresent()) {
                errors.put("inviteCode", "Unable to locate invite with entered code.");
                model.addAttribute("enteredCode", inviteCode.get());

                return "rsvp";
            }

            model.addAttribute("rsvpInvite", invite);


            return "rsvpUpdate";
        }
    }
}
