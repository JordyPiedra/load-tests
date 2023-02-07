package es.codeurjc.mca.practica_1_pruebas_ordinaria.ticket;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.mca.practica_1_pruebas_ordinaria.event.Event;
import es.codeurjc.mca.practica_1_pruebas_ordinaria.event.EventService;
import es.codeurjc.mca.practica_1_pruebas_ordinaria.user.User;
import es.codeurjc.mca.practica_1_pruebas_ordinaria.user.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    private final ReentrantLock lock = new ReentrantLock();


     public Ticket createTicket(Long eventId) {
        lock.lock();
        try {
            User user = userService.getMe();

            // Check for remaining tickets
            Event event = eventService.bookTicket(eventId);

            Ticket ticket = new Ticket(user, event);
            ticketRepository.save(ticket);
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public Optional<Ticket> getTicket(long id) {
        return ticketRepository.findById(id);
    }

    public void deleteTicket(Ticket ticket) {
        eventService.releaseTicket(ticket.getEvent().getId());
        ticketRepository.delete(ticket);
    }

    public boolean belongsToMe(Ticket ticket) {
        long id = userService.getMe().getId();
        return userService.getIfIAdmin() || ticket.getCustomer().getId().equals(id);
    }

}
