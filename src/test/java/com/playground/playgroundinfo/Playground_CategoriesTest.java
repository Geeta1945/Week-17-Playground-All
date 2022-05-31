package com.playground.playgroundinfo;

import com.playground.model.Playground_CategoriesPojo;
import com.playground.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Playground_CategoriesTest extends TestBase {

    @Test
    public void getAllCategories(){

        Response response = given()
                .when()
                .get("/categories");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getSingleService(){

        Response response =given()
                .pathParam("id","abcat0010000")
                .when()
                .get("/categories/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createCategories(){
        Playground_CategoriesPojo playground_categoriesPojo = new Playground_CategoriesPojo();
//        playground_categoriesPojo.setName("My Home theatre");
//        playground_categoriesPojo.setId("abcat00100256");
        playground_categoriesPojo.setName("My Home theatre-2");
        playground_categoriesPojo.setId("abcat00100256");

        Response response =given()
                .header("Content-Type", "application/json")
                .body(playground_categoriesPojo)
                .when()
                .post("/categories");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void patchCategories() {
        Playground_CategoriesPojo playground_categoriesPojo = new Playground_CategoriesPojo();
        playground_categoriesPojo.setName("Get your own Home Theatre");


        Response response = given()
                .pathParam("id", "abcat00100255")
                .header("Content-Type", "application/json")
                .body(playground_categoriesPojo)
                .patch("/categories/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
@Test
        public void deleteCategories(){
    Playground_CategoriesPojo playground_categoriesPojo = new Playground_CategoriesPojo();

        Response response =given()
                .pathParam("id", "abcat00100256")
                .header("Content-Type", "application/json")
                .body(playground_categoriesPojo)
                .delete("/categories/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
}


}
