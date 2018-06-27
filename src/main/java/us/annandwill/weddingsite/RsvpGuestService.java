package us.annandwill.weddingsite;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RsvpGuestService {

    public RsvpGuestService() { }

    public RsvpGuest createEmptyGuest() {
        return new RsvpGuest();
    }

    public List<RsvpGuest> createEmptyGuests(int count) {
        List<RsvpGuest> guests = new ArrayList<RsvpGuest>();

        for(int i = 0; i < count; i++) {
            guests.add(this.createEmptyGuest());
        }

        return guests;
    }
}
