package com.fbs.admin.repository;

import com.fbs.admin.model.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {

}
