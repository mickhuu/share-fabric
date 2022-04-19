package cn.edu.gxmzu.ai.mickhu.mapper;

import cn.edu.gxmzu.ai.mickhu.entity.DataURL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-10 10:46
 */
@Mapper
public interface DataURlMapper {
    int addDataToCloud(@Param("dataURL")DataURL dataURL);

    DataURL selectByDataID(String DataID);
}
