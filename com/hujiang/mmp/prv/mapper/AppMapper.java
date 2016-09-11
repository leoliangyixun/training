package com.hujiang.mmp.prv.mapper;

import com.hujiang.mmp.model.App;
import com.hujiang.mmp.model.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    App selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    App lockByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Tue Aug 23 13:55:33 CST 2016
     */
    int updateByPrimaryKey(App record);
}