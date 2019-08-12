# VIM 笔记

#### 从normal进入insert

```shell
a   append
i   insert
o   open a line below
A   append after line
I   insert before line
O   append a line above
```

sp、vs                  可进行分屏编辑
% s/str1/str2/[g]       可进行文本[全局]替换；
normal模式下 v/V/^v进行可视化（光标/整行选中/块）操作
#### vim 快捷键 (insert mode)
ctrl + h            删除字符
ctrl + w            删除单词
ctrl + u            删除一行
ctrl + c / [        功能同Esc 切换insert和normal模式

---
shell:
```shell
ctrl+a ahead of command line
ctrl+e end of command line
```
---

#### normal下的快速移动
w/W     word/WORD(以空格为分割符)               移动至下一个单词开头
e/E     end of word/以空格为分隔符              移动至下一个单词尾部
b/B     backword                                移动至上一个单词开头

##### 行间搜索
f       find        移动到字符上        ；/，   搜索下一个/上一个
t                   移动到前一个字符
F                   向前搜索
^                   移动第一个非空白字符
0                   移动至行首
$                   移动至行为

^u              up(half page)
^d              down(half page)
^f              front
^b              back

^o              快速返回

gg              文件头
G               文件尾

H/M/L           Head/Middle/Lower
zz              中至当前行

#### 快速增删改查
