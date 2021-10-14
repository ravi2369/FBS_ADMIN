package com.fbs.admin.service;

import com.fbs.admin.model.BookTicket;
import com.fbs.admin.model.dto.BookTicketDTO;

public interface BookTicketService {
    BookTicket bookTicket(String flightNumber, BookTicketDTO bookTicketDTO);
}
