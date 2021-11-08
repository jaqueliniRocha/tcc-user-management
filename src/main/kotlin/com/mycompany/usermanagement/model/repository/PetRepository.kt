package com.mycompany.usermanagement.model.repository

import com.mycompany.usermanagement.model.Pet
import org.springframework.data.repository.CrudRepository

interface PetRepository : CrudRepository<Pet, Long> {
}