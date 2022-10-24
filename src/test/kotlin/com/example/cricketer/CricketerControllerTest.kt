package com.example.cricketer

import com.example.cricketer.domain.Cricketer
import com.example.cricketer.domain.CricketerRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = [
        "spring.datasource.url = jdbc:tc:postgresql:14-alpine:///test"
    ]
)
class CricketerControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var cricketerRepository: CricketerRepository

    @BeforeEach
    fun setup() {
        cricketerRepository.deleteAll()
        val cricketers = listOf(
                Cricketer(null, "Sachin", "Ind",100),
Cricketer(null, "Sehwag", "Ind",200),
Cricketer(null, "Dhoni", "Ind",110),
Cricketer(null, "Kohli", "Ind",140),
Cricketer(null, "Ponting", "Aus",140),
Cricketer(null, "Warner", "Aus",150),
Cricketer(null, "Watson", "Aus",180),
Cricketer(null, "Butler", "Eng",154),
Cricketer(null, "Stokes", "Eng",170),
Cricketer(null, "Anderson", "Eng",18))
        cricketerRepository.saveAll(cricketers)
    }
    @Test
    fun shouldGetCricketers() {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cricketers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalItems").value(10))
            .andExpect(jsonPath("$.totalPages").value(2))
            .andExpect(jsonPath("$.currentPage").value(1))
            .andExpect(jsonPath("$.hasNext").value(true))
            .andExpect(jsonPath("$.hasPrevious").value(false))
            .andExpect(jsonPath("$.isFirst").value(true))
            .andExpect(jsonPath("$.isLast").value(false))
    }
}