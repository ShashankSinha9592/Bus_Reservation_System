package com.bus_reservation_system.demo.Service.Bus;

import com.bus_reservation_system.demo.DTO.BusDTO;
import com.bus_reservation_system.demo.DTO.RouteDTO;
import com.bus_reservation_system.demo.ExceptionHandler.AdminException;
import com.bus_reservation_system.demo.ExceptionHandler.BusException;
import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.AdminCurrentSession;
import com.bus_reservation_system.demo.Models.Bus;
import com.bus_reservation_system.demo.Models.UserCurrentSession;
import com.bus_reservation_system.demo.Repository.AdminLoginRepo;
import com.bus_reservation_system.demo.Repository.BusRepo;
import com.bus_reservation_system.demo.Repository.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusServiceImpl implements BusService{

    @Autowired
    private BusRepo busRepo;

    @Autowired
    private AdminLoginRepo adminLoginRepo;

    private UserLoginRepo userLoginRepo;
    @Override
    public Bus addBus(Bus bus, String key) throws BusException, LoginException {
        Optional<AdminCurrentSession> opt = adminLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Please login first");
        }
        return busRepo.save(bus);

    }

    @Override
    public Bus updateBus(Bus bus, String key) throws BusException, LoginException {
        Optional<AdminCurrentSession> opt = adminLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Please login first");
        }
        Optional<Bus> opt2 = busRepo.findById(bus.getBusId());

        if(opt2.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+bus.getBusId());
        }
        return busRepo.save(bus);


    }

    @Override
    public BusDTO viewBus(Integer busId, String key,String check) throws BusException, LoginException {

        Optional<AdminCurrentSession> adminOpt=null;
        Optional<UserCurrentSession> userOpt=null;
        if(check.equals("admin")) {
            adminOpt = adminLoginRepo.findByKey(key);
            if(adminOpt.isEmpty()){
                throw new AdminException("Please login first");
            }
        }
        if(check.equals("user")){
            userOpt = userLoginRepo.findByKey(key);
            if(userOpt.isEmpty()){
                throw new UserException("Please login first");
            }
        }
        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }
        Bus bus = busOpt.get();

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

    @Override
    public Bus deleteBus(Integer busId, String key) throws BusException, LoginException {
        Optional<AdminCurrentSession> opt = adminLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Please login first");
        }

        Optional<Bus> busOpt = busRepo.findById(busId);

        if(busOpt.isEmpty()){
            throw new BusException("Bus does not exists with bus id : "+busId);
        }
        Bus bus = busOpt.get();

        busRepo.delete(bus);
        return bus;

    }

    @Override
    public List<BusDTO> viewBusByType(String busType, String key, String check) throws BusException, LoginException {

        Optional<AdminCurrentSession> adminOpt;
        Optional<UserCurrentSession> userOpt;
        if(check.equals("admin")) {
            adminOpt = adminLoginRepo.findByKey(key);
            if(adminOpt.isEmpty()){
                throw new AdminException("Please login first");
            }
        }
        if(check.equals("user")){
            userOpt = userLoginRepo.findByKey(key);
            if(userOpt.isEmpty()){
                throw new UserException("Please login first");
            }
        }

        List<Bus> buses = busRepo.findByBusType(busType);

        if(buses.isEmpty()){
            throw new BusException("No bus found with bus type : "+busType);
        }

        List<BusDTO> busDTOs = new ArrayList<>();
        for(Bus bus : buses){
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
            busDTOs.add(busDTO);

        }
        return busDTOs;

    }

    @Override
    public List<BusDTO> viewAllBus(String key) throws BusException, LoginException {
        Optional<AdminCurrentSession> opt = adminLoginRepo.findByKey(key);

        if(opt.isEmpty()){
            throw new LoginException("Please login first");
        }


        List<Bus> buses = busRepo.findAll();

        if(buses.isEmpty()){
            throw new BusException("No bus found");
        }

        List<BusDTO> busDTOs = new ArrayList<>();
        for(Bus bus : buses){
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
            busDTOs.add(busDTO);

        }
        return busDTOs;



    }
}
