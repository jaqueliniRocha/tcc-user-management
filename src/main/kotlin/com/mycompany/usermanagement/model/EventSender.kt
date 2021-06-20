package com.mycompany.usermanagement.model

interface UserEventSender{
    fun create(user: User)
    fun delete(user: User)
    fun update(user: User)
}