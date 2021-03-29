package com.example.mynote.repositry

import androidx.lifecycle.LiveData
import com.example.mynote.db.UserDao
import com.example.mynote.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    fun addUser(user: User){
        userDao.addUser(user)
    }
    fun updateUser(user: User){
        userDao.updateUser(user)
    }
    fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

}