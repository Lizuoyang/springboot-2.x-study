#### 使用说明

1.  配置好mybatis-plus-general.properties文件中的包名，后缀名，数据库连接等
2.  IDEA工具在Build，Execution ，Deployment找到Build Tools下Maven项下的Runner ，在VM Options 添加-Dfile.encoding=GB2312，否则maven控制台打印会有乱码
3.  运行MyBatisPlusGeneratorAnt中的main方法根据提示操作即可
