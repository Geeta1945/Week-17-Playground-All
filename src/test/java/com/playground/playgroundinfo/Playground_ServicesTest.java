package com.playground.playgroundinfo;

import com.playground.model.Playground_ServicesPojo;
import com.playground.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Playground_ServicesTest extends TestBase {



    @Test
    public void getAllServicesInfo(){

        Response response = given()
                .when()
                .get("/services");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleInfo(){

        Response response = given()
                .pathParam("id",10)
                .when()
                .get("/services/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    // id -22,23 created
    @Test
    public void createServices(){
        Playground_ServicesPojo playground_servicesPojo = new Playground_ServicesPojo();
        playground_servicesPojo.setName("My Apple Mobile-2");

        Response response =given()
                .header("Content-Type", "application/json")
                .body(playground_servicesPojo)
                .when()
                .post("/services");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void patchServices(){
        Playground_ServicesPojo playground_servicesPojo = new Playground_ServicesPojo();
        playground_servicesPojo.setName("apple mobile - patch");

        Response response = given()
                .pathParam("id",22)
                .header("Content-Type", "application/json")
                .body(playground_servicesPojo)
                .when()
                .patch("/services/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void deleteServices(){

        Playground_ServicesPojo playground_servicesPojo = new Playground_ServicesPojo();
        Response response = given()
                .pathParam("id",24)
                .header("Content-Type", "application/json")
                .body(playground_servicesPojo)
                .when()
                .delete("/services/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }


}
