package crud.guestbook.Repository;

import crud.guestbook.Entity.GuestBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookEntryRepository extends JpaRepository<GuestBookEntry, Long> {}
