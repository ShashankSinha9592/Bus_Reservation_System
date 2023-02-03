package com.bus_reservation_system.demo.Service.Bus;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.*;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.Route;
import com.bus_reservation_system.demo.Repository.BusRepo;
import com.bus_reservation_system.demo.Repository.RouteRepo;
import com.bus_reservation_system.demo.Service.LoginService.Admin.AdminAuthentication;
import com.bus_reservation_system.demo.Service.LoginService.User.UserAuthentication;
import com.bus_reservation_system.demo.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService{

    @Autowired
    AdminAuthentication adminAuthentication;

    @Autowired
    UserAuthentication userAuthentication;

    @Autowired
    private BusRepo busRepo;

    @Autowired
    UserService userService;

    @Autowired
    RouteRepo routeRepo;

    @Override
    public Bus addBus(Bus bus, String token) throws BusException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        return busRepo.save(bus);

    }

    @Override
    public Bus updateBus(Bus bus, String token) throws BusException, LoginException {

        AdminCurrentSession adminCurrentSession = adminAuthentication.authenticateAdminLoginSession(token);

        authenticateBus(bus.getBusId());

        return busRepo.save(bus);


    }

    @Override
    public BusDTO viewBus(Integer busId, String token,String check) throws BusException, LoginException {

        userService.checkUser(check,token);

        Bus bus = authenticateBus(busId);

        return getBusDTO(bus);


    }

    @Override
    public Bus deleteBus(Integer busId, String token) throws BusException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);

        Bus bus = authenticateBus(busId);

        busRepo.delete(bus);

        return bus;

    }

    @Override
    public List<BusDTO> viewBusByType(String busType, String token, String check) throws BusException, LoginException {

        userService.checkUser(check,token);

        List<Bus> buses = busRepo.findByBusType(busType);

        if(buses.isEmpty()){
            throw new BusException("No bus found with bus type : "+busType);
        }

        List<BusDTO> busDTOs = new ArrayList<>();

        for(Bus bus : buses){

            BusDTO busDTO = getBusDTO(bus);

            busDTOs.add(busDTO);

        }
        return busDTOs;

    }

    @Override
    public List<BusDTO> viewAllBus(String token) throws BusException, LoginException {

        adminAuthentication.authenticateAdminLoginSession(token);


        List<Bus> buses = busRepo.findAll();

        if(buses.isEmpty()){
            throw new BusException("No bus found");
        }

        List<BusDTO> busDTOs = new ArrayList<>();
        for(Bus bus : buses){
            BusDTO busDTO = getBusDTO(bus);
            busDTOs.add(busDTO);

        }
        return busDTOs;

    }

    @Override
    public List<BusDTO> viewBusesByRoute(String startRoute, String endRoute, String token , String check) throws BusException, RouteException, LoginException {

        userService.checkUser(check,token);

        Optional<Route> routeOpt = routeRepo.findByRoute(startRoute,endRoute);

        if(routeOpt.isEmpty()){
            throw new RouteException("Route does not exists from the given route detail");
        }

        Route route = routeOpt.get();

        List<Bus> buses = route.getBuses();

        if(buses.isEmpty()){
            throw new BusException("Bus not found in this route");
        }

        List<BusDTO> busDTOs = new ArrayList<>();

        for(Bus bus:buses){
            BusDTO busDTO = getBusDTO(bus);

            busDTOs.add(busDTO);
        }

        return busDTOs;

    }


    public Bus authenticateBus(Integer busId)throws BusException{

        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }

        return busOpt.get();

    }


    private BusDTO getBusDTO(Bus bus){

        BusDTO busDTO = new BusDTO();

        busDTO.setBusName(bus.getBusName());
        busDTO.setBusType(bus.getBusType());
        busDTO.setDriver(bus.getDriver());
        busDTO.setBusId(bus.getBusId());
        busDTO.setArrivalTime(bus.getArrivalTime());
        busDTO.setDepartureTime(bus.getDepartureTime());
        busDTO.setAvailableSeats(bus.getAvailableSeats());
        busDTO.setTotalSeats(bus.getTotalSeats());

        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteFrom(bus.getRoute().getRouteFrom());
        routeDTO.setRouteTo(bus.getRoute().getRouteTo());
        routeDTO.setDistance(bus.getRoute().getDistance());

        busDTO.setRouteDTO(routeDTO);

        return busDTO;

    }


}
