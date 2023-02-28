import config.TestConfiguration
import org.testng.annotations.Test
import steps.PetStoreSteps

class SimpleTest : TestConfiguration() {
    private val petStoreSteps = PetStoreSteps()

    @Test
    fun `Create a new pet in the store and verify the response`() {
        val petRequest = petStoreSteps.preparePetRequest(petName = "Rex", petCategoryName = "Dog")
        val petId = petStoreSteps.createNewPet(petRequest).id
        petStoreSteps.waitUntilPetIsCreated(petId, petRequest.name)
        petStoreSteps.verifyPetCategory(petId, petRequest.category.name)

        petStoreSteps.deletePetById(petId)
        petStoreSteps.waitUntilThePetIsRemovedFromTheStore(petId)

        val newPetRequest = petStoreSteps.preparePetRequest(petId, "Kimba", "Cat")
        val petId2 = petStoreSteps.createNewPet(newPetRequest).id
        petStoreSteps.waitUntilPetIsCreated(petId2, newPetRequest.name)
        petStoreSteps.verifyPetCategory(petId2, newPetRequest.category.name)
    }
}