package ie.atu.lab3cicddp.controller;

import ie.atu.lab3cicddp.model.Passenger;
import ie.atu.lab3cicddp.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getOne(@PathVariable String id) {
        Optional<Passenger> maybe = service.findById(id);
        return maybe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p) {
        Passenger created = service.create(p);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + created.getPassengerId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@PathVariable String id,
                                            @Valid @RequestBody Passenger patch) {
        Passenger updated = service.update(id, patch);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

