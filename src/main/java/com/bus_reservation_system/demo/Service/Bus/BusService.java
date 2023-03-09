package com.bus_reservation_system.demo.Service.Bus;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Bus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BusService {


    public Bus addBus(Bus bus) throws BusException, LoginException;
    public Bus updateBus(Bus bus) throws BusException, LoginException;
    public BusDTO viewBus(Integer busId) throws BusException, LoginException;

    public Bus deleteBus(Integer busId) throws BusException , LoginException;

    public List<BusDTO> viewBusByType(String busType) throws BusException , LoginException;

    public List<BusDTO> viewAllBus() throws BusException, LoginException;

    public List<BusDTO> viewBusesByRoute(String startRoute, String endRoute) throws BusException, RouteException, LoginException;

    public Bus authenticateBus(Integer busId)throws BusException;

}
