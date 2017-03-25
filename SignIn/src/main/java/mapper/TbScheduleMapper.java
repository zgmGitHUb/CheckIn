package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.TbSchedule;
import pojo.TbScheduleExample;

public interface TbScheduleMapper {
    long countByExample(TbScheduleExample example);

    int deleteByExample(TbScheduleExample example);

    int deleteByPrimaryKey(String scheduleid);

    int insert(TbSchedule record);

    int insertSelective(TbSchedule record);

    List<TbSchedule> selectByExample(TbScheduleExample example);

    TbSchedule selectByPrimaryKey(String scheduleid);

    int updateByExampleSelective(@Param("record") TbSchedule record, @Param("example") TbScheduleExample example);

    int updateByExample(@Param("record") TbSchedule record, @Param("example") TbScheduleExample example);

    int updateByPrimaryKeySelective(TbSchedule record);

    int updateByPrimaryKey(TbSchedule record);
}