package crud.guestbook.Response;

import lombok.Data;

@Data
public class GuestBookEntryResponse {
  private long id;
  private String title;
  private String comment;
  private String commenter;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
