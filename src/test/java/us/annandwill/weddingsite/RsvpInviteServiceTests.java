package us.annandwill.weddingsite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RsvpInviteServiceTests {

	private static final int RSVP_CODE = 12345;

	private RsvpInviteService uut;
	private RsvpInviteRepository inviteRepoMock;
	private RsvpGuestService guestServiceMock;

	@Before
	public void setUp() {

		inviteRepoMock = mock(RsvpInviteRepository.class);
		guestServiceMock = mock(RsvpGuestService.class);

		uut = new RsvpInviteService(inviteRepoMock, guestServiceMock);
	}

	@Test
	public void testFindByCode() {
		Optional<RsvpInvite> invite = Optional.of(new RsvpInvite());

		when(inviteRepoMock.findByCode(RSVP_CODE)).thenReturn(invite);

		Optional<RsvpInvite> retval = this.uut.findByCode(RSVP_CODE);

		assertThat(retval).isEqualTo(invite);
		verify(inviteRepoMock).findByCode(RSVP_CODE);
	}

	@Test
	public void testSaveInvite() {
		RsvpInvite inviteMock = mock(RsvpInvite.class);
		when(inviteMock.getRedeemedOn()).thenReturn(null);

		this.uut.saveInvite(inviteMock);

		verify(inviteMock).setRedeemedOn(any(LocalDateTime.class));
		verify(inviteMock).setUpdatedOn(any(LocalDateTime.class));
		verify(inviteRepoMock).save(inviteMock);
	}


	@Test
	public void testFillGuests_NoGuests() {
		RsvpInvite inviteMock = mock(RsvpInvite.class);
		List<RsvpGuest> emptyGuestList = new ArrayList<>();
		when(inviteMock.getGuests()).thenReturn(emptyGuestList);
		when(inviteMock.getInviteCount()).thenReturn(2);

		List<RsvpGuest> inviteGuests = new ArrayList<>();
		inviteGuests.addAll(Arrays.asList(new RsvpGuest(), new RsvpGuest()));
		when(guestServiceMock.createEmptyGuests(2))
				.thenReturn(inviteGuests);

		uut.fillGuests(inviteMock);

		verify(inviteMock).setRedeemedOn(any(LocalDateTime.class));
		verify(inviteMock).setUpdatedOn(any(LocalDateTime.class));
		verify(inviteRepoMock).save(inviteMock);

		assertThat(emptyGuestList.size()).isEqualTo(2);
	}
}
