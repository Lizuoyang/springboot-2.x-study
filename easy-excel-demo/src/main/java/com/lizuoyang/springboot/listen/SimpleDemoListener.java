package com.lizuoyang.springboot.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSONObject;
import com.lizuoyang.springboot.pojo.eo.DemoDataRead;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单演示监听器
 *
 * @author lizuoyang
 * @date 2023/01/05
 */
@Slf4j
public class SimpleDemoListener implements ReadListener<DemoDataRead> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
//    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<DemoDataRead> cacheDataList = new ArrayList<>(BATCH_COUNT);

    @Override
    public void invoke(DemoDataRead demoDataRead, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSONObject.toJSONString(demoDataRead));
        cacheDataList.add(demoDataRead);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cacheDataList.size() >= BATCH_COUNT) {
            // 存储到数据库
            saveData();
            cacheDataList = new ArrayList<>(BATCH_COUNT);
        }
    }

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cacheDataList.size());
        JSONObject.toJSONString(cacheDataList);
        log.info("存储数据库成功！");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }
}
