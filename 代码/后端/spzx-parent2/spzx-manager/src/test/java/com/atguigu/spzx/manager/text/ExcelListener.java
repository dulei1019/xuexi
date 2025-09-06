package com.atguigu.spzx.manager.text;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Description:
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {
    private List<T> data = new ArrayList<>();

    //读取excel数据
    //从第二行开始读取，把每行对象封装到t对象里面
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        data.add(t);

    }
    public List<T> getData() {
        return data;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
