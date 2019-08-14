# VIM 笔记
#### 从normal进入insert
```shell
a           # append
i           # insert
o           # open a line below
A           # append after line
I           # insert before line
O           # append a line above
```
```shell
sp、vs                          可进行分屏编辑
% s/str1/str2/[g]               可进行文本[全局]替换;
normal模式下  v/V/^v进行可视化    (光标/整行选中/块)操作
```
#### vim 快捷键 (insert mode)
```shell
ctrl + h                # 删除字符
ctrl + w                # 删除单词
ctrl + u                # 删除一行
ctrl + c / [            # 功能同Esc 切换insert和normal模式
```
---
shell:
```shell
ctrl+a ahead of command line
ctrl+e end of command line
```
---

#### normal下的快速移动
```shell
w/W         # word/WORD(以空格为分割符)               移动至下一个单词开头
e/E         # end of word/以空格为分隔符              移动至下一个单词尾部
b/B         # backword                               移动至上一个单词开头
```
#### 行间搜索
```shell
f           find   # 移动到字符上        ;/,     搜索下一个/上一个
t                  # 移动到前一个字符
F                  # 向前搜索
^                  # 移动第一个非空白字符
0                  # 移动至行首
$                  # 移动至行为

^u                 # up(half page)
^d                 # down(half page)
^f                 # front
^b                 # back
 
^o                 # 快速返回
 
gg                 # 文件头
G                  # 文件尾
 
H/M/L              # Head/Middle/Lower
zz                 # 中至当前行
```
#### 快速增删改查(normal mode)
**删除:**
```shell
x                   # 快速删除一个字符
d                   # 配合文本对象快速删除
dw                  # delete word
daw                 # delete around word
diw                 # delete in word
dt"                 # delete to "
d$                  # delete to end of line
d0                  # delete to head of line
2dd                 # delete 2 line
4x                  # delete 2 char

visual mode:
d                   # delete select string
```
**修改**
```shell
r                   # replace           替换一个字符
s                   # substitute        删除并进入插入模式
4s                  #                   删除4个字符并进入insert模式
R                   # Replace mode      进入insert模式并替换后续所有字符
S                   # Sunstitute        删除整行并进入插入模式
c                   # change            配合文本对象进行快速替换
caw                 #                   删除一个单词并进入插入模式
c                   # change            配合文本对象进行快速替换
C                   #                   删除整行，并进入插入模式
ct"                 # change to "
ctw                 # change to word
```
**搜索**
```shell
/                   # 向前(front)搜索
?                   # 向后(back)搜索
n                   # 跳转到下一个匹配
N                   # 跳转到上一个匹配
*/#                 # 对当前单词进行向前/向后的匹配
```

#### vim搜索替换
![vim_search_replace](vim-replcae-command-1.png)
![vim_search_flags](vim-replcae-command-2.png)