package us.annandwill.weddingsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class WeddingController {

    RsvpInviteService inviteService;

    WeddingController(
            RsvpInviteService inviteService
    ) {
        this.inviteService = inviteService;
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

    @GetMapping("/registry")
    public String registryPage() {
        return "registry";
    }

    @GetMapping("/rsvp")
    public String rsvpInvite(@RequestParam Optional<String> inviteCode, Model model) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);

        if (!inviteCode.isPresent()) {
            return "rsvp";
        } else {
            String code = inviteCode.get();
            try {
                int intValue = Integer.parseInt(code);

                Optional<RsvpInvite> inviteOption = this.inviteService.findByCode(intValue);
                if (!inviteOption.isPresent()) {
                    errors.put("inviteCode", "Unable to locate invite with entered code.");
                    model.addAttribute("enteredCode", inviteCode.get());

                    return "rsvp";
                }

                RsvpInvite invite = inviteOption.get();
                this.inviteService.fillGuests(invite);
                model.addAttribute("rsvpInvite", invite);

                return "rsvpUpdate";
            } catch (NumberFormatException e) {
                errors.put("inviteCode", "Invalid invite code entered, please try again.");

                return "rsvp";
            }
        }
    }

    // TODO Should this be audited?
    @PostMapping("/rsvp")
    public String setInvite(RsvpInvite rsvpSubmission) {
        Optional<RsvpInvite> inviteOption = this.inviteService.findByCode(rsvpSubmission.getCode());

        if (inviteOption.isPresent()) {
            RsvpInvite dbInvite = inviteOption.get();
            List<RsvpGuest> dbGuests = dbInvite.getGuests();

            for (RsvpGuest submissionGuest : rsvpSubmission.getGuests()) {
                for (RsvpGuest dbGuest: dbGuests) {
                    if (dbGuest.getId().compareTo(submissionGuest.getId()) == 0) {
                        dbGuest.setName(submissionGuest.getName());
                        dbGuest.setAttending(submissionGuest.getAttending());
                    }
                }
            }

            this.inviteService.saveInvite(dbInvite);
        }


        return "redirect:/rsvp-success";
    }

    @GetMapping("/rsvp-success")
    public String rsvpSuccess() {
        return "rsvpSuccess";
    }
}
