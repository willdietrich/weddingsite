package us.annandwill.weddingsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeddingController {

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
}
