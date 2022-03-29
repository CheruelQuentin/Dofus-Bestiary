package fr.tuto.dofusapi.Repository

import androidx.annotation.WorkerThread
import fr.tuto.dofusapi.DAO.UserDao
import fr.tuto.dofusapi.dataClass.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    var allUsers: Flow<List<User>> = userDao.getUsenameOrderByAsc()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert(user)
    }

}