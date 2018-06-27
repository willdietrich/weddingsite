package us.annandwill.weddingsite;

import java.util.ArrayList;
import java.util.List;

public class RsvpSubmission {
    Integer code;
    List<RsvpGuest> guests;

    public RsvpSubmission(Integer code, List<RsvpGuest> guests) {
        this.code = code;
        this.guests = guests;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<RsvpGuest> getGuests() {
        return guests;
    }

    public void setGuests(List<RsvpGuest> guests) {
        this.guests = guests;
    }
}
