package sporteventmanagement.repository;

import sporteventmanagement.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("FROM ticket WHERE " +
            "lower(customerName)  like %:param%")
    List<Ticket> searchTicketByParam(String param);
}
