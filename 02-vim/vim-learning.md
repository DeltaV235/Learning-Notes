
# VIM 笔记

## TODO

- [ ] 整理笔记结构
- [ ] 补充笔记内容
- [ ] 重构图片目录

## VIM 基础操作

### VIM 模式

#### Normal Mode

VIM 打开后默认的模式，用于光标的移动和文本的操作。

**Normal Mode** 下的光标行内快速移动

- **w / W**
word / WORD(以空格为分割符)
移动至下一个单词开头
- **e / E**
end of word / 以空格为分隔符
移动至下一个单词尾部
- **b / B**
backword / 以空格为分隔符
移动至上一个单词开头
- **t / T**
till / 向前搜索
光标后移直到找到指定的字母
- **f / F**
find / 向前搜索
查找指定的字母并将光标移至其上

#### Insert Mode

VIM 中写入文本的模式。

从 **Normal Mode** 进入 **Insert Mode**

- **a**
append
在光标后进入 Insert Mode
- **i**
insert
在光标前进入 Insert Mode
- **o**
open a line below
在光标下新增一行并进入插入模式
- **A**
append after line
在行末进入 Insert Mode
- **I**
insert before line
在行首进入 Insert Mode
- **O**
append a line above
在光标上新增一行并进入插入模式

#### Command Mode

末行模式

- `sp`、`vs`
分屏编辑
- `% s/str1/str2/[g]`
文本[全局]替换;

#### visual Mode

Normal Mode 下
`v` / `V` / `^v` 进行可视化    (光标/整行选中/块)操作

#### Replace Mode

`R` 进入替换模式

## VIM 使用技巧

### VIM 快捷键 (Insert Mode)

```shell
ctrl + j                # 换行
ctrl + h                # 删除字符
ctrl + w                # 删除单词
ctrl + u                # 删除一行
ctrl + c / [            # 功能同Esc 切换insert和normal模式
```

- shell shortcut:

```shell
ctrl+a ahead of command line
ctrl+e end of command line
```

### 行间搜索

```shell
f           find   # 移动到字符上        ;/,     搜索下一个/上一个
t           till   # 移动到前一个字符
F                  # 向前搜索
^                  # 移动第一个非空白字符
0                  # 移动至行首
$                  # 移动至行尾

^u                 # up(half page)
^d                 # down(half page)

^f                 # front
^b                 # back

^e                 # down scroll onw row
^y                 # up scroll one row

^o                 # 快速返回

gg                 # 文件头
G                  # 文件尾

H/M/L              # Head/Middle/Lower

zz                 # 中置当前行
zt                 # 将当前行至于屏幕顶部
zb                 # 将当前行至于屏幕底部
```

### 快速增删改查(normal mode)

#### 删除

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
4x                  # delete 4 char

