package dto

data class PetResponse(
    val id: Long,
    val category: Category,
    val name: String?,
    val photoUrls: List<String>,
    val tags: List<Tags>,
    val status: String?
) {
    data class Category(
        val id: Long,
        val name: String?
    )

    data class Tags(
        val id: Long,
        val name: String?
    )
}
