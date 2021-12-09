package crud.guestbook.Mapper;

import crud.guestbook.Entity.GuestBookEntry;
import crud.guestbook.Request.GuestBookEntryRequest;
import crud.guestbook.Response.GuestBookEntryResponse;
import org.springframework.stereotype.Service;

@Service
public class GuestBookEntryMapper {

    public GuestBookEntry mapRequestToModel(GuestBookEntryRequest request) {
        GuestBookEntry entry = new GuestBookEntry();

        entry.setTitle(request.getTitle());
        entry.setComment(request.getComment());
        entry.setCommenter(request.getCommenter());

        return entry;
    }

    public GuestBookEntry mapRequestToModel(long id, GuestBookEntryRequest request) {

        GuestBookEntry entry = new GuestBookEntry();
        entry.setId(id);
        entry.setTitle(request.getTitle());
        entry.setComment(request.getComment());
        entry.setCommenter(request.getCommenter());

        return entry;
    }

    public GuestBookEntryResponse mapModelToResponse(GuestBookEntry entry) {

        GuestBookEntryResponse response = new GuestBookEntryResponse();
        response.setId(entry.getId());
        response.setTitle(entry.getTitle());
        response.setComment(entry.getComment());
        response.setCommenter(entry.getCommenter());

        return response;
    }
}
