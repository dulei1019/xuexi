package com.atguigu.spzx.manager.text;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Description:
 */
public class ExcelLisrener {

    public static void main(String[] args) {
        read();

        write();
    }


    public static void read(){
        //1.定义读取excel文件的位置
        String fileName = "C://01.xlsx";
        //2 调用方法
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();
        EasyExcel.read(fileName, CategoryExcelVo.class, excelListener)
                .sheet().doRead();

        List<CategoryExcelVo> data = excelListener.getData();
        System.out.println(data);
    }

    public static void write(){

        ArrayList<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;

        EasyExcel.write("C://02.xlsx", CategoryExcelVo.class)
                .sheet("分类数据").doWrite(list);

    }


}
