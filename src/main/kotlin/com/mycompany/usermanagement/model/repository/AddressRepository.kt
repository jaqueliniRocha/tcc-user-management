package com.mycompany.usermanagement.model.repository

import com.mycompany.usermanagement.model.Address
import com.mycompany.usermanagement.model.User
import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository<Address, Long> {
}