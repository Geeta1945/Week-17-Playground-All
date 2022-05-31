package com.playground.playgroundinfo;

import com.playground.model.Playground_ProductsPojo;
import com.playground.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Playground_ProductsTests extends TestBase {

    Playground_ProductsPojo playground_productsPojo = new Playground_ProductsPojo();

    @Test
    public void getAllProducts() {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void getSingleProduct() {

        Response response = given()
                .pathParam("id", 43900)
                .when()
                .get("/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createProduct() {

        playground_productsPojo.setName("Energizer-Rechargeable(AA1)");
        playground_productsPojo.setType("Extra-Strong");
        playground_productsPojo.setPrice(6);
        playground_productsPojo.setShipping(123456);

        playground_productsPojo.setUpc("03980");
        playground_productsPojo.setDescription("AA2 alkaline batteries");
        playground_productsPojo.setManufacturer("Energizer");
        playground_productsPojo.setModel("Model4");
        playground_productsPojo.setUrl("http://www.bestbuy.com/site/pioneer-4-3-way-surface-mount-speakers-with-impp-composite-cones-pair-black/309062.p?id=1218643240258&skuId=309062&cmp=RMXCC");
        playground_productsPojo.setImage("http://img.bbystatic.com/BestBuy_US/images/products/0309/0309062_sa.jpg");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(playground_productsPojo)
                .when()
                .post("/products");
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void patchProduct(){

        playground_productsPojo.setPrice(5);
        Response response = given()
                .pathParam("id",9999681)
                .header("Content-Type", "application/json")
                .body(playground_productsPojo)
                .when()
                .patch("/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteProduct(){

        Response response=given()
                .pathParam("id",9999680)
                .header("Content-Type", "application/json")
                .body(playground_productsPojo)
                .when()
                .delete("/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }


}
