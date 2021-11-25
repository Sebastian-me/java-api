package crud.guestbook.Request;

import lombok.Getter;

@Getter
public class GuestBookEntryRequest {
    private String title;
    private String comment;
    private String commenter;
}
