package us.annandwill.weddingsite;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RsvpInvite {

    @Id
    private Long id;

    private Integer code;

    private String belongsTo;

    private Integer inviteCount;

    @OneToMany(mappedBy = "rsvpInvite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RsvpGuest> guests = new ArrayList<RsvpGuest>();

    private LocalDateTime redeemedOn;
    private LocalDateTime updatedOn;

    public RsvpInvite() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public List<RsvpGuest> getGuests() {
        return guests;
    }

    public void setGuests(List<RsvpGuest> guests) {
        this.guests = guests;
    }

    public LocalDateTime getRedeemedOn() {
        return redeemedOn;
    }

    public void setRedeemedOn(LocalDateTime redeemedOn) {
        this.redeemedOn = redeemedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
