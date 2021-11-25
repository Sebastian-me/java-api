package crud.guestbook.Mapper;

import crud.guestbook.Entity.GuestbookEntry;
import crud.guestbook.Request.GuestBookEntryRequest;
import crud.guestbook.Response.GuestBookEntryResponse;

public class GuestBookEntryMapper {
    public GuestbookEntry mapRequestToModel(GuestBookEntryRequest request) {
        GuestbookEntry entry = new GuestbookEntry();

        entry.setTitle(request.getTitle());
        entry.setComment(request.getComment());
        entry.setCommenter(request.getCommenter());

        return entry;
    }

    public GuestbookEntry mapRequestToModel(long id, GuestBookEntryRequest request) {
        return new GuestbookEntry();
    }

    public GuestBookEntryResponse mapModelToResponse(GuestbookEntry entry) {

        GuestBookEntryResponse response = new GuestBookEntryResponse();
        response.setId(entry.getId());
        response.setTitle(entry.getTitle());
        response.setComment(entry.getComment());
        response.setCommenter(response.getCommenter());

        return response;
    }
}
