https://www.cnblogs.com/jike1219/p/11182303.html


poi提供microsoft office旧版本支持,eg .xls Excel
poi-ooxml提供microsoft office新版本支持,eg .xlsx Excel
1 <dependency>
2         <groupId>org.apache.poi</groupId>
3         <artifactId>poi</artifactId>
4         <version>3.10-FINAL</version>
5     </dependency>
1 <dependency>
2         <groupId>org.apache.poi</groupId>
3         <artifactId>poi-ooxml</artifactId>
4         <version>3.10-FINAL</version>
5     </dependency>


一、POI概述

　　Jakarta POI 是一套用于访问微软格式文档的Java API。POI提供API给Java程序对Microsoft Office格式档案读和写的功能。在许多企业办公系统中，经常会有用户要求，需要对数据进行统计并且可以直接下载Excel文件。

　　结构：
HSSF － 提供读写Microsoft Excel格式档案的功能。(仅能导出2003的excel)
XSSF － 提供读写Microsoft Excel OOXML格式档案的功能。
HWPF － 提供读写Microsoft Word格式档案的功能。
HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
HDGF － 提供读写Microsoft Visio格式档案的功能    