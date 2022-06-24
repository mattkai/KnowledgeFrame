package com.kdf.sysframes.db.dao

import androidx.room.*
import com.kdf.sysframes.data.CnStudent

@Dao
interface CnStuDao {

    /**
     * 增加记录
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStu(stu: CnStudent)

    /**
     * 删除记录
     */
    @Delete
    fun deleteStu(stu: CnStudent)

    @Query(value = "delete from t_student where id= :sid")
    fun deleteStuById(sid: Long)

    /**
     * 更新记录
     */
    @Update
    fun updStu(stu: CnStudent)

    @Query(value = "update t_student set age =:sage where id= :sid")
    fun updStuById(sid: Long,sage: Int)

    /**
     * 查询所有记录
     */
    @Query(value = "select * from t_student")
    fun selectAll(): MutableList<CnStudent>

    /**
     * 查询一条记录
     */
    @Query(value = "select * from t_student where id = :sid")
    fun selectStubyId(sid: Long): CnStudent

}