package com.project.customer.domain.department

import javax.persistence.*

@Entity
@Table(name = "department", schema = "worker")
data class Department(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private var id: Long? = null,

    @Column(nullable = false)
    private var shortName: String? = null,

    @Column(nullable = false)
    private var ceo: String? = null,

    @Column(nullable = false)
    private var address: String? = null
) {

    val getId: Long? get() = this.id
    // Кастомный геттер и сеттер для id
    fun getId(): Long? = id
    fun setId(newId: Long?) {
        id = newId
    }

    // Кастомный геттер и сеттер для shortName
    fun getShortName(): String? = shortName
    fun setShortName(newShortName: String?) {
        shortName = newShortName
    }

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