package com.example.cricketer.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface CricketerRepository: PagingAndSortingRepository<Cricketer, Long> {
    @Query("select new com.example.cricketer.domain.CricketerDTO(c.id, c.name, c.country, c.highestScore) from Cricketer c")
    fun findCricketers(pageable: Pageable): Page<CricketerDTO>
}