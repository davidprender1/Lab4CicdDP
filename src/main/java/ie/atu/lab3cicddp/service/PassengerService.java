package ie.atu.lab3cicddp.service;

import ie.atu.lab3cicddp.controller.errorHandling.DuplicateException;
import ie.atu.lab3cicddp.controller.errorHandling.NotFoundException;
import ie.atu.lab3cicddp.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll() {
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {   // <-- lower-d
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Passenger create(Passenger p) {
        if (findById(p.getPassengerId()).isPresent()) {  // <-- lower-d
            throw new DuplicateException("passengerId already exists");
        }
        store.add(p);
        return p;
    }

    public Passenger update(String id, Passenger patch) {
        Passenger existing = findById(id)
                .orElseThrow(() -> new NotFoundException("passenger not found: " + id));
        existing.setName(patch.getName());
        existing.setEmail(patch.getEmail());
        return existing;
    }

    public void delete(String id) {
        Passenger existing = findById(id)
                .orElseThrow(() -> new NotFoundException("passenger not found: " + id));
        store.remove(existing);
    }
}
