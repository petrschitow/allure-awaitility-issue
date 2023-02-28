package steps

import config.Constants.PET_STORE_URL
import dto.PetDeleteResponse
import dto.PetRequest
import dto.PetResponse
import io.qameta.allure.Step
import io.restassured.RestAssured.given
import org.awaitility.Awaitility.await
import org.testng.Assert.assertEquals

class PetStoreSteps {

    @Step("Prepare pet's request")
    fun preparePetRequest(petId: Long = 0, petName: String, petCategoryName: String): PetRequest {
        return PetRequest(
            id = petId,
            name = petName,
            category = PetRequest.Category(
                name = petCategoryName
            )
        )
    }

    @Step("Create new pet")
    fun createNewPet(petRequest: PetRequest): PetResponse {
        return given()
            .body(petRequest)
            .post("$PET_STORE_URL/pet")
            .then().statusCode(200)
            .extract().response().`as`(PetResponse::class.java)
    }

    @Step("Get pet by id = '{0}'")
    fun getPetById(petId: Long): PetResponse {
        return given()
            .get("$PET_STORE_URL/pet/$petId")
            .then().statusCode(200)
            .extract().response().`as`(PetResponse::class.java)
    }

    @Step("Get pet status code by id = '{0}'")
    fun getPetStatusCode(petId: Long): Int {
        return given()
            .get("$PET_STORE_URL/pet/$petId")
            .then().extract().statusCode()
    }

    @Step("Wait until pet is created")
    fun waitUntilPetIsCreated(petId: Long, petName: String) {
        await("Wait until pet is created").until { getPetById(petId).name == petName }
    }

    @Step("Verify that pet with id = '{0}' has category name = '{1}'")
    fun verifyPetCategory(petId: Long, expectedCategoryName: String) {
        val petStoreResponse = getPetById(petId)
        assertEquals(petStoreResponse.category.name, expectedCategoryName)
    }

    @Step("Delete pet with id = '{0}'")
    fun deletePetById(petId: Long, expectedStatusCode: Int = 200): PetDeleteResponse {
        return given()
            .delete("$PET_STORE_URL/pet/$petId")
            .then().statusCode(expectedStatusCode)
            .extract().response().`as`(PetDeleteResponse::class.java)
    }

    @Step("Wait until the pet with id = '{0}' is removed from the store")
    fun waitUntilThePetIsRemovedFromTheStore(petId: Long) {
        await("Wait until the pet with id = '$petId' is removed from the store")
            .until { getPetStatusCode(petId) == 404 }
    }

}