visual mode:
d                   # delete select string
```

#### 修改

```shell
r                   # replace           替换一个字符
s                   # substitute        删除并进入插入模式
4s                  #                   删除4个字符并进入insert模式
R                   # Replace mode      进入insert模式并替换后续所有字符
S                   # Sunstitute        删除整行并进入插入模式
c                   # change            配合文本对象进行快速替换
caw                 #                   删除一个单词并进入插入模式
C                   #                   删除整行，并进入插入模式
ct"                 # change to "
ctw                 # change to word
```

#### 搜索

```shell
/                   # 向前(front)搜索
?                   # 向后(back)搜索
n                   # 跳转到下一个匹配
N                   # 跳转到上一个匹配
*/#                 # 对当前单词进行向前/向后的匹配
```

---

### vim搜索替换

substitude 命令允许我们查找并且替换掉文本，并且支持正则表达式

`:[range]s[ubstitude]/{pattern}/{string}/{flag}`

- range 表示范围 比如 :10,20 表示 10-20 行，% 表示全部文本
- pattern 是要替换的模式，string 是替换后的文本
- flag 替换标志位
  - g(global) 全局范围内执行
  - c(confirm) 替换前确认
  - n(number) 仅报告匹配到的次数而不替换，可以用来查询匹配的次数

---

### vim多文件操作

- buffer 是指打开的一个文件的内存缓冲区
- window 是 buffer 可视化的分割区域
- tab 可以组织 window 为一个工作区

![vim-buffer-window-tab-2](pic/multifiles/vim-buffer-window-tab-2.png)

#### Buffer

- VIM 打开一个文件后会加载文件内容到缓冲区
- 之后的修改都是针对内存中的缓冲群，并不会直接保存到文件
- 直到我们执行 `:w` 的时候才会把修改内容写入到文件里
- buffer 切换
  - 使用 `:ls`会列举当前缓冲区，使用 `:b n` 跳转到第 n 个缓冲区
  - `:bpre` `:bnext` `:bfirst` `:blast`
  - `:b buffer_name` 加上 tab 补全来跳转

---

#### Window

窗口是可视化的分割区域

- 一个缓冲区可以分割为多个窗口，每个窗口也可以打开不同的缓冲区
- `<C-w> + s` 水平分割，`<C-w> + v` 垂直分割。或者 `:sp` 和 `:vs`
- 每个窗口可以继续无限分割
- `<C-w> + p`跳转到上一个窗口

![how_to_switch_window](pic/multifiles/vim-how-to-switch-window.png)
![how_to_resize_window](pic/multifiles/vim-how-to-resize-window.png)

---

#### Tab

Tab 是可以容纳一系列窗口的容器(:h tabpage)

![how_to_use_tab](pic/multifiles/vim-how-to-create-tab.png)
![how_to_switch_tab](pic/multifiles/vim-how-to-switch-tab.png)

---

### vim的Text Object

```shell
[number]<command>[text object]
```

- number 表示次数，command 是命令，d(delete)，c(change)，y(yank)
- text object 是要操作的文本对象，比如单词 w，句子 s，段落 p

```shell
iw              # inner word
aw              # around word
i"              # inner ""
```

---

### vim下的复制粘贴和寄存器

**normal mode:**

```shell
y               # yank
d               # delete
p               # put
yiw             # yank inner a word
```

**register:**

VIM 不使用单一剪贴板进行剪贴、复制与粘贴，而是多组寄存器

- 通过 `"{register}` 前缀可以指定寄存器，不指定则默认使用无名寄存器
- `"ayiw` 复制一个单词到寄存器 a 中，`"bdd` 剪贴当前行到寄存器 b 中
- VIM 中 `""` 表示无名寄存器，缺省使用。`""p` <==> `p`

除了有名寄存器 a-z，VIM 中还有一些常见寄存器

- 复制专用寄存器 `"0` 使用 y 复制文本同时会被拷贝到复制寄存器0中
- 系统剪贴板 `"+`
- `"%` 当前文件名，`".` 上次插入的文本

```vim
:e! 重新加载文件 ,且不保存
set clipboard=unnamed       # 使系统剪贴板与无名寄存器共用
set autoindent              # 自动缩进
set paste
set unpaste
```

---

### 宏的录制和使用

Vim使用q键来录制，q键结束录制
使用 q{register} 选择要保存的寄存器，把录制的命令保存其中
使用 @{register} 回放寄存器中的保存的所有命令

---

### 代码补全

![vim-code-completion](pic/code_completion/vim-code-completion.png)

---

### 修改vim配色

![vim-colorscheme](pic/colorscheme/color_scheme.jpg)

---

### vim常用命令

```vim
:h option-list 命令查询
```

---

### vim映射

vim映射就是把 一个操作 映射到 另一个操作

#### 基本映射

所有模式下都生效

```vim
map key default_key         # 在所有模式下生效
unmap key                   # 取消映射

imap key default_key        # 只在insert下生效
imap/nmap/vmap              # 定义的映射只在insert/normal/visual下有效
```

##### 非递归映射

映射不会递归解释映射的按键

```shell
let mapleader = ","         # set Leader Key
nnoremap                    # normal Non-recursive map
inoremap                    # insert
vnoremap                    # visual
```

---

## Plugins

### NERDtree

install vim plug and add this in ~/.vimrc

```shell
Plug 'scrooloose/nerdtree'
# 映射的快捷键
nnoremap <leader>f :NERDTreeFind<cr>        # 快速将光标回到NERDtree的位置
nnoremap <leader>t :NERDTreeToggle<cr>      # toggle NERDtree
let NERDTreeShowHidden=1                    # 显示隐藏文件
let NERDTreeIgnore=['\.swp', '\.pyc']       # NERDTree中不显示的文件
```

NERDtree基本操作

