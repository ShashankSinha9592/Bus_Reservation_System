package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.Models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Integer> {
}
