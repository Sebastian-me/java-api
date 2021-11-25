package crud.guestbook.Service;

import crud.guestbook.Entity.GuestbookEntry;
import crud.guestbook.Repository.GuestBookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookEntryService {
    @Autowired
    private GuestBookEntryRepository repository;

    public GuestbookEntry create(GuestbookEntry request) {
        return repository.save(request);
    }

    public List<GuestbookEntry> readAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public GuestbookEntry read(long id) {
        return repository.findById(id).get();
    }

    public GuestbookEntry update(GuestbookEntry request) {
        GuestbookEntry old = read(request.getId());
        old.setTitle(request.getTitle());
        old.setComment(request.getComment());
        old.setCommenter(request.getCommenter());

        return repository.save(old);
    }
}
