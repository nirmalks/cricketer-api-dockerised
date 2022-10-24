//package com.example.cricketer
//
//import com.example.cricketer.domain.Cricketer
//import com.example.cricketer.domain.CricketerRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//
//@Component
//class DataInitializer: CommandLineRunner {
//    @Autowired
//    lateinit var cricketerRepository: CricketerRepository
//
//    override fun run(vararg args: String?) {
//        cricketerRepository.save(Cricketer(null, "Sachin", "Ind",100))
//        cricketerRepository.save(Cricketer(null, "Sehwag", "Ind",200))
//        cricketerRepository.save(Cricketer(null, "Dhoni", "Ind",110))
//        cricketerRepository.save(Cricketer(null, "Kohli", "Ind",140))
//        cricketerRepository.save(Cricketer(null, "Ponting", "Aus",140))
//        cricketerRepository.save(Cricketer(null, "Warner", "Aus",150))
//        cricketerRepository.save(Cricketer(null, "Watson", "Aus",180))
//        cricketerRepository.save(Cricketer(null, "Butler", "Eng",154))
//        cricketerRepository.save(Cricketer(null, "Stokes", "Eng",170))
//        cricketerRepository.save(Cricketer(null, "Anderson", "Eng",18))
//    }
//}