package com.project.customer.domain.department

import javax.persistence.*

@Entity
@Table(name = "department")
data class Department(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long?,

    @Column(nullable = false)
    var shortName: String?,

    @Column(nullable = false)
    var ceo: String?,

    @Column(nullable = false)
    var address: String?
) {

//    val getId: Long? get() = this.id
//    // Кастомный геттер и сеттер для id
//    fun getId(): Long? = id
//    fun setId(newId: Long?) {
//        id = newId
//    }
//
//    // Кастомный геттер и сеттер для shortName
//    fun getShortName(): String? = shortName
//    fun setShortName(newShortName: String?) {
//        shortName = newShortName
//    }
//
//    // Кастомный геттер и сеттер для ceo
//    fun getCeo(): String? = ceo
//    fun setCeo(newCeo: String?) {
//        ceo = newCeo
//    }
//
//    // Кастомный геттер и сеттер для address
//    fun getAddress(): String? = address
//    fun setAddress(newAddress: String?) {
//        address = newAddress
//    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   shortName = $shortName   ,   ceo = $ceo   ,   address = $address )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Department

        if (id != other.id) return false
        if (shortName != other.shortName) return false
        if (ceo != other.ceo) return false
        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (shortName?.hashCode() ?: 0)
        result = 31 * result + (ceo?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        return result
    }
}