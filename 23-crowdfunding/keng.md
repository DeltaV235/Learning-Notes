# 坑

## JSON

要将 JavaBean 对象直接返回 json 字符串,需要导入 jackson 包,以及在 springmvc 配置文件中开启注解驱动<mvc:annotation-driven>

## script

html 中的 script 标签不建议自封闭,浏览器会将`<script>`到`</script>`中的所用文本当做js脚本处理
