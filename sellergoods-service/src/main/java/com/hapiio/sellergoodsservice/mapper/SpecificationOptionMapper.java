package com.hapiio.sellergoodsservice.mapper;

import com.hapiio.pojo.model.SpecificationOption;
import com.hapiio.pojo.model.SpecificationOptionExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SpecificationOptionMapper {
    int countByExample(SpecificationOptionExample example);

    int deleteByExample(SpecificationOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecificationOption record);

    int insertSelective(SpecificationOption record);

    List<SpecificationOption> selectByExample(SpecificationOptionExample example);

    SpecificationOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionExample example);

    int updateByExample(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionExample example);

    int updateByPrimaryKeySelective(SpecificationOption record);

    int updateByPrimaryKey(SpecificationOption record);

    @Select("select id as id,option_name as optionName,spec_id as specId,orders as orders from tb_specification_option where spec_id=#{specId}")
    List<SpecificationOption> findSpecificationOption (Long specId);

    @Delete("delete from tb_specification_option where spec_id=#{specId}")
    Integer deleteSpecificationOption(Long specId);
}