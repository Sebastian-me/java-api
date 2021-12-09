package crud.guestbook.Controller;

import crud.guestbook.Entity.GuestBookEntry;
import crud.guestbook.Mapper.GuestBookEntryMapper;
import crud.guestbook.Repository.GuestBookEntryRepository;
import crud.guestbook.Request.GuestBookEntryRequest;
import crud.guestbook.Response.GuestBookEntryResponse;
import crud.guestbook.Service.GuestBookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/guest-book/")
public class GuestBookEntryController {
    @Autowired
    private GuestBookEntryMapper mapper;

    @Autowired
    private GuestBookEntryService service;

    @PostMapping(value = "create")
    public ResponseEntity<GuestBookEntryResponse> createGuestBookEntry(@RequestBody GuestBookEntryRequest request) {
        GuestBookEntry rEntry = mapper.mapRequestToModel(request);
        GuestBookEntry entry = service.create(rEntry);

        return new ResponseEntity<>(mapper.mapModelToResponse(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestBookEntryResponse> getGuestBookEntry(@PathVariable long id) {
        GuestBookEntry response = service.read(id);

        return new ResponseEntity<>(mapper.mapModelToResponse(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GuestBookEntryResponse>> getAllGuestBookEntries() {
        List<GuestBookEntry> list = service.readAll();

        List<GuestBookEntryResponse> responseList = new ArrayList<>();

        for(GuestBookEntry entry: list) {
            responseList.add(mapper.mapModelToResponse(entry));
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        service.delete(id);

        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestBookEntryResponse> updateBookEntry(@PathVariable long id, @RequestBody GuestBookEntryRequest request) {
        GuestBookEntry entry = service.update(id, mapper.mapRequestToModel(request));

        return new ResponseEntity<>(mapper.mapModelToResponse(entry), HttpStatus.OK);
    }
}
