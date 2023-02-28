package dto

open class PetRequest(
    val id: Long = 0,
    val category: Category,
    val name: String,
    val photoUrls: List<String> = emptyList(),
    val tags: List<Tags> = emptyList(),
) {
    data class Category(
        val id: Long = 0,
        val name: String = "Test category"
    )

    data class Tags(
        val id: Long = 0,
        val name: String = "Tag one"
    )
}
