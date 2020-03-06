package main.service;

import main.entites.user.Geo;
import main.entites.user.User;

import java.util.List;

public class DivideAndConquerAlgorithm {

    private List<User> users;

    public DivideAndConquerAlgorithm(List<User> users){
        this.users = users;
    }
    public User findTheClosest(User user){
        double max = Double.MAX_VALUE;
        User result = new User();
        for(User arrayUser : users){
            if(user.equals(arrayUser)){
                continue;
            }
            double distanceBetweenUsers = calculateDistance(user.getAddress().getGeo(), arrayUser.getAddress().getGeo());
            if(distanceBetweenUsers < max){
                max = distanceBetweenUsers;
                result = arrayUser;
            }
        }
        return result;
    }
    private double calculateDistance(Geo geo1, Geo geo2){
        return Math.sqrt(Math.pow(Double.parseDouble(geo1.getLat()) - Double.parseDouble(geo2.getLat()), 2) -
                Math.pow(Double.parseDouble(geo1.getLng()) - Double.parseDouble(geo2.getLng()), 2));
    }
}
