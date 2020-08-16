# HTML Notes

## Chore

### 常用HTML标签的英文全称及简单描述

| HTML标签    | 英文全称                  | 中文释义                       |
| ----------- | ------------------------- | ------------------------------ |
| a           | Anchor                    | 锚                             |
| abbr        | Abbreviation              | 缩写词                         |
| acronym     | Acronym                   | 取首字母的缩写词               |
| address     | Address                   | 地址                           |
| alt         | alter                     | 替用(一般是图片显示不出的提示) |
| b           | Bold                      | 粗体（文本）                   |
| bdo         | Direction of Text Display | 文本显示方向                   |
| big         | Big                       | 变大（文本）                   |
| blockquote  | Block Quotation           | 区块引用语                     |
| br          | Break                     | 换行                           |
| cell        | cell                      | 巢                             |
| cellpadding | cellpadding               | 巢补白                         |
| cellspacing | cellspacing               | 巢空间                         |
| center      | Centered                  | 居中（文本）                   |
| cite        | Citation                  | 引用                           |
| code        | Code                      | 源代码（文本）                 |
| dd          | Definition Description    | 定义描述                       |
| del         | Deleted                   | 删除（的文本）                 |
| dfn         | Defines a Definition Term | 定义定义条目                   |
| div         | Division                  | 分隔                           |
| dl          | Definition List           | 定义列表                       |
| dt          | Definition Term           | 定义术语                       |
| em          | Emphasized                | 加重（文本）                   |
| font        | Font                      | 字体                           |
| h1~h6       | Header 1 to Header 6      | 标题1到标题6                   |
| hr          | Horizontal Rule           | 水平尺                         |
| href        | hypertext reference       | 超文本引用                     |
| i           | Italic                    | 斜体（文本）                   |
| iframe      | Inline frame              | 定义内联框架                   |
| ins         | Inserted                  | 插入（的文本）                 |
| kbd         | Keyboard                  | 键盘（文本）                   |
| li          | List Item                 | 列表项目                       |
| nl          | navigation lists          | 导航列表                       |
| ol          | Ordered List              | 排序列表                       |
| optgroup    | Option group              | 定义选项组                     |
| p           | Paragraph                 | 段落                           |
| pre         | Preformatted              | 预定义格式（文本 ）            |
| q           | Quotation                 | 引用语                         |
| rel         | Reload                    | 加载                           |
| s/ strike   | Strikethrough             | 删除线                         |
| samp        | Sample                    | 示例（文本）                   |
| small       | Small                     | 变小（文本）                   |
| span        | Span                      | 范围                           |
| src         | Source                    | 源文件链接                     |
| strong      | Strong                    | 加重（文本）                   |
| sub         | Subscripted               | 下标（文本）                   |
| sup         | Superscripted             | 上标（文本）                   |
| td          | table data cell           | 表格中的一个单元格             |
| th          | table header cell         | 表格中的表头                   |
| tr          | table row                 | 表格中的一行                   |
| tt          | Teletype                  | 打印机（文本）                 |
| u           | Underlined                | 下划线（文本）                 |
| ul          | Unordered List            | 不排序列表                     |
| var         | Variable                  | 变量（文本）                   |

### HTML简单用法

```html
<html>
    <head></head>
    <body></body>
</html>

<a href="url">链接文本</a>

<p>段落</p>

<h1>一号标题</h1>

<b>粗体</b> <strong>粗体</strong>
<i>斜体</i> <em>斜体</em>

<img src="url" alt="some_text" title="" width="width_px" height="height_px">

<a href="http://www.runoob.com/" target="_blank">访问菜鸟教程!</a>

每30秒钟刷新当前页面:
<meta http-equiv="refresh" content="30">


table:
    占2Row 行     占2Col 列
<th rowspan="2" colspan="2"></th>
<tr></tr>   :table row
<td></td>   :table data

       边框粗细     单元格间距       单元格边距
<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <th>Header 1</th>           表头
        <th>Header 2</th>
    </tr>
    <tr>
        <td>row 1, cell 1</td>
        <td>row 1, cell 2</td>
    </tr>
    <tr>
        <td>row 2, cell 1</td>
        <td>row 2, cell 2</td>
    </tr>
</table>

<ul>                :unordered list   无序列表
    <li></li>
</ul>

<ol start="50">     :ordered list     有序列表   start="开始序号"
    <li></li>
</ol>



<h4>编号列表：</h4>
<ol>
</ol>  

<h4>大写字母列表：</h4>
<ol type="A">
</ol>  

<h4>小写字母列表：</h4>
<ol type="a">
</ol>  

<h4>罗马数字列表：</h4>
<ol type="I" start="401">
</ol>  

<h4>小写罗马数字列表：</h4>
<ol type="i">
</ol>  


<h4>圆点列表：</h4>
<ul type="disc"></ul>
<==>
<ul style="list-style-type:disc">
</ul>  

<h4>圆圈列表：</h4>
<ul style="list-style-type:circle">
</ul>  

<h4>正方形列表：</h4>
<ul style="list-style-type:square">
</ul>
```

### git usage

```bash
echo "# HTML-Learning" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/DeltaV235/HTML-Learning.git
git push -u origin master
```
