package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.TbEmployeenotifyExample;
import pojo.TbEmployeenotifyKey;

public interface TbEmployeenotifyMapper {
    long countByExample(TbEmployeenotifyExample example);

    int deleteByExample(TbEmployeenotifyExample example);

    int deleteByPrimaryKey(TbEmployeenotifyKey key);

    int insert(TbEmployeenotifyKey record);

    int insertSelective(TbEmployeenotifyKey record);

    List<TbEmployeenotifyKey> selectByExample(TbEmployeenotifyExample example);

    int updateByExampleSelective(@Param("record") TbEmployeenotifyKey record, @Param("example") TbEmployeenotifyExample example);

    int updateByExample(@Param("record") TbEmployeenotifyKey record, @Param("example") TbEmployeenotifyExample example);
}