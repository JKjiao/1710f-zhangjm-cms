package com.jk.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BaseDao<T> {

		//查询 模糊
		List<T> select(T t);
		
		//回显 查询一条数据
		T selectById(Integer id);
		
		//添加
		int insert(T t);
			
		//修改
		int update(T t);
			
		//删除
		int delete(@Param("ids")String ids);
	
}
                 