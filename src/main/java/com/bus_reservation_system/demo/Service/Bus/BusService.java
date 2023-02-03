package com.bus_reservation_system.demo.Service.Bus;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.RouteException;
import com.bus_reservation_system.demo.Models.Bus;

import java.util.List;

public interface BusService {


    public Bus addBus(Bus bus, String key) throws BusException, LoginException;
    public Bus updateBus(Bus bus, String key) throws BusException, LoginException;
    public BusDTO viewBus(Integer busId, String key,String check) throws BusException, LoginException;

    public Bus deleteBus(Integer busId, String key) throws BusException , LoginException;

    public List<BusDTO> viewBusByType(String busType, String key, String check) throws BusException , LoginException;

    public List<BusDTO> viewAllBus(String key) throws BusException, LoginException;

    public List<BusDTO> viewBusesByRoute(String startRoute, String endRoute, String token , String check) throws BusException, RouteException, LoginException;

    public Bus authenticateBus(Integer busId)throws BusException;

}
