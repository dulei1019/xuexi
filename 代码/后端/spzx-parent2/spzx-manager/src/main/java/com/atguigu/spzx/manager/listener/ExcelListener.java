package com.atguigu.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

/**
 * 监听器  不能交给spring管理，因为spring管理的是单例的，而监听器是多例的
 */

public class ExcelListener<T> extends AnalysisEventListener<T> {

    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 通过构造传递mapper
     */
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    //从第二行开始读取，把每行读取的内容封装到t对象里面
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //把每行数据对象t放到cachedDataList集合中
        cachedDataList.add(t);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            //存储完成清理list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        //保存数据，防止最后不够BATCH_COUNT条数据，也进行存储
        saveData();
    }

    //保存的方法
    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>) cachedDataList);

    }
}
