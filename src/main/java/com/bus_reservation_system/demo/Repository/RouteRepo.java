package com.bus_reservation_system.demo.Repository;

import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

    @Query("from Route r where r.routeFrom = :startRoute and r.routeTo = :endRoute")
    public Optional<Route> findByRoute(@Param("startRoute") String startRoute, @Param("endRoute") String endRoute)throws RouteException;

}
