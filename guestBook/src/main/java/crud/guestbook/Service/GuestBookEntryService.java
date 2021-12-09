package crud.guestbook.Service;

import crud.guestbook.Entity.GuestBookEntry;
import crud.guestbook.Repository.GuestBookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookEntryService {
    @Autowired
    private GuestBookEntryRepository repository;

    public GuestBookEntry create(GuestBookEntry request) {
        return repository.save(request);
    }

    public List<GuestBookEntry> readAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public GuestBookEntry read(long id) {
        return repository.findById(id).orElseGet(repository.findById(id)::get);
    }

    public GuestBookEntry update(long id, GuestBookEntry request) {
        GuestBookEntry old = read(id);
        old.setTitle(request.getTitle());
        old.setComment(request.getComment());
        old.setCommenter(request.getCommenter());

        return repository.save(old);
    }
}
