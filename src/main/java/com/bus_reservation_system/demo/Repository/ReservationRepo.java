package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    public List<Reservation> findByReservationDate(LocalDate date);

}
