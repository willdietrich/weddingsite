package us.annandwill.weddingsite;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RsvpGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private RsvpInvite rsvpInvite;

    private String name;

    private Boolean attending;

    public RsvpGuest() {
        this.attending = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RsvpInvite getRsvpInvite() {
        return rsvpInvite;
    }

    public void setRsvpInvite(RsvpInvite rsvpInvite) {
        this.rsvpInvite = rsvpInvite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAttending() {
        return attending;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }
}
