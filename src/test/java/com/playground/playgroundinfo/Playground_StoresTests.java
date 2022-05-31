package com.playground.playgroundinfo;

import com.playground.model.PlaygroundPojo;
import com.playground.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Playground_StoresTests extends TestBase {



    @Test
    public void getAllStoresInfo() {
        Response response =given()
                .when()
                .get("/stores");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSingleStoreInfo() {
        Response response =given()
                .pathParam("id",8)
                .when()
                .get("/stores/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createStore(){
        PlaygroundPojo playgroundPojo = new PlaygroundPojo();
        List<String> serviceList = new ArrayList<>();
        serviceList.add("");
        serviceList.add("");
        playgroundPojo.setName("My second name");
        playgroundPojo.setType("xyz");
        playgroundPojo.setAddress("107 my road");
        playgroundPojo.setAddress2("");
        playgroundPojo.setCity("london");
        playgroundPojo.setState("My state");
        playgroundPojo.setZip("ha3 5qj");
        playgroundPojo.setLat((long) 11.256);
        playgroundPojo.setLng((long) 25.356);
        playgroundPojo.setHours("Mon:10-9;Tue:10-9;Wed:10-9;Thurs:10-9;Fri:10-9;Sat:10-9:Sun:10-8");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(playgroundPojo)
                .when()
                .post("/stores");
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void patchStoreInformation(){
        PlaygroundPojo playgroundPojo = new PlaygroundPojo();
        playgroundPojo.setCity("Birmingham");
        playgroundPojo.setAddress("My New address");
        playgroundPojo.setAddress2("Address 2");
        playgroundPojo.setZip("bm 2 8lk");

        Response response = given()
                .pathParam("id",8924)
                .header("Content-Type", "application/json")
                .body(playgroundPojo)
                .when()
                .patch("/stores/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void deleteStoreId(){
        PlaygroundPojo playgroundPojo = new PlaygroundPojo();

        Response response  = given()
                .pathParam("id",8924)
                .header("Content-Type", "application/json")
                .body(playgroundPojo)
                .when()
                .delete("/stores/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }


}
