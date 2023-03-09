package com.bus_reservation_system.demo.Service.User;

import com.bus_reservation_system.demo.ExceptionHandler.LoginException;
import com.bus_reservation_system.demo.ExceptionHandler.UserException;
import com.bus_reservation_system.demo.Models.Authority;
import com.bus_reservation_system.demo.Models.User;
import com.bus_reservation_system.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) throws UserException {

        Optional<User> userOpt = userRepo.findByEmail(user.getEmail());

        if(userOpt.isPresent()){
            throw new UserException("User already exists with email : "+user.getEmail());
        }

        if(!validateName(user.getFirstName()) || !validateName(user.getLastName())){
            throw new UserException("Firstname or Lastname must contain only character");
        }

        if(!validatePassword(user.getPassword())){
            throw new UserException("Password must contain Integer , Symbol , Uppercase and lowercase");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Authority> authorities = user.getAuthorities();

        for(Authority auth : authorities){
            auth.setUser(user);
        }

        return userRepo.save(user);

    }

    @Override
    public User updateUser(User user, Authentication authentication) throws LoginException, UserException {

        User oldUser = validateUserByEmail(authentication.getName());

        validateUserById(user.getUserid());

        if(oldUser.getUserid()==user.getUserid()){

           if(oldUser.getEmail().equals(user.getEmail())){

               if(!validateName(user.getFirstName()) || !validateName(user.getLastName())){
                   throw new UserException("Firstname or Lastname must contain only character");
               }

               if(!validatePassword(user.getPassword())){
                   throw new UserException("Password must contain Integer , Symbol , Uppercase and lowercase");
               }

               user.setFeedbacks(oldUser.getFeedbacks());

               user.setReservations(oldUser.getReservations());

               user.setPassword(passwordEncoder.encode(user.getPassword()));

               return userRepo.save(user);

           }
           else{
               Optional<User> userOpt = userRepo.findByEmail(user.getEmail());

               if(userOpt.isPresent()){
                   throw new UserException("User already exists with email : "+user.getEmail());
               }
               else{

                   if(!validateName(user.getFirstName()) || !validateName(user.getLastName())){
                       throw new UserException("Firstname or Lastname must contain only character");
                   }

                   if(!validatePassword(user.getPassword())){
                       throw new UserException("Password must contain Integer , Symbol , Uppercase and lowercase");
                   }

                   user.setFeedbacks(oldUser.getFeedbacks());

                   user.setReservations(oldUser.getReservations());


                   user.setPassword(passwordEncoder.encode(user.getPassword()));

                   return userRepo.save(user);
               }
           }
        }
        else{
            Set<Authority> authoritySet = oldUser.getAuthorities();

            for(Authority authority : authoritySet){

                if (authority.getRole().equals("ROLE_ADMIN")){

                    if(!validateName(user.getFirstName()) || !validateName(user.getLastName())){

                        throw new UserException("Firstname or Lastname must contain only character");

                    }

                    if(!validatePassword(user.getPassword())){

                        throw new UserException("Password must contain Integer , Symbol , Uppercase and lowercase");

                    }

                    user.setFeedbacks(oldUser.getFeedbacks());

                    user.setReservations(oldUser.getReservations());


                    user.setPassword(passwordEncoder.encode(user.getPassword()));

                    return userRepo.save(user);
                }
            }

        }
        throw new UserException("User does not exists with user id : "+user.getUserid());

    }

    @Override
    public User viewUser(Integer uId, Authentication authentication) throws LoginException, UserException {

        User user = validateUserById(uId);

        User authenticatedUser = validateUserByEmail(authentication.getName());

        if(authenticatedUser.getUserid()==uId){

            return authenticatedUser;

        }
        else{
            Set<Authority> authorities = authenticatedUser.getAuthorities();

            for(Authority authority:authorities){

                if (authority.getRole().equals("ROLE_ADMIN")){

                    return user;

                }
            }
        }

        throw new UserException("Invalid User id");

    }

    @Override
    public User deleteUser(Integer uId, Authentication authentication) throws UserException, LoginException {

        User user = validateUserById(uId);

        User authenticatedUser = validateUserByEmail(authentication.getName());

        if(authenticatedUser.getUserid()==uId){

            userRepo.delete(user);

            return user;

        }

        else{
            Set<Authority> authorities = authenticatedUser.getAuthorities();

            for(Authority authority:authorities){

                if (authority.getRole().equals("ROLE_ADMIN")){

                    userRepo.delete(user);

                    return user;

                }
            }
        }

        throw new UserException("Invalid user id");

    }

    @Override
    public List<User> viewAllUser() throws UserException, LoginException {

        List<User> users = userRepo.findAll();

        if(users.isEmpty()){

            throw new UserException("No users found");

        }

        return users;

    }

    @Override
    public User validateUserByEmail(String email)throws UserException{

        return userRepo.findByEmail(email).orElseThrow(()-> new UserException("User does not exists with email : "+email));

    }

    @Override
    public User validateUserById(Integer userId)throws UserException{

        return userRepo.findById(userId).orElseThrow(()-> new UserException("User does not exists with ueser id : "+userId));

    }
    private boolean validateName(String name){

        char[] arr = name.toCharArray();

        for(int i=0;i<name.length();i++){

            if(Character.isDigit(arr[i])){

                return false;

            }
            else if(checkSymbol(arr[i])){

                return false;

            }
        }

        return true;

    }

    private boolean checkSymbol(char c){

        String str = "@#$%^&*";

        return str.contains(String.valueOf(c));

    }

    private boolean validatePassword(String pass){

        char[] arr = pass.toCharArray();

        for(int i=0;i<pass.length();i++){

            if(Character.isDigit(arr[i])){

                if(checkSpcl(arr)){

                    if(checkLower(arr)){

                        if(checkUpper(arr)){

                            return true;

                        }
                    }
                }
            }
        }

        return false;

    }

    private boolean checkSpcl(char[] arr){

        String str = "@#$%^&*";

        for(char i:arr){
            if(str.contains(String.valueOf(i))){
                return true;
            }
        }
        return false;
    }

    private boolean checkLower(char[]arr){

        for(char i:arr){
            if(Character.isLowerCase(i)){
                return true;
            }
        }
        return false;
    }
    private boolean checkUpper(char[]arr){

        for(char i:arr){
            if(Character.isUpperCase(i)){
                return true;
            }
        }
        return false;
    }
}
