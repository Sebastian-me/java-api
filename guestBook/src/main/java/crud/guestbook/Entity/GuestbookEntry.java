package crud.guestbook.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Entry")
public class GuestbookEntry {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String comment;
    private String commenter;
    private Date date = new Date();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