```vim
?: 快速帮助文档
o: 打开一个目录或者打开文件，创建的是buffer，也可以用来打开书签
go: 打开一个文件，但是光标仍然留在NERDTree，创建的是buffer
t: 打开一个文件，创建的是Tab，对书签同样生效
T: 打开一个文件，但是光标仍然留在NERDTree，创建的是Tab，对书签同样生效
i: 水平分割创建文件的窗口，创建的是buffer
gi: 水平分割创建文件的窗口，但是光标仍然留在NERDTree
s: 垂直分割创建文件的窗口，创建的是buffer
gs: 和gi，go类似
x: 收起当前打开的目录
X: 收起所有打开的目录
e: 以文件管理的方式打开选中的目录
D: 删除书签
P: 大写，跳转到当前根路径
p: 小写，跳转到光标所在的上一级路径
K: 跳转到第一个子路径
J: 跳转到最后一个子路径
<C-j>和<C-k>: 在同级目录和文件间移动，忽略子目录和子文件
C: 将根路径设置为光标所在的目录
u: 设置上级目录为根路径
U: 设置上级目录为跟路径，但是维持原来目录打开的状态
r: 刷新光标所在的目录
R: 刷新当前根路径
I: 显示或者不显示隐藏文件
f: 打开和关闭文件过滤器
q: 关闭NERDTree
A: 全屏显示NERDTree，或者关闭全屏
```

---

### ctrlp

模糊搜索当前目录下的文件

---

### vim-easymotion

vim-easymotion
快速移动光标位置

**~/.vimrc**中加入如下递归映射

```shell
nmap <Leader>s <Plug>(easymotion-s2)
```

\<Leader>s 后，输入要查找的两个字符，然后选择文本中出现的高亮字符来跳转到相应的位置

---

### vim-surround

normal mode 下的增加、删除、修改成对的内容
`ds` delete a surrounding
`cs` change a surrounding
`ys` you add a surrounding
**usage:**
`ds"`
`ds(`
`cs"(`
`cs[(`      change surrounding '[' to '('
`ysiw"`
`ystg]`     you add a surrounding to 'char' ']'

also can use in vscode

### commentary.vim

install:
Plug 'tpope/vim-commentary'

usage:
`gcc`    comment/uncomment

### vim-gitgutter

Plug 'airblade/vim-gitgutter'
add `set updatetime=100` in ~/.vimrc

## Tumx

**install Tmux:**

```shell
sudo apt-get install tmux
```

## uppercase or lowercase

```shell
gu{motion}  lowercase
gU{motion}  uppercase
```

## vim 的配置

### 多会话撤销历史

```shell
# 启用 undofile，开启撤销文件
set undofile
# 指定撤销文件位置
set undodir=~/.vim/undodir
```

当 `undodir` 不存在时，创建该目录。

```shell
if !isdirectory(&undodir)
    call mkdir(&undodir, 'p', 0700)
endif
```

### 鼠标的支持

```shell
if has('mouse')
  if has('gui_running') || (&term =~ 'xterm' && !has('mac'))
    set mouse=a
  else
    set mouse=nvi
  endif
endif
```

- 当 VIM 以图形界面运行或终端兼容 xterm 且不是 Mac 时，启用完全的鼠标支持。鼠标在 VIM 中拖拽时，VIM 将使用可视模式选中对应的内容。当按下 Shift 键时，操作系统或终端接管鼠标事件，用户可以使用鼠标复制 VIM 窗口里的内容供其他应用程序使用。
- 否则（非图形界面的的终端，且终端类型不是 xterm），就只在正常模式（n）、可视模式（v）、插入模式（i）中使用鼠标。这意味着，当用户按下 `:` 键进入命令行模式时，VIM 将不对鼠标进行响应，这时，用户就可以使用鼠标复制 VIM 窗口里的内容到其他应用程序里去了。

### 中文支持

```shell
set fileencodings=ucs-bom,utf-8,gb18030,latin1
```

1. 首先，检查文件是不是有 Unicode 的 BOM（字节顺序标记）字符，有的话按照 BOM 字符来转换文件内容。
2. 其次，检查文件能不能当作 UTF-8 来解码；如果可以，就当作 UTF-8 来解释。
3. 否则，尝试用 GB18030 来解码；如果能成功，就当作 GB18030 来转换文件内容。
4. 最后，如果上面的解码都不成功，就按 Latin1 字符集来解码；由于这是单字节的编码，转换必定成功。

## vim command

### set

- set ft?
查询当前文件类型
