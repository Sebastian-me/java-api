package crud.guestbook.Repository;

import crud.guestbook.Entity.GuestbookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookEntryRepository extends JpaRepository<GuestbookEntry, Long> {
}
