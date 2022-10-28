package com.example.cricketer.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface CricketerRepository: PagingAndSortingRepository<Cricketer, Long> {
    @Query("select new com.example.cricketer.domain.CricketerDTO(c.id, c.name, c.country, c.highestScore) from Cricketer c")
    fun findCricketers(pageable: Pageable): Page<CricketerDTO>

    @Query("""
        select new com.example.cricketer.domain.CricketerDTO(c.id, c.name, c.country, c.highestScore) from Cricketer c
        where lower(c.name) like lower(concat('%', :query, '%'))
    """)
    fun searchCricketers(query: String, pageable: Pageable): Page<CricketerDTO>

    fun findByNameContainsIgnoreCase(query: String, pageable: Pageable): Page<CricketerDTO>
}