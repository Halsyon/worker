package com.customer.domain.department.model

import org.hibernate.proxy.HibernateProxy
import javax.persistence.*

@Entity
@Table(name = "department", schema = "worker")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private var id: Long? = null,

    @Column(nullable = false)
    private var shortName: String? = null,

    @Column(nullable = false)
    private var ceo: String? = null,

    @Column(nullable = false)
    private var address: String? = null
) {
//    fun getId(): Long? {
//        return id
//    }
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


    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Department

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   shortName = $shortName   ,   ceo = $ceo   ,   address = $address )"
    }
}