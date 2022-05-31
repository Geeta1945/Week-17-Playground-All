package com.playground.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class SearchJsonPathExample {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI ="http://localhost";
        RestAssured.port =3030;
      response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    // 1) Extract the value of limit
    @Test
    public void test001() {
     int limit_no =   response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+limit_no);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the list of IDs of all products
    @Test
    public void test002() {


     List<Integer> listOfIds= response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : "+listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first product name from data by providing list index value
    @Test
    public void test003() {

        String name = response.extract().path("data[0].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : "+name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the categories list of the first data
    @Test
    public void test004() {

        //
        List<String> listOfCategories= response.extract().path("data[0].categories");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Categories list are : "+listOfCategories);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Print the size of data
    @Test
    public void test005() {

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the products Name from data
    List<String> listOfName= response.extract().path("data.name");
    @Test
    public void test006() {

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are : "+listOfName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the values for Name == Duracell - AA Batteries (8-Pack)
    @Test
    public void test007() {
       List<HashMap<String,?>> values = response.extract().path("data.findAll{it.name=='Duracell - AA Batteries (8-Pack)'}");// this one is groovy language
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Duracell - AA Batteries (8-Pack)' are: "+values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the price for product Name == Duracell - D Batteries (4-Pack)
    @Test
    public void test008() {
        //List<Integer> price = response.extract().path("data.findAll{it.name == 'Duracell - D Batteries (4-Pack)'}.price")
      List<Integer>price =  response.extract().path("data.findAll{it.name=='Duracell - D Batteries (4-Pack)'}.price");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price of product name 'Duracell - D Batteries (4-Pack)' is : "+price);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String> names = response.extract().path("data.findAll{it.price <16.99}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is less than 16.99 are: "+names);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the manufacturer of products whose name Start = Ene
    @Test
    public void test010() {

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of products whose name Start = Ene are: ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the price of products whose name end with = Vehicles
    @Test
    public void test011() {

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The prices of products whose name end with = Vehicles are: ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get the id of product whose name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test012() {
        List<Integer> ids = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of product whose name 'Energizer - N Cell E90 Batteries (2-Pack)' is : "+ids);
        System.out.println("------------------End of Test---------------------------");
    }

}
