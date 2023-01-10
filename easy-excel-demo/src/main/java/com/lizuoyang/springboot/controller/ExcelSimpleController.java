package com.lizuoyang.springboot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lizuoyang.springboot.listen.SimpleDemoListener;
import com.lizuoyang.springboot.pojo.eo.DemoDataRead;
import com.lizuoyang.springboot.pojo.eo.DemoDataWrite;
import com.lizuoyang.springboot.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * excel最简单操作控制器
 *
 * @author lizuoyang
 * @date 2023/01/04
 */
@Slf4j
@RestController
@RequestMapping("/simple")
public class ExcelSimpleController {
    private static final String fileReadName = FileUtil.getPath() + "demo" + File.separator + "read.xlsx";

    private static final String fileWriteName = FileUtil.getPath() + "write" +  + System.currentTimeMillis()  + ".xlsx";

    @GetMapping("/read")
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个Listener
        /*EasyExcel.read(fileReadName, DemoDataEo.class, new PageReadListener<DemoDataEo>(dataList -> {
            for (DemoDataEo demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();*/

        // 写法2：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileReadName, DemoDataRead.class, new SimpleDemoListener()).sheet().doRead();

        // 写法3
        // 一个文件一个reader
        /*try (ExcelReader excelReader = EasyExcel.read(fileReadName, DemoDataEo.class, new SimpleDemoListener()).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }*/
    }

    @GetMapping("/write")
    public void simpleWrite(@RequestParam("count") int count) {
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("姓名");
        excludeColumnFiledNames.add("生日");
//        excludeColumnFiledNames.add("红包金额");
        EasyExcel.write(fileWriteName, DemoDataWrite.class)
                // 只导出需要的字段
                .excludeColumnFiledNames(excludeColumnFiledNames)
                // 自动列宽
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("模板").doWrite(getData(count));
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoDataWrite}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("/write/download")
    public void download(@RequestParam("count") int count,HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        List<Map> data = getData(count);
        List<DemoDataWrite> writeData = new ArrayList<>();
        for (Map datum : data) {
            DemoDataWrite demoDataWrite = new DemoDataWrite();
            demoDataWrite.setName((String)datum.get("name"));
            demoDataWrite.setBirthDay((Date) datum.get("birthDay"));
            demoDataWrite.setMoney((Double) datum.get("money"));
            writeData.add(demoDataWrite);
        }
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DemoDataWrite.class).sheet("模板").doWrite(writeData);
    }


    /**
     * 模拟获取数据
     *
     * @return {@link List}
     */
    private List getData(int count) {
        // 模拟查询数据库数据
        List<Map> list = new ArrayList<>();
        for (int i = 1; i < count; i++) {
            /*DemoDataWrite dataEo = new DemoDataWrite();
            dataEo.setName("克隆人" + i + "号");
            dataEo.setBirthDay(new Date());
            dataEo.setMoney(new Double(200));
            list.add(dataEo);*/
            Map<String, Object> data = new HashMap<>();
            data.put("name", "克隆人" + i + "号");
            data.put("birthDay", new Date());
            data.put("money", new Double(200));
            list.add(data);
        }
        return list;
    }
}
