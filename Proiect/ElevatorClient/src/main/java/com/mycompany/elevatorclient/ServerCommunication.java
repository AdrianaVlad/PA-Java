/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elevatorclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mappers.Account;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class ServerCommunication{
    HttpClient client = HttpClient.newHttpClient();
    public boolean checkPassword(String username,String password){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/username/"+username+"/password/"+password))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 302;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public boolean createAccount(String username,String password,String type){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/create"))
                .POST(BodyPublishers.ofString("username="+username+"&password="+password+"&type="+type))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 201;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
     public boolean createBuilding(String name){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/create"))
                .POST(BodyPublishers.ofString("name="+name))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 201;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public boolean checkName(String name){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/name/"+name))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 302;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public Account getAccountByName(String username){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/allinfo/"+username))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if(statusCode==500)
                return null;
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<>();
            map = (Map<String, Object>) gson.fromJson(response.body(), map.getClass());
            return new Account((int)(double)map.get("id"),(String) map.get("name"),(String) map.get("password"),(String) map.get("type"));
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }

    public List<String> getAllBuildingNames() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/names"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Gson gson = new Gson();
            List<String> nameList = new ArrayList<>();
            nameList = gson.fromJson(response.body(), nameList.getClass());
            return nameList;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }

    public List<String> getBuildingNamesFor(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/buildingNames/"+id))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Gson gson = new Gson();
            List<String> nameList = new ArrayList<>();
            nameList = gson.fromJson(response.body(), nameList.getClass());
            return nameList;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }
    public boolean deleteAccount(int id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/"+id))
                .DELETE()
                .build();

        try {
            HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode==200;
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean changeUsername(int id, String username){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/accounts/"+id+"/username"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .PUT(BodyPublishers.ofString("username="+username))
                .build();

        try {
            HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode==200;
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean changePassword(int id, String password){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/account/"+id+"/password"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .PUT(BodyPublishers.ofString("password="+password))
                .build();

        try {
            HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode==200;
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean deleteBuilding(String name){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/"+name))
                .DELETE()
                .build();

        try {
            HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode==200;
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean addRights(int id, String name){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/addRights"))
                .POST(BodyPublishers.ofString("id="+id+"&name="+name))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 201;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public boolean removeRights(int id, String name){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/buildings/removeRights/id/"+id+"/name/"+name))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 200;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public boolean createElevator(String status,int lowestFloor, int highestFloor, int currentFloor, String buildingName){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/create"))
                .POST(BodyPublishers.ofString("status="+status+"&lowestFloor="+lowestFloor+"&highestFloor="+highestFloor+"&currentFloor="+currentFloor+"&buildingName="+buildingName))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 201;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return true;
    }
    public boolean deleteElevator(int number,String buildingName){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/number/"+number+"/buildingName/"+buildingName))
                .DELETE()
                .build();

        try {
            HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode==200;
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean updateElevator(int column,String status,int lowestFloor, int highestFloor, int currentFloor, String buildingName){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/update"))
                .PUT(BodyPublishers.ofString("column="+column+"&status="+status+"&lowestFloor="+lowestFloor+"&highestFloor="+highestFloor+"&currentFloor="+currentFloor+"&buildingName="+buildingName))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 200;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public List<Elevator> getElevatorsFor(String buildingName){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/allFor/"+buildingName))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Gson gson = new Gson();
            List<Elevator> elevatorList = (List<Elevator>) gson.fromJson(response.body(), new TypeToken<List<Elevator>>() {}.getType());
            return elevatorList;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }
    public List<Elevator> getValidElevators(int floor,String buildingName){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/validFor/"+buildingName+"/"+floor))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Gson gson = new Gson();
            List<Elevator> elevatorList = (List<Elevator>) gson.fromJson(response.body(), new TypeToken<List<Elevator>>() {}.getType());
            return elevatorList;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }
    public Elevator getElevator(int id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/"+id))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<>();
            map = (Map<String, Object>) gson.fromJson(response.body(), map.getClass());
            return new Elevator((int)(double)map.get("id"),(int)(double)map.get("lowestFloor"),(int)(double)map.get("highestFloor"),(int)(double)map.get("currentFloor"),(String) map.get("status"));
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return null;
    }
    public boolean startMoving(int id, int floor){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/move"))
                .PUT(BodyPublishers.ofString("id="+id+"&floor="+floor))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 200;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
    public boolean brokenElevator(int id){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2434/elevators/broken"))
                .PUT(BodyPublishers.ofString("id="+id))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            return statusCode == 200;   
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return false;
    }
}
