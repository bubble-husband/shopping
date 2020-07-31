package com.hapiio.fastdfsservice.mapper;

import com.hapiio.pojo.model.FastDfsFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FastDfsFileMapper {

    @Insert("insert into fastdfsfile(server,name,groupname,path,create_time,type) " +
            "values(#{server},#{name},#{groupname},#{path},#{createTime},#{type})")
    public int add(FastDfsFile file);

    @Select("select id,server,name,groupname,path,create_time as createTime,type" +
            "from fastdfsfile where id=#{id}")
    public FastDfsFile findOne(Long id);

    @Select("select id, server, name, groupname, path, create_time as createTime " +
            "from fastdfs_file where type=#{type}")
    public List<FastDfsFile> findByType(Integer type);
}
